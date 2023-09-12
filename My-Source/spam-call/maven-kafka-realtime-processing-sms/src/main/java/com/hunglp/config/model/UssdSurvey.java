package com.hunglp.config.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "caller",
        "called",
        "content",
        "resultCode"
})
public class UssdSurvey {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("caller")
    private String caller;

    @JsonProperty("called")
    private String called;

    @JsonProperty("content")
    private String content;

    @JsonProperty("resultCode")
    private Integer resultCode; // 1 : La Spam, -1 : Ko phai spam, 0 : bo qua
}
