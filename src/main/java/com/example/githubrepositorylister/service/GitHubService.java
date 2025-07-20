package com.example.githubrepositorylister;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RepositoryDto> getNonForkRepositoriesWithBranches(String username) {
        String reposUrl = "https://api.github.com/users/" + username + "/repos";
        RepositoryDto[] repositories = restTemplate.getForObject(reposUrl, RepositoryDto[].class);

        if (repositories == null) return List.of();

        return Arrays.stream(repositories)
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    String branchesUrl = "https://api.github.com/repos/" + username + "/" + repo.getName() + "/branches";
                    BranchDto[] branches = restTemplate.getForObject(branchesUrl, BranchDto[].class);
                    repo.setBranches(branches != null ? Arrays.asList(branches) : List.of());
                    return repo;
                })
                .collect(Collectors.toList());
    }
}
