package se.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mathias Fernstedt on 29-09-2016.
 */
 @Controller
public class webbController {
    @RequestMapping("/hej3")
    public ModelAndView displayQuiz() {
        return new ModelAndView("hej3");
    }

    @RequestMapping("/hej44")
    public ModelAndView displayQuiz2() {
        return new ModelAndView("hej4");
    }
}







