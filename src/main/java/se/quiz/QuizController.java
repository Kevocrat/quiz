package se.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016-09-28.
 */

@Controller
public class QuizController {
    public List<String> funshit = Arrays.asList("Sweet", "Sweet", "Sweet");
    @Autowired
    private QuizRepository quizRepository;

//
//    @RequestMapping("/")
//    public ModelAndView toQuiz(){
//        return new ModelAndView("/Quiz")
//                .addObject("Question", "Aight")
//                .addObject("choices", funshit);
//    }

    @RequestMapping(path="/questions")
    public ModelAndView listQuestions() throws SQLException {
        return new ModelAndView("/questions")
                .addObject("questions", quizRepository.listQuestions());
    }

    //Visa quizfrågor
    @RequestMapping("/Quiz")
    public ModelAndView displayQuiz() {
        return new ModelAndView("/Quiz");
    }


    //posta svaren
    @RequestMapping("/result")
    public ModelAndView postAndContinue(@RequestParam String Value ) {
        System.out.println(Value);
        return new ModelAndView("/Results");
    }

    //Visa resultat
    @RequestMapping("/Results")
    public ModelAndView displayResults(){ return new ModelAndView("/Results");}

}

