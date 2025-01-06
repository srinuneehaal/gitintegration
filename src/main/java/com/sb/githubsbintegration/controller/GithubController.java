package com.sb.githubsbintegration.controller;

import com.sb.githubsbintegration.model.GithubCommitContentResopnse;
import com.sb.githubsbintegration.model.GithubCommitResopnse;
import com.sb.githubsbintegration.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Value("${my.app.env}")
    private String myAppEnv;

    @Autowired
    public GithubController(GithubService githubService, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.githubService = githubService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/repo")
    public Mono<List<String>> getRepo(OAuth2AuthenticationToken authenticationToken) {

        String accessToken = getAccessToken(authenticationToken, "github");
        System.out.println("accesstoken===" + accessToken);
        Mono<List<String>> repos = githubService.getRepos(accessToken);

        repos.subscribe(
                reposList -> doSomething(reposList),
                error -> error.printStackTrace(),
                () -> System.out.println("completed without a value")
        );


        return repos;


    }

    private void doSomething(List<String> reposList) {

        reposList.stream().forEach(address -> {

            System.out.println(address);
        });
    }

    @GetMapping("/repo/commits")
    public List<GithubCommitResopnse> getCommitsForRepo(OAuth2AuthenticationToken authenticationToken) {
        String repoName = "obd-sb-ms";

        String accessToken = getAccessToken(authenticationToken, "github");
        System.out.println("accesstoken===" + accessToken);
        List<GithubCommitResopnse> commitsWithBranch = githubService.getCommitsWithBranch(accessToken, repoName, "dev");
        return commitsWithBranch;
    }

    @GetMapping("/repo/commit/content")
    public List<GithubCommitContentResopnse> getCommitsContentForBranch(OAuth2AuthenticationToken authenticationToken) {
        String repoName = "obd-sb-ms";

        String accessToken = getAccessToken(authenticationToken, "github");

        System.out.println("accesstoken===" + accessToken);
        System.out.println("myAppEnv-->"+myAppEnv);
        List<GithubCommitResopnse> commitsWithBranch = githubService.getCommitsWithBranch(accessToken, repoName, "dev");
        System.out.println("commitsWithBranch.size()-->" + commitsWithBranch.size());

        List<GithubCommitContentResopnse> githubCommitContentResopnses = new ArrayList<>();
//        commitsWithBranch.subscribe(githubCommitResopnses -> {
        commitsWithBranch.stream().forEach(githubCommitResopnse -> {
//                System.out.println("githubCommitResopnse url :"+githubCommitResopnse.getUrl());
                    List<GithubCommitContentResopnse> githubCommitContentResopnse = githubService
                            .getCommitsContentUsingSHA(accessToken, repoName, githubCommitResopnse.getSha());
                    //System.out.println("githubCommitContentResopnse.size()-->" + githubCommitContentResopnse.size());
//                githubCommitContentResopnse.subscribe(githubCommitResopnseList->{
                    githubCommitContentResopnse.stream().forEach(githubCommitContentResopnse1 ->
                    {
                        githubCommitContentResopnses.add(githubCommitContentResopnse1);
//                        System.out.println("sha from githubCommitContentResopnse1:" + githubCommitContentResopnse1.getSha());
                    });
                }
        );

//                githubCommitContentResopnse.getFiles().stream().forEach(file->{
//
//                    System.out.println("file name: "+file.getFilename()+", Raw URL:"+file.getRaw_url());
//                                    });
//                githubCommitContentResopnses.add(githubCommitContentResopnse);


//                }

//        );
        System.out.println("githubCommitContentResopnses size==>" + githubCommitContentResopnses.size());

        //https://github.com/srinuneehaal/obd-sb-ms/blob/76c6eb1b8531a3f94b7b252d55d5fca3bca7a1d2/test.json
        //https://github.com/srinuneehaal/obd-sb-ms/raw/76c6eb1b8531a3f94b7b252d55d5fca3bca7a1d2/test.json
        //https://raw.githubusercontent.com/srinuneehaal/obd-sb-ms/76c6eb1b8531a3f94b7b252d55d5fca3bca7a1d2/test.json
        githubService.getContent(accessToken,"https://raw.githubusercontent.com/srinuneehaal/obd-sb-ms/76c6eb1b8531a3f94b7b252d55d5fca3bca7a1d2/test.json");
        return githubCommitContentResopnses;
    }
    private String getAccessToken(OAuth2AuthenticationToken authenticationToken, String clientRegistrationId) {

        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService
                .loadAuthorizedClient(clientRegistrationId, authenticationToken.getName());

        if (client != null) {

            OAuth2AccessToken accessToken = client.getAccessToken();
            if (accessToken != null) {
                return accessToken.getTokenValue();

            }
        }
        throw new RuntimeException();
    }


}
