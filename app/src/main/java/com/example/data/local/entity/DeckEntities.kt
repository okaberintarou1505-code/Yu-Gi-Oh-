package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val archetype: String = "GENERIC",
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "deck_cards",
    foreignKeys = [
        ForeignKey(
            entity = DeckEntity::class,
            parentColumns = ["id"],
            childColumns = ["deckId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["deckId"]), Index(value = ["deckId", "cardId", "section"], unique = true)]
)
data class DeckCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val deckId: Long,
    val cardId: String,
    val count: Int,
    val section: String // MAIN, EXTRA, SIDE
)

@Entity(tableName = "user_collection")
data class UserCardEntity(
    @PrimaryKey
    val cardId: String,
    val ownedCount: Int = 1
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val duelistName: String = "Jaden Yuki",
    val playerLevel: Int = 1, // 1 = Early, 2 = Mid, 3 = Endgame
    val currentDp: Int = 2500
)
