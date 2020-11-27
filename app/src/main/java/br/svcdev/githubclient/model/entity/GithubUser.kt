package br.svcdev.githubclient.model.entity

import com.google.gson.annotations.Expose

data class GithubUser(
        @Expose val id: Int?,
        @Expose val login: String?,
        @Expose val avatarUrl: String?,
        @Expose val reposUrl: String?,
)