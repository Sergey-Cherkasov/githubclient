package br.svcdev.githubclient.model.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.svcdev.githubclient.model.entity.room.dao.IRepositoryDao
import br.svcdev.githubclient.model.entity.room.dao.IUserDao

@androidx.room.Database(entities = [RoomGithubUser::class,
    RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: IUserDao
    abstract val repositoryDao: IRepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}