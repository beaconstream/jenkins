package com.travelur.travelconnect.settings.models.questionlisttypedatamodel;

/**
 * @author by Abhijit.
 */

public class QuestionListRadioButtonDataModelOptions {

    public String options;
    public boolean checked;

    public QuestionListRadioButtonDataModelOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
