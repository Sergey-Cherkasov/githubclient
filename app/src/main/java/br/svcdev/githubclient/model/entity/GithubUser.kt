package br.svcdev.githubclient.model.entity

import com.google.gson.annotations.Expose

data class GithubUser(
        @Expose val id: String,
        @Expose val login: String,
        @Expose val avatarUrl: String,
        @Expose val repos: String,
)