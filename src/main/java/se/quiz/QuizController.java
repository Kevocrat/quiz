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
    public ModelAndView toQuiz() throws SQLException {
       Quiz thisQuiz = quizRepository.getQuiz();
        List<Integer> questionsInQuiz = quizRepository.listQuestionID(thisQuiz.quizID);
        return new ModelAndView("/Quiz")
                .addObject("Question", quizRepository.getQuestion(questionsInQuiz.get(0)))
                .addObject("choices", quizRepository.getChoicesForQuestion(questionsInQuiz.get(0)));


    }

    @RequestMapping(params="previous", method= RequestMethod.POST)
    public String previousQuestion() throws SQLException {
        System.out.println("previous");

        return "redirect:/";

    }

    @RequestMapping(params="next", method= RequestMethod.POST)
    public String nextQuestion() throws SQLException {
        System.out.println("next");

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

