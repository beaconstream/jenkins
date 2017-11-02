package com.travelur.travelconnect.settings.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Abhijit.
 */

public class YourPreferencesDataModel {

    String qid;
    String question;
    String questiontype;
    List<String> options;

    public YourPreferencesDataModel(String qid, String question, String questiontype, List<String> options) {
        this.qid = qid;
        this.question = question;
        this.questiontype = questiontype;
        this.options = options;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}
