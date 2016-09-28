package se.quiz;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Choice {
    private long quiz_ID;
    private long question_ID;
    private long choiceID;
    private String choice;
    private String descr;

    public Choice(long question_ID, long choiceID, String choice, String descr){
        this.question_ID=question_ID;
        this.choice=choice;
        this.choiceID=choiceID;
        this.descr=descr;
    }
}
