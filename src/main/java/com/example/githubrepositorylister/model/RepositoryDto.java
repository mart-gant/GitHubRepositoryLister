package com.example.githubrepositorylister.model;

import java.util.List;

public record RepositoryDto(String repositoryName, String ownerLogin, List<BranchDto> branches) {
    public record BranchDto(String name, String lastCommitSha) {}
}
