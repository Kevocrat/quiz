package se.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-09-28.
 */
@Component
public class QuizRepository implements Repository {

    @Autowired
    private DataSource dataSource;


    public List<Choice> getChoicesForQuestion(long question_ID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT [Question_ID], [ChoiceID], [Choices], [Desc] FROM [Choices] WHERE Question_ID=?")) {
            ps.setLong(1, question_ID);
            try (ResultSet rs = ps.executeQuery()) {
                List<Choice> choices = new ArrayList<>();
                while (rs.next()) choices.add(rsChoice(rs));
                return choices;
            }
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }


    public List<Question> getQuestionsForQuiz(long quizID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT QuestionID, Questions FROM [Questions] WHERE Quiz_ID = ?")) {
            ps.setLong(1, quizID);
            try (ResultSet rs = ps.executeQuery()) {
                List<Question> questions = new ArrayList<>();
                while (rs.next()) questions.add(rsQuestion(rs));
                return questions;
            }
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public List<Quiz> listQuiz() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [Quiz]")) {
            List<Quiz> quizzes = new ArrayList<>();
            while (rs.next()) quizzes.add(rsQuiz(rs));
            return quizzes;
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public List<Answer> listAnswer() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [Ansvers]")) {
            List<Answer> answers = new ArrayList<>();
            while (rs.next()) answers.add(rsAnswer(rs));
            return answers;
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public List<Question> listQuestions() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT QuestionID, Questions FROM [Questions]")) {

            List<Question> questions = new ArrayList<>();
            while (rs.next()) questions.add(rsQuestion(rs));
            return questions;
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public Question getQuestion(long questionID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT QuestionID, Questions FROM [Questions] WHERE QuestionID = ?")) {
            ps.setLong(1, questionID);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException();
                else return rsQuestion(rs);
            }
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public Quiz getQuiz() {
        try (Connection conn = dataSource.getConnection();

             PreparedStatement ps = conn.prepareStatement("SELECT TOP 1 QuizID, Title, [Desc] FROM [Quiz] ORDER BY [CreatedDate] DESC")) {

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException();
                else return rsQuiz(rs);
            }
        } catch (SQLException e) {
            throw new SQLRepositoryException(e);
        }
    }

    public Answer getAnswer(long question_ID) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT AnswersID, Answers FROM [Ansvers] WHERE Question_ID = ?")) {
            ps.setLong(1, question_ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException();
                else return rsAnswer(rs);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    public List<Integer> listQuestionID(int Quiz_ID) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT QuestionID FROM [Questions] WHERE Quiz_ID = ?")) {
            ps.setInt(1, Quiz_ID);
            try (ResultSet rs = ps.executeQuery()) {
                List<Integer> questions = new ArrayList<>();
                if (!rs.next()) throw new SQLException();
                else
                    while (rs.next()) questions.add(rs.getInt("QuestionID"));
                return questions;
            }

        } catch (SQLException e) {

            throw new SQLException(e);
        }
    }


    private Choice rsChoice(ResultSet rs) throws SQLException {
        return new Choice(rs.getInt("Question_ID"), rs.getInt("ChoiceID"), rs.getString("Choices"), rs.getString("Desc"));
    }

    private Question rsQuestion(ResultSet rs) throws SQLException {
        return new Question(rs.getInt("QuestionID"), rs.getString("Questions"));
    }

    private Quiz rsQuiz(ResultSet rs) throws SQLException {
        return new Quiz(rs.getInt("QuizID"), rs.getString("Title"), rs.getString("Desc"), rs.getDate("CreatedDate"));
    }

    private Answer rsAnswer(ResultSet rs) throws SQLException {
        return new Answer(rs.getInt("AnswersID"), rs.getString("Answers"));
    }

    private int rsQuestionID(ResultSet rs) throws SQLException{
        return rs.getInt("QuestionID");
    }



}
