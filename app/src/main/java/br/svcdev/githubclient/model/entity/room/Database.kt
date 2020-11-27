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
        private const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. " +
                "Please call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}