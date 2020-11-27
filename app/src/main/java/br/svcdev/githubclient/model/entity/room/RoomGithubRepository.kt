package br.svcdev.githubclient.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [ForeignKey(
                entity = RoomGithubUser::class,
                parentColumns = ["id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE)]
)
data class RoomGithubRepository(
        @PrimaryKey var id: Int,
        var name: String,
        var descroption: String,
        var forksCount: Int,
        var userId: Int
)