package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.DeckCardEntity
import com.example.data.local.entity.DeckEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    @Query("SELECT * FROM decks ORDER BY updatedAt DESC")
    fun getAllDecks(): Flow<List<DeckEntity>>

    @Query("SELECT * FROM decks WHERE id = :deckId LIMIT 1")
    fun getDeckById(deckId: Long): Flow<DeckEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deck: DeckEntity): Long

    @Update
    suspend fun updateDeck(deck: DeckEntity)

    @Delete
    suspend fun deleteDeck(deck: DeckEntity)

    @Query("SELECT * FROM deck_cards WHERE deckId = :deckId")
    fun getDeckCards(deckId: Long): Flow<List<DeckCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeckCards(cards: List<DeckCardEntity>)

    @Query("DELETE FROM deck_cards WHERE deckId = :deckId")
    suspend fun clearDeckCards(deckId: Long)
}
