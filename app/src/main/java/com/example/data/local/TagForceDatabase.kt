package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.DeckDao
import com.example.data.local.dao.UserCollectionDao
import com.example.data.local.entity.DeckCardEntity
import com.example.data.local.entity.DeckEntity
import com.example.data.local.entity.UserCardEntity
import com.example.data.local.entity.UserProfileEntity

@Database(
    entities = [
        DeckEntity::class,
        DeckCardEntity::class,
        UserCardEntity::class,
        UserProfileEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TagForceDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun userCollectionDao(): UserCollectionDao

    companion object {
        @Volatile
        private var INSTANCE: TagForceDatabase? = null

        fun getDatabase(context: Context): TagForceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TagForceDatabase::class.java,
                    "tagforce_deckbuilder.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
