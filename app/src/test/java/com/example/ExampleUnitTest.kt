package com.example

import com.example.data.datasource.MetaDecksData
import com.example.data.datasource.TagForceCardDatabase
import com.example.data.model.PlayerLevel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testCardDatabaseHasCards() {
    val cards = TagForceCardDatabase.allCards
    assertTrue("O banco de dados deve conter cartas", cards.isNotEmpty())

    val stratos = TagForceCardDatabase.getCardById("c_stratos")
    assertNotNull(stratos)
    assertEquals("Elemental HERO Stratos", stratos?.name)
  }

  @Test
  fun testSubstitutionsEngine() {
    val mirrorForce = TagForceCardDatabase.getCardById("c_mirror_force")
    assertNotNull(mirrorForce)

    val subs = TagForceCardDatabase.findSubstitutionsFor(mirrorForce!!, PlayerLevel.LEVEL_1_EARLY)
    assertTrue("Deve encontrar substitutos para Mirror Force", subs.isNotEmpty())

    // Sakuretsu Armor is level 1 early accessible
    val hasSakuretsu = subs.any { it.replacementCard.id == "c_sakuretsu_armor" }
    assertTrue("Sakuretsu Armor deve ser uma sugestão de substituto", hasSakuretsu)
  }

  @Test
  fun testSynergyAnalysis() {
    val dad = TagForceCardDatabase.getCardById("c_dark_armed_dragon")
    assertNotNull(dad)

    val report = TagForceCardDatabase.analyzeSynergyForCard(dad!!)
    assertTrue("Deve conter parceiros de sinergia", report.bestPartners.isNotEmpty())
    assertTrue("Deve ter combos chave", report.keyCombos.isNotEmpty())
  }

  @Test
  fun testMetaDecksLoaded() {
    val metaDecks = MetaDecksData.metaDecks
    assertTrue(metaDecks.size >= 7)
    assertTrue(metaDecks.any { it.id == "meta_tele_dad" })
    assertTrue(metaDecks.any { it.id == "meta_starter_beatdown" })
  }
}

