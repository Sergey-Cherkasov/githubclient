package br.svcdev.githubclient.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
        @PrimaryKey val id: Int,
        val login: String,
        val avatarUrl: String,
        val reposUrl: String
)