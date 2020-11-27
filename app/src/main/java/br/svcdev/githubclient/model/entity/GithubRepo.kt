package br.svcdev.githubclient.model.entity

import com.google.gson.annotations.Expose

data class GithubRepo(
        @Expose val id: Int?,
        @Expose val name: String?,
        @Expose val description: String?,
        @Expose val forksCount: Int?
)
