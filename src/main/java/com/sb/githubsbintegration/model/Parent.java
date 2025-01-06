package com.sb.githubsbintegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parent {
    @JsonProperty("sha")
    String sha;

    @JsonProperty("url")
    String url;

    @JsonProperty("html_url")
    String html_url;
    public String getSha() {
        return this.sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getHtml_url() {
        return this.html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

}
