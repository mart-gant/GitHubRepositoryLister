package com.example.githubrepositorylister.controller;

import com.example.githubrepositorylister.model.RepositoryResponse;
import com.example.githubrepositorylister.service.GitHubService;
import com.example.githubrepositorylister.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<RepositoryResponse>> getRepositories(@PathVariable String username) {
        List<RepositoryResponse> repositories = gitHubService.getNonForkRepositoriesWithBranches(username);
        return ResponseEntity.ok(repositories);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    record ErrorResponse(int status, String message) {}
}
