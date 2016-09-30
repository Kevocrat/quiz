package se.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2016-09-28.
 */

@Controller
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping("/")
    public ModelAndView toQuiz(HttpSession session) throws SQLException {
        List<Question> questions;
        Map<Integer, String> userAnswers = new HashMap<>();

        if(session.isNew()){
            Quiz thisQuiz = quizRepository.getQuiz();
            questions = quizRepository.listQuestion(thisQuiz.quizID);
            session.setAttribute("Quiz", thisQuiz);
            session.setAttribute("index", 0);
            session.setAttribute("Questions", questions);
            session.setAttribute("userAnswers", userAnswers);
        }
        else {
            questions  = (List <Question>)session.getAttribute("Questions");
            userAnswers = (Map<Integer, String>)session.getAttribute("userAnswers");

        }
        List<Choice> choices = quizRepository.getChoicesForQuestion(questions.get((int)session.getAttribute("index")).questionID);

        return new ModelAndView("/Quiz")
                .addObject("Question", questions.get((int)session.getAttribute("index")))
                .addObject("choices", choices);

    }

    @RequestMapping(params="previous", path="result", method= RequestMethod.POST)
    public String previousQuestion(HttpSession session, @RequestParam String q1, @RequestParam Integer qID) throws SQLException {

        Map<Integer, String> userAnswers = (Map<Integer, String>)session.getAttribute("userAnswers");
        userAnswers.put(qID, q1);
        session.setAttribute("userAnswers", userAnswers);

        List<Question> questions =(List<Question>) session.getAttribute("Questions");
        int index = (int)session.getAttribute("index");

        if(index > 0) {
            index--;
            session.setAttribute("index", index);
            return "redirect:/";
        }else return "redirect:/";

    }

    @RequestMapping(params="next", path="result", method= RequestMethod.POST)
    public String nextQuestion(HttpSession session, @RequestParam String q1, @RequestParam Integer qID) throws SQLException {

        Map<Integer, String> userAnswers = (Map<Integer, String>)session.getAttribute("userAnswers");
        userAnswers.put(qID, q1);
        session.setAttribute("userAnswers", userAnswers);

        List<Question> questions =(List<Question>) session.getAttribute("Questions");
        int index = (int)session.getAttribute("index");

        if(index < questions.size()-1) {
            index++;
            session.setAttribute("index", index);
            return "redirect:/";
        }else return "redirect:/";

    }

    @RequestMapping(params="toDB", path="result", method= RequestMethod.POST)
    public ModelAndView sendToDB(HttpSession session, @RequestParam String q1, @RequestParam Integer qID) throws SQLException {
        Quiz thisquiz = (Quiz)session.getAttribute("Quiz");
        HashMap<Integer, String> userAnswers = (HashMap<Integer, String>)session.getAttribute("userAnswers");
        userAnswers.put(qID, q1);
        HashMap<Integer, String> correctAnswers= new HashMap<>();


        for(Answer answer: quizRepository.listAnswer(thisquiz.quizID)) {
            correctAnswers.put(answer.question_ID, answer.answer);
        }

        int points = ResultLogic.Result(userAnswers, correctAnswers);

//        System.out.println("to DB");

        return new ModelAndView("/Results")
                .addObject("score", points);

    }

    //Visa quizfrågor
    @RequestMapping("/Quiz")
    public ModelAndView displayQuiz() {
        return new ModelAndView("/Quiz");
    }


    //posta svaren
//    @RequestMapping("/result")
//    public ModelAndView postAndContinue(@RequestParam String Value ) {
//        System.out.println(Value);
//        return new ModelAndView("/Results");
//    }

    //Visa resultat
//    @RequestMapping("/Results")
//    public ModelAndView displayResults(){ return new ModelAndView("/Results");}

}

