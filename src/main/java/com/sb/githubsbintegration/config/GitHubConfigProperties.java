package com.sb.githubsbintegration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Configuration("gitHubProperties")
//@PropertySource("classpath:application.properties")
public class GitHubConfigProperties {

    @Value("${app.github.url}")
    private String githubUrl;

    private Environment env;

    public String getConfigValue(String configKey){
        return env.getProperty(configKey);
    }
    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}