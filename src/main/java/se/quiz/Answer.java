package se.quiz;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Answer {
    private long answerID; //answerID behövs ej..
    private String answer;
    private long quiz_ID;
    private long question_ID;



    public Answer(long answerID, String answer){
        this.answerID=answerID;
        this.answer=answer;
    }
}
