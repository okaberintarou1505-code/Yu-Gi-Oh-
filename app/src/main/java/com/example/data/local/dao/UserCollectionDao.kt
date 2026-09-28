package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.UserCardEntity
import com.example.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCollectionDao {
    @Query("SELECT * FROM user_collection")
    fun getAllOwnedCards(): Flow<List<UserCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateOwned(userCard: UserCardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchOwned(cards: List<UserCardEntity>)

    @Query("DELETE FROM user_collection WHERE cardId = :cardId")
    suspend fun deleteOwnedCard(cardId: String)

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)
}
