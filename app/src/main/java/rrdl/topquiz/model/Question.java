package rrdl.topquiz.model;

import java.util.List;

/**
 * Created by moneem on 14/02/18.
 */

public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;
    //Getters
    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
    //Class constructor
    public Question(String question, List<String> choicelist, int answerIndex)
    {
        this.mQuestion=question;
        this.mChoiceList=choicelist;
        this.mAnswerIndex=answerIndex;

    }
}
