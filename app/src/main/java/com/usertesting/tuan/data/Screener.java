package com.usertesting.tuan.data;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tuan on 3/17/2016.
 */

@JsonObject
public class Screener {

    @JsonProperty("id")
    @JsonField(name = "id")
    private String id;

    @JsonProperty("next_question")
    @JsonField(name = "next_question")
    private NextQuestion next_question;

    public String getScreenerId() {
        return id;
    }

    public NextQuestion getNextQuestion() {
        return next_question;
    }

}
