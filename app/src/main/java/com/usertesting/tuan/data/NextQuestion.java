package com.usertesting.tuan.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tuan on 3/19/2016.
 */
@JsonObject
public class NextQuestion {

    @JsonProperty("id")
    @JsonField(name = "id")
    private String id;

    @JsonProperty("question")
    @JsonField(name = "question")
    private String question;

    @JsonProperty("multi_answer")
    @JsonField(name = "multi_answer")
    private boolean multi_answer;

    @JsonProperty("answers")
    @JsonField(name = "answers")
    private List<Answer> answers;

    public String getNextQuestionId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isMultiAnswer() {
        return multi_answer;
    }

    public List<Answer> getAnswersList() {
        return answers;
    }
}

