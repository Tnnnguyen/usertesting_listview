package com.usertesting.tuan.data;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tuan on 3/17/2016.
 */

@JsonObject
public class TestItem {

    public static final String STATE_RESERVED = "reserved";
    public static final String STATE_AVAILABLE = "available";
    public static final String OS_ANDROID = "android";

    @JsonProperty("id")
    @JsonField(name = "id")
    private String id;

    @JsonProperty("state")
    @JsonField(name = "state")
    private String state;

    @JsonProperty("unreliable_internal_state")
    @JsonField(name = "unreliable_internal_state")
    private String unreliable_internal_state;

    @JsonProperty("form_factor")
    @JsonField(name = "form_factor")
    private List<String> form_factor;

    @JsonProperty("operating_systems")
    @JsonField(name = "operating_systems")
    private List<String> operating_systems;

    @JsonProperty("web_browsers")
    @JsonField(name = "web_browsers")
    private List<String> web_browsers;

    @JsonProperty("instructions")
    @JsonField(name = "instruction")
    private String instructions;

    @JsonProperty("other_instructions")
    @JsonField(name = "other_instructions")
    private String other_instructions;

    @JsonProperty("type")
    @JsonField(name = "type")
    private String type;

    @JsonProperty("url")
    @JsonField(name = "url")
    private String url;

    @JsonProperty("introduction")
    @JsonField(name = "introduction")
    private String introduction;

    @JsonProperty("created_at")
    @JsonField(name = "created_at")
    private String created_at;

    @JsonProperty("updated_at")
    @JsonField(name = "updated_at")
    private String updated_at;

    @JsonProperty("reference_id")
    @JsonField(name = "reference_id")
    private String reference_id;

    @JsonProperty("is_for_recruitment")
    @JsonField(name = "is_for_recruitment")
    private boolean is_for_recruitment = false;

    @JsonProperty("is_moderated")
    @JsonField(name = "is_moderated")
    private boolean is_moderated = false;

    @JsonProperty("recorder_type")
    @JsonField(name = "recorder_type")
    private String recorder_type;

    @JsonProperty("screener")
    @JsonField(name = "screener")
    private Screener screener;

    public Screener getScreener() {
        return screener;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getUnreliableInternalState() {
        return unreliable_internal_state;
    }

    public List<String> getFormFactor() {
        return form_factor;
    }

    public List<String> getOperatingSystems() {
        return operating_systems;
    }

    public List<String> getWebBrowsers() {
        return web_browsers;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getOtherInstructions() {
        return other_instructions;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getCreatedTime() {
        return created_at;
    }

    public String getUpdatedTime() {
        return updated_at;
    }

    public String getReferenceId() {
        return reference_id;
    }

    public boolean is_for_recruitment() {
        return is_for_recruitment;
    }

    public boolean isModerated() {
        return is_moderated;
    }

    public String getRecorderType() {
        return recorder_type;
    }
}
