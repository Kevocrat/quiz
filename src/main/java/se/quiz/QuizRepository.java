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

    public List<Question> listQuestions() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT QuestionID, Questions FROM [Questions]")) {
            List<Question> questions = new ArrayList<>();
            while (rs.next()) questions.add(rsQuestion(rs));
            return questions;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

//    public Question getQuestion(int questionID) throws SQLException {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT QuestionID, Questions FROM [Question] WHERE QuestionID = ?")) {
//            ps.setLong(1, questionID);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (!rs.next()) throw new SQLException();
//                else return rsQuestion(rs);
//            }
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        }
//    }

    private Question rsQuestion(ResultSet rs) throws SQLException {
        return new Question(rs.getLong("QuestionID"), rs.getString("Questions"));
    }

//    private Answer rsAnswer(ResultSet rs) throws SQLException {
//        return new Answer(rs.getLong("AnswerID"), rs.getString("answer"));
//    }

//    public Answer getAnswer(long question_ID) throws SQLException {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT AnswerID, answer FROM [Answer] WHERE Question_ID = ?")) {
//            ps.setLong(1, question_ID);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (!rs.next()) throw new SQLException();
//                else return rsAnswer(rs);
//            }
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        }
//    }
}
