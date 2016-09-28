package se.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016-09-28.
 */
@Controller
public class TestController {
    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(path="/questions")
    public ModelAndView listQuestions() throws SQLException {
        return new ModelAndView("/questions")
                .addObject("questions", quizRepository.listQuestions());
    }
//    @RequestMapping(method=RequestMethod.GET, path="/")
//    public ModelAndView displayQuiz(){
//        return new ModelAndView("index");
//    }
}
