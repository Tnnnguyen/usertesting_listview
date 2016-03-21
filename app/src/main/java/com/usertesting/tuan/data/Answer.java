package com.usertesting.tuan.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tuan on 3/19/2016.
 */
@JsonObject
public class Answer implements Parcelable {

    public Answer(Parcel in) {
        this.id = in.readString();
        this.text = in.readString();
        this.acceptable = in.readByte() != 0;
    }

    public final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeByte((byte) (acceptable ? 1 : 0));
    }

    @JsonProperty("id")
    @JsonField(name = "id")
    private String id;

    @JsonProperty("text")
    @JsonField(name = "text")
    private String text;

    @JsonProperty("acceptable")
    @JsonField(name = "acceptable")
    private boolean acceptable;

    public String getAnswerId() {
        return id;
    }

    public String getAnswerText() {
        return text;
    }

    public boolean isAnswerAcceptable() {
        return acceptable;
    }
}
