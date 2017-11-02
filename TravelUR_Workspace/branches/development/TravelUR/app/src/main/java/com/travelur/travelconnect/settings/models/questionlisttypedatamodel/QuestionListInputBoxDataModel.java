package com.travelur.travelconnect.settings.models.questionlisttypedatamodel;

import java.util.ArrayList;

/**
 * @author  by Abhijit.
 */

public class QuestionListInputBoxDataModel {
    public String qid, question, question_type;
    public ArrayList<String> options;
    //public boolean checked;

    public QuestionListInputBoxDataModel(String qid, String questions, String question_type, ArrayList<String> options) {
        this.options = options;
        this.qid = qid;
        this.question = questions;
        this.question_type = question_type;

    }

}
