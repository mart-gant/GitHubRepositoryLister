package com.example.githubrepositorylister.service;

import com.example.githubrepositorylister.model.BranchResponse;
import com.example.githubrepositorylister.model.RepositoryResponse;
import com.example.githubrepositorylister.model.github.GitHubBranch;
import com.example.githubrepositorylister.model.github.GitHubRepository;
import com.example.githubrepositorylister.exception.UserNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {

    private final RestClient restClient;

    public GitHubService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader(HttpHeaders.USER_AGENT, "SpringApp")
                .build();
    }

    public List<RepositoryResponse> getNonForkRepositoriesWithBranches(String username) {
        try {
            GitHubRepository[] repositories = restClient.get()
                    .uri("/users/{username}/repos", username)
                    .retrieve()
                    .body(GitHubRepository[].class);

            List<RepositoryResponse> result = new ArrayList<>();

            if (repositories != null) {
                for (GitHubRepository repo : repositories) {
                    if (!repo.isFork()) {
                        GitHubBranch[] branches = restClient.get()
                                .uri("/repos/{owner}/{repo}/branches", repo.getOwner().getLogin(), repo.getName())
                                .retrieve()
                                .body(GitHubBranch[].class);

                        List<BranchResponse> branchResponses = new ArrayList<>();
                        if (branches != null) {
                            for (GitHubBranch branch : branches) {
                                branchResponses.add(new BranchResponse(branch.getName(), branch.getCommit().getSha()));
                            }
                        }

                        result.add(new RepositoryResponse(repo.getName(), repo.getOwner().getLogin(), branchResponses));
                    }
                }
            }

            return result;

        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User " + username + " not found");
        }
    }
}
