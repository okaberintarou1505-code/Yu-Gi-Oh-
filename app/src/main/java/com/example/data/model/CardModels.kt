package com.example.data.model

enum class CardType(val displayName: String) {
    NORMAL_MONSTER("Monstro Normal"),
    EFFECT_MONSTER("Monstro de Efeito"),
    FUSION_MONSTER("Monstro de Fusão"),
    SYNCHRO_MONSTER("Monstro Sincro"),
    SPELL("Magia"),
    TRAP("Armadilha")
}

enum class CardAttribute(val symbol: String, val displayName: String) {
    DARK("闇", "TREVAS"),
    LIGHT("光", "LUZ"),
    EARTH("地", "TERRA"),
    FIRE("炎", "FOGO"),
    WATER("水", "ÁGUA"),
    WIND("風", "VENTO"),
    DIVINE("神", "DIVINO"),
    SPELL("魔", "MAGIA"),
    TRAP("罠", "ARMADILHA")
}

enum class MonsterType(val displayName: String) {
    WARRIOR("Guerreiro"),
    SPELLCASTER("Mago"),
    DRAGON("Dragão"),
    ZOMBIE("Zumbi"),
    MACHINE("Máquina"),
    FIEND("Demônio"),
    BEAST_WARRIOR("Besta-Guerreira"),
    BEAST("Besta"),
    WINGED_BEAST("Besta Alada"),
    INSECT("Inseto"),
    DINOSAUR("Dinossauro"),
    REPTILE("Réptil"),
    FISH("Peixe"),
    SEA_SERPENT("Serpente Marinha"),
    AQUA("Aqua"),
    PYRO("Piro"),
    THUNDER("Trovão"),
    ROCK("Rocha"),
    PLANT("Planta"),
    PSYCHIC("Psíquico"),
    FAIRY("Fada"),
    NONE("Nenhum")
}

enum class BanStatus(val limit: Int, val displayName: String) {
    FORBIDDEN(0, "Proibida"),
    LIMITED(1, "Limitada a 1"),
    SEMI_LIMITED(2, "Semilimitada a 2"),
    UNLIMITED(3, "Ilimitada (3)")
}

enum class TacticalRole(val displayName: String, val description: String) {
    BEATSTICK("Beatstick", "Monstro com alto ATK para controle de campo sem sacrifício"),
    MONSTER_REMOVAL("Remoção de Monstro", "Destrói, bane ou devolve monstros do oponente"),
    BACKROW_REMOVAL("Remoção de Magia/Armadilha", "Limpa campo traseiro do oponente"),
    SEARCHER("Buscador / Tutor", "Busca cartas específicas do deck para a mão"),
    DRAW_ENGINE("Motor de Compra", "Acelera o deck gerando vantagem de cartas"),
    SWARM_REVIVAL("Enxame / Ressurreição", "Invocação Especial rápida do cemitério ou mão"),
    NEGATION_PROTECTION("Negação / Proteção", "Anula efeitos ou protege monstros chave"),
    BOSS_MONSTER("Monstro Chefe / Finalizador", "Carta de alto poder que define o duelo"),
    TRIBUTE_FODDER("Fonte de Tributo", "Monstros reciclados para invocações por tributo"),
    COMBO_ENABLER("Ativador de Combo", "Prepara o cemitério, modula níveis ou sincroniza")
}

enum class PlayerLevel(val levelNumber: Int, val title: String, val subtitle: String, val packsUnlocked: String) {
    LEVEL_1_EARLY(
        1,
        "Nível 1 - Início do Jogo",
        "Slifer Red (Iniciante)",
        "Deck Inicial e Booster Packs 01 a 08 (100 DP cada)"
    ),
    LEVEL_2_MID(
        2,
        "Nível 2 - Meio do Jogo",
        "Ra Yellow (Intermediário)",
        "Packs 01 a 21 (Cybernetic, Lost Millennium, Enemy of Justice...)"
    ),
    LEVEL_3_ENDGAME(
        3,
        "Nível 3 - Fim de Jogo / Meta",
        "Obelisk Blue (Avançado)",
        "Todos os Booster Packs 01 a 48 (Phantom Darkness, Light of Destruction, Duelist Genesis)"
    )
}

enum class Archetype(val displayName: String) {
    HERO("Elemental HERO"),
    DESTINY_HERO("Destiny HERO"),
    GLADIATOR_BEAST("Gladiator Beast"),
    LIGHTSWORN("Lightsworn"),
    MONARCH("Monarch"),
    CYBER_DRAGON("Cyber Dragon"),
    ZOMBIE("Zombie World"),
    SIX_SAMURAI("Six Samurai"),
    TELE_DAD("Tele-DAD / Dark Synchro"),
    GENERIC_BEATDOWN("Starter Beatdown"),
    GENERIC("Genérico / Suporte")
}

data class TagForceCard(
    val id: String,
    val name: String,
    val cardType: CardType,
    val attribute: CardAttribute,
    val monsterType: MonsterType = MonsterType.NONE,
    val level: Int = 0,
    val attack: Int? = null,
    val defense: Int? = null,
    val description: String,
    val packCode: String,
    val packName: String,
    val packDpCost: Int,
    val requiredPlayerLevel: PlayerLevel,
    val banStatus: BanStatus = BanStatus.UNLIMITED,
    val tacticalRole: TacticalRole,
    val archetype: Archetype = Archetype.GENERIC,
    val synergyNotes: String = "",
    val commonSubstitutions: List<String> = emptyList()
)

enum class DeckSection {
    MAIN,
    EXTRA,
    SIDE
}

data class DeckCardItem(
    val card: TagForceCard,
    var count: Int = 1,
    val section: DeckSection = DeckSection.MAIN
)

data class CardSubstitution(
    val originalCard: TagForceCard,
    val replacementCard: TagForceCard,
    val matchReason: String,
    val synergyScore: Int,
    val isAvailableAtCurrentLevel: Boolean
)

data class SynergyReport(
    val card: TagForceCard,
    val bestPartners: List<TagForceCard>,
    val strategicRolesNeeded: List<TacticalRole>,
    val recommendedDeckStrategy: String,
    val keyCombos: List<String>,
    val sampleDecklist: List<TagForceCard>
)
