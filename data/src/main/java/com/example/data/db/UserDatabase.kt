package com.example.data.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.domain.model.User
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(item: User.Item)

    @Delete
    suspend fun removeUser(item: User.Item)

    @Query("SELECT * FROM user WHERE login LIKE :value")
    suspend fun getUsers(value: String): List<User.Item>

    @Query("SELECT EXISTS(SELECT * FROM user WHERE id = :id)")
    suspend fun isRowIsExist(id : Int) : Boolean
}

@Database(
    entities = [User.Item::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): UserDatabase = Room
            .databaseBuilder(context, UserDatabase::class.java, "users.db")
            .build()
    }
}