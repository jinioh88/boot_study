package com.bootstudy.study.common.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"사용자ID", "이름", "성", "메일주소", "전화번호1", "우편번호", "주소1"})
@Getter
@Setter
public class UserCsv implements Serializable {
    private static final long serialVersionUID = -1239812418548180L;

    @JsonProperty("사용자ID")
    String password;

    @JsonProperty("이름")
    String firstname;

    @JsonProperty("성")
    String lastName;

    @JsonProperty("메일주소")
    String email;

    @JsonProperty("전화번호")
    String tel;

    @JsonProperty("우편번호")
    String zip;

    @JsonProperty("주소")
    String address;
}
