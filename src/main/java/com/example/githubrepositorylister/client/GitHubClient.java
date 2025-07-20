package com.example.githubrepositorylister.client;

import com.example.githubrepositorylister.model.BranchDto;
import com.example.githubrepositorylister.model.RepositoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/users/{username}/repos")
    List<RepositoryDto> getRepositories(@PathVariable("username") String username);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
