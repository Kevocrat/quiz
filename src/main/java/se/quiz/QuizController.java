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
import java.util.Arrays;
import java.util.List;

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

        if(session.isNew()){
            Quiz thisQuiz = quizRepository.getQuiz();
            questions = quizRepository.listQuestion(thisQuiz.quizID);
            session.setAttribute("index", 0);
            session.setAttribute("Questions", questions);
        }
        else {
            questions  = (List <Question>)session.getAttribute("Questions");

        }
        List<Choice> choices = quizRepository.getChoicesForQuestion(questions.get((int)session.getAttribute("index")).questionID);

        return new ModelAndView("/Quiz")
                .addObject("Question", questions.get((int)session.getAttribute("index")))
                .addObject("choices", choices);
    }

    @RequestMapping(params="previous", method= RequestMethod.POST)
    public String previousQuestion(HttpSession session) throws SQLException {
        int index = (int)session.getAttribute("index");
        index--;
        session.setAttribute("index", index);

        return "redirect:/";

    }

    @RequestMapping(params="next", method= RequestMethod.POST)
    public String nextQuestion(HttpSession session, @RequestParam String q1) throws SQLException {
        session.setAttribute("answer" , q1);

        int index = (int)session.getAttribute("index");
        index++;

        session.setAttribute("index", index);
        return "redirect:/";

    }

    @RequestMapping(params="toDB", method= RequestMethod.POST)
    public String sendToDB() throws SQLException {
        System.out.println("to DB");

        return "redirect:/";

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

