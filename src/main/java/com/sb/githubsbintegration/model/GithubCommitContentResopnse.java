package com.sb.githubsbintegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommitContentResopnse {
    @JsonProperty("sha")
    String sha;
    @JsonProperty("node_id")
    String node_id;
    @JsonProperty("url")
    String url;
    @JsonProperty("html_url")
    String html_url;
    @JsonProperty("comments_url")
    String comments_url;


/*

    @JsonProperty("commit")
    Commit commit;

    @JsonProperty("author")
    Author author;

    @JsonProperty("committer")
    Committer committer;

    @JsonProperty("parents")
    ArrayList<Parent> parents;

    @JsonProperty("stats")
    Stats stats;

 */
    @JsonProperty("files")
    ArrayList<File> files;

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }
}
