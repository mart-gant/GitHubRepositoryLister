package com.example.githubrepositorylister;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repos")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public List<RepositoryDto> getRepositories(@PathVariable String username) {
        return gitHubService.getNonForkRepositoriesWithBranches(username);
    }
}
