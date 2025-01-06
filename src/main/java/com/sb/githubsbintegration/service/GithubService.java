package com.sb.githubsbintegration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.githubsbintegration.model.GithubCommitContentResopnse;
import com.sb.githubsbintegration.model.GithubCommitResopnse;
import com.sb.githubsbintegration.model.GithubRepoResopnse;
import com.sb.githubsbintegration.model.Parent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GithubService {
private String ownerName = "srinuneehaal";
    private final WebClient webClient;
//    @Autowired
//    private GitHubConfigProperties gitHubProperties;

    public GithubService(WebClient.Builder webClientBuilder) {
//        System.out.println("github gitHubConfigProperties: " + gitHubProperties.getConfigValue("app.github.url"));
        this.webClient = webClientBuilder
                .baseUrl("https://api.github.com")
                .build();
    }

    public Mono<List<String>> getRepos(String accessToken) {

        return webClient.get()
                .uri("/user/repos")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToFlux(GithubRepoResopnse.class)
                .map(GithubRepoResopnse::getName)
                .collectList();


    }

    public List<GithubCommitResopnse> getCommitsWithBranch(String accessToken, String repoName, String branch) {
        //https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits?sha=feature&since=2024-12-22T12:19:01Z

        return webClient.get()
                .uri("/repos/" + ownerName + "/" + repoName + "/commits?sha=" + branch)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToFlux(GithubCommitResopnse.class)
                .collectList().block();
    }

    public List<GithubCommitContentResopnse> getCommitsContentUsingSHA(String accessToken, String repoName, String sha) {
        //https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits/6914130fec3edd3df551d8cc372a50723abd6257
//        System.out.println("https://api.github.com/repos/" + ownerName + "/" + repoName + "/commits/" + sha);
        GithubCommitContentResopnse githubCommitContentResopnse= new GithubCommitContentResopnse();

        List<GithubCommitContentResopnse> collectList = webClient.get()
                .uri("/repos/" + ownerName + "/" + repoName + "/commits/" + sha)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToFlux(GithubCommitContentResopnse.class)
                .collectList().block();


        return collectList;
    }

    public void getContent(String accessToken, String uri){

        String response = webClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("json res-->"+response);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Parent parentObj= objectMapper.readValue(response, Parent.class);
            System.out.println("parent obj==>"+parentObj.getSha());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
