package br.svcdev.githubclient.model.entity

import com.google.gson.annotations.Expose

data class GithubRepo(
        @Expose val name: String,
        @Expose val description: String?,
        @Expose val fork: Boolean,
        @Expose val forksCount: Int
)
