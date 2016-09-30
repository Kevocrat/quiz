package se.quiz;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-09-30.
 */
public class ResultLogic {

    public static int Result(HashMap<Integer, String> userAnswers, HashMap<Integer, String> correctAnswers){
        int points = 0;

        for (Map.Entry<Integer, String> e: correctAnswers.entrySet()) {
            if (e.getValue().equals(userAnswers.get(e.getKey()))){
                points ++;
            }

        }

        return points;
    }
}
