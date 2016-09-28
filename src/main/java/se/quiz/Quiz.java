package se.quiz;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Quiz {
    private long quizID;
    private String title;
    private String descr;

    public Quiz(long quizID, String title, String descr){
        this.quizID=quizID;
        this.title=title;
        this.descr=descr;
    }
}
