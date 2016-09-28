package se.quiz;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Answer {
    public int answerID; //answerID behövs ej..
    public String answer;
    public int quiz_ID;
    public int question_ID;



    public Answer(int answerID, String answer){
        this.answerID=answerID;
        this.answer=answer;
    }
}
