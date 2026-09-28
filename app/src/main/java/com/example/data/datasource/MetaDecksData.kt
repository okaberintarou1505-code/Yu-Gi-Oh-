package com.example.data.datasource

import com.example.data.model.PlayerLevel

data class MetaDeckTemplate(
    val id: String,
    val name: String,
    val tier: String,
    val requiredLevel: PlayerLevel,
    val archetype: String,
    val description: String,
    val strategySummary: String,
    val mainCards: List<Pair<String, Int>>, // cardId to count
    val extraCards: List<Pair<String, Int>> = emptyList(),
    val keyCardIds: List<String>,
    val beginnerSubstitutionsTip: String
)

object MetaDecksData {

    val metaDecks: List<MetaDeckTemplate> = listOf(
        MetaDeckTemplate(
            id = "meta_starter_beatdown",
            name = "Slifer Red Starter Beatdown (Nível 1)",
            tier = "Início do Jogo (100% Acessível)",
            requiredLevel = PlayerLevel.LEVEL_1_EARLY,
            archetype = "GENERIC_BEATDOWN",
            description = "O melhor deck para começar no Tag Force 3! Usa apenas cartas dos Booster Packs 01 a 08 (100 DP) e do Starter Deck inicial. Monstros com 1900-2300 de ATK apoiados pelas melhores remoções do jogo.",
            strategySummary = "Estratégia simples e devastadora: invoque Vorse Raider, Gemini Elf e Goblin Attack Force para bater por cima dos monstros da IA. Use Sakuretsu Armor, Trap Hole e Fissure para limpar ameaças sem gastar muitos DP.",
            mainCards = listOf(
                "c_vorse_raider" to 3,
                "c_gemini_elf" to 3,
                "c_goblin_attack_force" to 3,
                "c_jinzo" to 1,
                "c_sangan" to 1,
                "c_fissure" to 3,
                "c_smashing_ground" to 3,
                "c_mystical_space_typhoon" to 1,
                "c_heavy_storm" to 1,
                "c_swords_of_revealing_light" to 1,
                "c_monster_reborn" to 1,
                "c_premature_burial" to 1,
                "c_reinforcement_of_army" to 2,
                "c_sakuretsu_armor" to 3,
                "c_trap_hole" to 3,
                "c_dust_tornado" to 3,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_bottomless_trap_hole" to 2,
                "c_solemn_judgment" to 1
            ),
            extraCards = emptyList(),
            keyCardIds = listOf("c_vorse_raider", "c_goblin_attack_force", "c_sakuretsu_armor", "c_fissure"),
            beginnerSubstitutionsTip = "Já disponível desde o primeiro dia! Se não tiver Vorse Raider ainda, use qualquer monstro de 1800+ ATK do Pack 01 ou 06."
        ),

        MetaDeckTemplate(
            id = "meta_tele_dad",
            name = "Tele-DAD (Dark Armed Dragon)",
            tier = "Tier 0 (Rei Absoluto de 2008)",
            requiredLevel = PlayerLevel.LEVEL_3_ENDGAME,
            archetype = "TELE_DAD",
            description = "O deck mais temido e dominante da história de Yu-Gi-Oh! em 2008. Combina a velocidade insana de Emergency Teleport + Krebons com o motor Destiny HERO (Malicious e Destiny Draw) e a devastação de Dark Armed Dragon.",
            strategySummary = "Envia Malicious para o cemitério com Destiny Draw ou Armageddon Knight. Ativa Emergency Teleport para trazer Krebons e sincroniza Stardust Dragon ou Goyo Guardian. Quando tiver exatamente 3 TREVAS no túmulo, desce Dark Armed Dragon e limpa todo o campo adversário.",
            mainCards = listOf(
                "c_dark_armed_dragon" to 1,
                "c_krebons" to 3,
                "c_d_hero_malicious" to 2,
                "c_stratos" to 1,
                "c_armageddon_knight" to 2,
                "c_sangan" to 1,
                "c_plaguespreader_zombie" to 1,
                "c_emergency_teleport" to 2,
                "c_allure_of_darkness" to 2,
                "c_destiny_draw" to 3,
                "c_reinforcement_of_army" to 2,
                "c_monster_reborn" to 1,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_smashing_ground" to 2,
                "c_solemn_judgment" to 1,
                "c_dark_bribe" to 2,
                "c_torrential_tribute" to 1,
                "c_mirror_force" to 1,
                "c_bottomless_trap_hole" to 2
            ),
            extraCards = listOf(
                "c_stardust_dragon" to 2,
                "c_goyo_guardian" to 3
            ),
            keyCardIds = listOf("c_dark_armed_dragon", "c_emergency_teleport", "c_krebons", "c_d_hero_malicious"),
            beginnerSubstitutionsTip = "Se estiver no início do jogo e não tiver DAD ou Sincros, substitua por Caius the Shadow Monarch ou monstros beater de Trevas com Allure of Darkness."
        ),

        MetaDeckTemplate(
            id = "meta_lightsworn",
            name = "Lightsworn OTK (Judgment Dragon)",
            tier = "Tier 1 (Poder Máximo)",
            requiredLevel = PlayerLevel.LEVEL_3_ENDGAME,
            archetype = "LIGHTSWORN",
            description = "Deck hiper-agressivo focado em descarte direto do deck (mill). Quando 4 Lightsworn com nomes diferentes estiverem no cemitério, invoca Judgment Dragon por Invocação Especial pagando 1000 PV para destruir tudo.",
            strategySummary = "Solar Recharge compra cartas e acelera Wulf para entrar em campo de graça. Lumina revive do túmulo. Honest garante vitória em qualquer batalha na mão. Finalize com Judgment Dragon limpando o campo e atacando direto com 3000 ATK.",
            mainCards = listOf(
                "c_judgment_dragon" to 2,
                "c_lumina_lightsworn" to 3,
                "c_wulf_lightsworn" to 3,
                "c_honest" to 2,
                "c_sangan" to 1,
                "c_solar_recharge" to 3,
                "c_monster_reborn" to 1,
                "c_premature_burial" to 1,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_pot_of_avarice" to 2,
                "c_smashing_ground" to 2,
                "c_bottomless_trap_hole" to 2,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_solemn_judgment" to 1,
                "c_sakuretsu_armor" to 2
            ),
            extraCards = listOf(
                "c_stardust_dragon" to 1,
                "c_goyo_guardian" to 1
            ),
            keyCardIds = listOf("c_judgment_dragon", "c_solar_recharge", "c_lumina_lightsworn", "c_honest"),
            beginnerSubstitutionsTip = "Todas as cartas Lightsworn principais estão concentradas no Pack 25 [Light of Destruction] por 150 DP. Até desbloqueá-lo, use o deck Starter Beatdown."
        ),

        MetaDeckTemplate(
            id = "meta_gladiator_beast",
            name = "Gladiator Beast Control",
            tier = "Tier 1 (Campeão Mundial 2008)",
            requiredLevel = PlayerLevel.LEVEL_3_ENDGAME,
            archetype = "GLADIATOR_BEAST",
            description = "O deck que conquistou o Campeonato Mundial de Yu-Gi-Oh! em 2008. Maestria em controle de campo, reciclagem contínua no deck e fusões de contato poderosas sem gastar Polimerização.",
            strategySummary = "Batalhe com Laquari e troque por Bestiari para destruir magias do rival. Use Elemental HERO Prisma para copiar Bestiari do deck e realizar Fusão de Contato com Gyzarus, explodindo 2 cartas de uma só vez. Segure qualquer reação com War Chariot.",
            mainCards = listOf(
                "c_gb_bestiari" to 2,
                "c_gb_laquari" to 3,
                "c_prisma" to 2,
                "c_stratos" to 1,
                "c_sangan" to 1,
                "c_reinforcement_of_army" to 2,
                "c_e_call" to 2,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_monster_reborn" to 1,
                "c_smashing_ground" to 2,
                "c_gb_war_chariot" to 3,
                "c_solemn_judgment" to 1,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_bottomless_trap_hole" to 2,
                "c_dimensional_prison" to 2,
                "c_sakuretsu_armor" to 2
            ),
            extraCards = listOf(
                "c_gb_gyzarus" to 3
            ),
            keyCardIds = listOf("c_gb_gyzarus", "c_gb_bestiari", "c_gb_war_chariot", "c_prisma"),
            beginnerSubstitutionsTip = "Substitua War Chariot por Dark Bribe ou Solemn Judgment caso ainda não tenha os pacotes promocionais avançados."
        ),

        MetaDeckTemplate(
            id = "meta_cyber_dragon_otk",
            name = "Cyber Dragon OTK (Zane Truesdale)",
            tier = "Tier 1.5 (OTK Explosivo)",
            requiredLevel = PlayerLevel.LEVEL_2_MID,
            archetype = "CYBER_DRAGON",
            description = "O clássico deck do Zane Truesdale (Hell Kaiser). Especializado em One-Turn Kill com Power Bond, invocando monstros com mais de 5000 a 11000 de ATK que atacam múltiplas vezes.",
            strategySummary = "Invoque Cyber Dragon de graça se o adversário tiver monstros. Limpe o caminho de armadilhas com Heavy Storm ou Mystical Space Typhoon. Ative Power Bond para invocar Cyber Twin Dragon com 5600 ATK e ataque 2 vezes seguidas para vitória imediata.",
            mainCards = listOf(
                "c_cyber_dragon" to 2,
                "c_gene_warped" to 3,
                "c_goblin_attack_force" to 2,
                "c_sangan" to 1,
                "c_power_bond" to 3,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_monster_reborn" to 1,
                "c_premature_burial" to 1,
                "c_smashing_ground" to 3,
                "c_fissure" to 2,
                "c_swords_of_revealing_light" to 1,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_sakuretsu_armor" to 3,
                "c_bottomless_trap_hole" to 2,
                "c_solemn_judgment" to 1
            ),
            extraCards = listOf(
                "c_cyber_twin_dragon" to 3
            ),
            keyCardIds = listOf("c_cyber_dragon", "c_power_bond", "c_cyber_twin_dragon"),
            beginnerSubstitutionsTip = "Desbloqueável bem cedo no Nível 2 (Pack 15 Cybernetic Revolution por 150 DP). Enquanto não tiver 2 Cyber Dragons, use Polymerization e outros monstros máquina."
        ),

        MetaDeckTemplate(
            id = "meta_d_hero_monarch",
            name = "Destiny HERO Monarch (Caius & Raiza)",
            tier = "Tier 1 (Controle Absoluto)",
            requiredLevel = PlayerLevel.LEVEL_2_MID,
            archetype = "MONARCH",
            description = "Deck de tributo clássico da Era GX. Une a reciclagem infinita de Treeborn Frog e Malicious para baixar Monarcas devastadores como Caius the Shadow Monarch e Raiza the Storm Monarch todos os turnos.",
            strategySummary = "Na Fase de Espera, Treeborn Frog revive do cemitério sem custo. Você o tributa para Caius e bane a carta mais forte do oponente (monstro ou magia). Raiza joga cartas ao topo do deck travando as compras dele.",
            mainCards = listOf(
                "c_caius_monarch" to 3,
                "c_raiza_monarch" to 1,
                "c_mobius_monarch" to 2,
                "c_thestalos_monarch" to 2,
                "c_treeborn_frog" to 1,
                "c_d_hero_malicious" to 2,
                "c_stratos" to 1,
                "c_sangan" to 1,
                "c_destiny_draw" to 3,
                "c_allure_of_darkness" to 2,
                "c_reinforcement_of_army" to 2,
                "c_monster_reborn" to 1,
                "c_premature_burial" to 1,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_smashing_ground" to 2,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_bottomless_trap_hole" to 2,
                "c_solemn_judgment" to 1
            ),
            extraCards = emptyList(),
            keyCardIds = listOf("c_caius_monarch", "c_treeborn_frog", "c_raiza_monarch", "c_d_hero_malicious"),
            beginnerSubstitutionsTip = "Se estiver sem Caius (Pack 25), use Zaborg the Thunder Monarch (Pack 18) ou Thestalos e Mobius (Packs 11 e 12)."
        ),

        MetaDeckTemplate(
            id = "meta_hero_fusion",
            name = "Elemental HERO Fusion (Jaden Yuki)",
            tier = "Tier 2 (Clássico GX Favorito)",
            requiredLevel = PlayerLevel.LEVEL_2_MID,
            archetype = "HERO",
            description = "O lendário deck de Jaden Yuki com Fusões rápidas. Usa Stratos e E-Emergency Call para consistência, e Miracle Fusion para fundir diretamente do cemitério sem gastar recursos da mão.",
            strategySummary = "Faça fusões usando matérias normais e, quando estiverem no túmulo, ative Miracle Fusion para descer Elemental HERO Shining Flare Wingman com alto bônus de ATK e causar dano direto do ATK do monstro destruído.",
            mainCards = listOf(
                "c_stratos" to 1,
                "c_prisma" to 3,
                "c_gemini_elf" to 2,
                "c_vorse_raider" to 2,
                "c_sangan" to 1,
                "c_reinforcement_of_army" to 2,
                "c_e_call" to 3,
                "c_miracle_fusion" to 3,
                "c_monster_reborn" to 1,
                "c_heavy_storm" to 1,
                "c_mystical_space_typhoon" to 1,
                "c_smashing_ground" to 2,
                "c_swords_of_revealing_light" to 1,
                "c_mirror_force" to 1,
                "c_torrential_tribute" to 1,
                "c_bottomless_trap_hole" to 2,
                "c_sakuretsu_armor" to 3,
                "c_solemn_judgment" to 1
            ),
            extraCards = listOf(
                "c_shining_flare_wingman" to 3
            ),
            keyCardIds = listOf("c_stratos", "c_prisma", "c_miracle_fusion", "c_shining_flare_wingman"),
            beginnerSubstitutionsTip = "No início, Polymerization normal do Pack 04 funciona perfeitamente até obter Miracle Fusion no Pack 15."
        )
    )
}
