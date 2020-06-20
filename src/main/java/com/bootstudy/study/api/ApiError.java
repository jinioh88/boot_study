package com.bootstudy.study.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError implements Serializable {

    @Getter
    private static class Detail implements Serializable {
        private static final long serialVersionUID = -239842304932L;
        private final String target;
        private final String message;

        public Detail(String target, String message) {
            this.target = target;
            this.message = message;
        }
    }

    private static final Long serialVersionUID = -98761293876127836L;

    private String message;

    @JsonProperty("documentation_url")
    private String documentationUrl;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Detail> details = new ArrayList<>();

    public void addDetail(String target, String message) {
        details.add(new Detail(target, message));
    }
}
