package com.sb.githubsbintegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class GithubRepoResopnse {


    @JsonProperty("name")
    private String name;

    public GithubRepoResopnse() {

    }
    public GithubRepoResopnse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
