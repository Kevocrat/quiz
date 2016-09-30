package se.quiz;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Choice {
    public int question_ID;
    public int choiceID;
    public String choice;
    public String descr;

    public Choice(int question_ID, int choiceID, String choice, String descr){
        this.question_ID=question_ID;
        this.choice=choice;
        this.choiceID=choiceID;
        this.descr=descr;
    }
}
