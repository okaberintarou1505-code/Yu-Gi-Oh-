package com.example.data.datasource

import com.example.data.model.Archetype
import com.example.data.model.BanStatus
import com.example.data.model.CardAttribute
import com.example.data.model.CardSubstitution
import com.example.data.model.CardType
import com.example.data.model.MonsterType
import com.example.data.model.PlayerLevel
import com.example.data.model.SynergyReport
import com.example.data.model.TacticalRole
import com.example.data.model.TagForceCard

object TagForceCardDatabase {

    val allCards: List<TagForceCard> = listOf(
        // ==========================================
        // LEVEL 1: INÍCIO DO JOGO (Packs 01-08)
        // ==========================================
        TagForceCard(
            id = "c_vorse_raider",
            name = "Vorse Raider",
            cardType = CardType.NORMAL_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.BEAST_WARRIOR,
            level = 4,
            attack = 1900,
            defense = 1200,
            description = "Este Guerreiro-Besta malévolo não tem sentimentos e comete atos perversos com seu machado.",
            packCode = "06",
            packName = "Step Up Monster",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.GENERIC_BEATDOWN,
            synergyNotes = "Melhor beater de Nível 4 sem tributo do início do jogo (1900 ATK). Essencial para dominar os primeiros duelos da academia.",
            commonSubstitutions = listOf("c_gemini_elf", "c_gene_warped", "c_goblin_attack_force")
        ),
        TagForceCard(
            id = "c_gemini_elf",
            name = "Gemini Elf",
            cardType = CardType.NORMAL_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.SPELLCASTER,
            level = 4,
            attack = 1900,
            defense = 900,
            description = "Irmãs elfas que alternam seus ataques para confundir o inimigo.",
            packCode = "06",
            packName = "Step Up Monster",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.GENERIC_BEATDOWN,
            synergyNotes = "Excelente atacante inicial com 1900 de ATK. Ótima base para decks iniciantes de controle de campo.",
            commonSubstitutions = listOf("c_vorse_raider", "c_gene_warped")
        ),
        TagForceCard(
            id = "c_goblin_attack_force",
            name = "Goblin Attack Force",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.WARRIOR,
            level = 4,
            attack = 2300,
            defense = 0,
            description = "Se esta carta atacar, ela é colocada em Posição de Defesa no final da Fase de Batalha. Sua posição de batalha não pode ser alterada até a Fase Final do seu próximo turno.",
            packCode = "06",
            packName = "Step Up Monster",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.GENERIC_BEATDOWN,
            synergyNotes = "2300 ATK no nível 4 derruba praticamente qualquer monstro tributo dos duelistas no início do jogo.",
            commonSubstitutions = listOf("c_vorse_raider", "c_cyber_dragon")
        ),
        TagForceCard(
            id = "c_fissure",
            name = "Fissure",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Destrua 1 monstro com a face para cima com o menor ATK no campo do seu oponente.",
            packCode = "02",
            packName = "First Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Remoção direta acessível desde o primeiro minuto de jogo. Sem custo, perfeita para início.",
            commonSubstitutions = listOf("c_smashing_ground", "c_sakuretsu_armor", "c_caius_monarch")
        ),
        TagForceCard(
            id = "c_trap_hole",
            name = "Trap Hole",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando seu oponente Invocar por Invocação-Normal ou Virar um monstro com 1000 ou mais de ATK: escolha aquele monstro; destrua o alvo.",
            packCode = "02",
            packName = "First Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Excelente armadilha inicial contra qualquer monstro forte normal sumonado pelo adversário.",
            commonSubstitutions = listOf("c_bottomless_trap_hole", "c_sakuretsu_armor", "c_mirror_force")
        ),
        TagForceCard(
            id = "c_sakuretsu_armor",
            name = "Sakuretsu Armor",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando um monstro do oponente declarar um ataque: selecione o monstro atacante; destrua-o.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "A melhor armadilha de proteção defensiva no início do jogo. Substituição direta clássica de Mirror Force.",
            commonSubstitutions = listOf("c_mirror_force", "c_dimensional_prison", "c_trap_hole")
        ),
        TagForceCard(
            id = "c_smashing_ground",
            name = "Smashing Ground",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Destrua o monstro com a face para cima com a maior DEF no campo do seu oponente.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Destrói o monstro mais forte do oponente sem selecionar. Perfeito contra chefes defensivos.",
            commonSubstitutions = listOf("c_fissure", "c_lightning_vortex", "c_caius_monarch")
        ),
        TagForceCard(
            id = "c_dust_tornado",
            name = "Dust Tornado",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Destrua 1 Carta de Magia ou Armadilha no campo do seu oponente. Depois, você pode Baixar 1 Carta de Magia ou Armadilha da sua mão.",
            packCode = "02",
            packName = "First Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            tacticalRole = TacticalRole.BACKROW_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Remoção de magias e armadilhas defensiva que permite setar no turno do oponente. Ótimo substituto inicial para MST.",
            commonSubstitutions = listOf("c_mystical_space_typhoon", "c_heavy_storm")
        ),
        TagForceCard(
            id = "c_mystical_space_typhoon",
            name = "Mystical Space Typhoon",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Escolha 1 Carta de Magia ou Armadilha no campo; destrua o alvo.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.BACKROW_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Staple obrigatória em praticamente qualquer deck no Tag Force 3. Velocidade rápida Quick-Play.",
            commonSubstitutions = listOf("c_dust_tornado", "c_heavy_storm", "c_twister")
        ),
        TagForceCard(
            id = "c_heavy_storm",
            name = "Heavy Storm",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Destrua todas as Cartas de Magia e Armadilha no campo.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.BACKROW_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "A derradeira limpeza de campo traseiro. Abre caminho para ataques diretos e OTKs.",
            commonSubstitutions = listOf("c_mystical_space_typhoon", "c_dust_tornado")
        ),
        TagForceCard(
            id = "c_sangan",
            name = "Sangan",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.FIEND,
            level = 3,
            attack = 1000,
            defense = 600,
            description = "Se esta carta for enviada do campo para o Cemitério: adicione 1 monstro com 1500 ou menos de ATK do seu Deck à sua mão.",
            packCode = "05",
            packName = "Effect Monsters",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SEARCHER,
            archetype = Archetype.GENERIC,
            synergyNotes = "Busca praticamente qualquer monstro chave (Krebons, Lumina, Bestiari, D.D. Crow, Treeborn Frog).",
            commonSubstitutions = listOf("c_reinforcement_of_army", "c_e_call")
        ),
        TagForceCard(
            id = "c_swords_of_revealing_light",
            name = "Swords of Revealing Light",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Vire todos os monstros com a face para baixo do oponente para cima. Por 3 turnos do oponente, monstros do oponente não podem declarar ataque.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.GENERIC,
            synergyNotes = "Garante 3 turnos de sobrevivência para montar o campo ou juntar cartas de combo.",
            commonSubstitutions = listOf("c_threatening_roar", "c_waboku")
        ),
        TagForceCard(
            id = "c_jinzo",
            name = "Jinzo",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.MACHINE,
            level = 6,
            attack = 2400,
            defense = 1500,
            description = "Cartas de Armadilha e seus efeitos no campo não podem ser ativados. Negue todos os efeitos de Cartas de Armadilha no campo.",
            packCode = "08",
            packName = "Direct Attack",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.GENERIC,
            synergyNotes = "Trava completamente qualquer deck focado em armadilhas. Um dos tributos mais fortes no início.",
            commonSubstitutions = listOf("c_caius_monarch", "c_mobius_monarch", "c_cyber_dragon")
        ),
        TagForceCard(
            id = "c_mirror_force",
            name = "Mirror Force",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando um monstro do oponente declarar um ataque: destrua todos os monstros em Posição de Ataque do oponente.",
            packCode = "08",
            packName = "Direct Attack",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Vira jogos perdidos ao aniquilar o campo adversário num único ataque.",
            commonSubstitutions = listOf("c_sakuretsu_armor", "c_dimensional_prison", "c_torrential_tribute")
        ),
        TagForceCard(
            id = "c_torrential_tribute",
            name = "Torrential Tribute",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando um monstro for Invocado: destrua todos os monstros no campo.",
            packCode = "08",
            packName = "Direct Attack",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Botão de emergência contra campos cheios e invocações de monstros chefes.",
            commonSubstitutions = listOf("c_mirror_force", "c_bottomless_trap_hole")
        ),
        TagForceCard(
            id = "c_reinforcement_of_army",
            name = "Reinforcement of the Army",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Adicione 1 monstro do Tipo Guerreiro de Nível 4 ou menor do seu Deck à sua mão.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.SEARCHER,
            archetype = Archetype.HERO,
            synergyNotes = "Cola fundamental de decks Guerreiros, Elemental HERO e Six Samurai. Consistência pura.",
            commonSubstitutions = listOf("c_e_call", "c_sangan")
        ),

        // ==========================================
        // LEVEL 2: MEIO DO JOGO (Packs 09-21)
        // ==========================================
        TagForceCard(
            id = "c_cyber_dragon",
            name = "Cyber Dragon",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.MACHINE,
            level = 5,
            attack = 2100,
            defense = 1600,
            description = "Se o seu oponente controlar um monstro e você não controlar nenhum, você pode Invocar este card por Invocação-Especial da sua mão.",
            packCode = "15",
            packName = "Cybernetic Revolution",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.CYBER_DRAGON,
            synergyNotes = "A carta que redefiniu o Yu-Gi-Oh! GX. Invocação Especial gratuita de 2100 ATK que serve de tributo, atacante ou fusão.",
            commonSubstitutions = listOf("c_goblin_attack_force", "c_vorse_raider")
        ),
        TagForceCard(
            id = "c_power_bond",
            name = "Power Bond",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Invoque por Invocação-Fusão 1 Monstro de Fusão Máquina do seu Deck Adicional usando monstros da mão ou campo. O ATK do monstro é dobrado. Você sofre dano igual ao ATK original na Fase Final.",
            packCode = "15",
            packName = "Cybernetic Revolution",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.CYBER_DRAGON,
            synergyNotes = "Gera Cyber Twin Dragon com 5600 ATK que ataca 2 vezes para OTK instantâneo no Tag Force!",
            commonSubstitutions = listOf("c_polymerization", "c_overload_fusion")
        ),
        TagForceCard(
            id = "c_cyber_twin_dragon",
            name = "Cyber Twin Dragon",
            cardType = CardType.FUSION_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.MACHINE,
            level = 8,
            attack = 2800,
            defense = 2100,
            description = "\"Cyber Dragon\" + \"Cyber Dragon\". Uma Invocação-Fusão deste monstro só pode ser feita com as Matérias de Fusão acima. Este monstro pode atacar duas vezes durante cada Fase de Batalha.",
            packCode = "15",
            packName = "Cybernetic Revolution",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.CYBER_DRAGON,
            synergyNotes = "2800 x 2 = 5600 de dano! Com Power Bond sobe para 5600 x 2 = 11200 de dano garantindo vitória num turno.",
            commonSubstitutions = listOf("c_chimeratech_overdragon", "c_cyber_end_dragon")
        ),
        TagForceCard(
            id = "c_treeborn_frog",
            name = "Treeborn Frog",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.WATER,
            monsterType = MonsterType.AQUA,
            level = 1,
            attack = 100,
            defense = 100,
            description = "Uma vez por turno, durante sua Fase de Espera, se esta carta estiver no seu Cemitério e você não controlar Cartas de Magia ou Armadilha: você pode Invocar este card por Invocação-Especial.",
            packCode = "17",
            packName = "Shadow of Infinity",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.TRIBUTE_FODDER,
            archetype = Archetype.MONARCH,
            synergyNotes = "Tributo infinito para Caius, Raiza, Mobius e Thestalos a cada turno!",
            commonSubstitutions = listOf("c_d_hero_malicious", "c_cyber_dragon")
        ),
        TagForceCard(
            id = "c_raiza_monarch",
            name = "Raiza the Storm Monarch",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.WIND,
            monsterType = MonsterType.WINGED_BEAST,
            level = 6,
            attack = 2400,
            defense = 1000,
            description = "Se esta carta for Invocada por Invocação-Tributo: escolha 1 carta no campo; coloque o alvo no topo do Deck.",
            packCode = "21",
            packName = "Force of the Breaker",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.MONARCH,
            synergyNotes = "Devolve a carta ao topo do deck, travando a compra do adversário no próximo turno!",
            commonSubstitutions = listOf("c_caius_monarch", "c_mobius_monarch", "c_thestalos_monarch")
        ),
        TagForceCard(
            id = "c_mobius_monarch",
            name = "Mobius the Frost Monarch",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.WATER,
            monsterType = MonsterType.AQUA,
            level = 6,
            attack = 2400,
            defense = 1000,
            description = "Quando esta carta for Invocada por Invocação-Tributo: você pode escolher até 2 Cartas de Magia ou Armadilha no campo; destrua os alvos.",
            packCode = "11",
            packName = "Soul of the Duelist",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BACKROW_REMOVAL,
            archetype = Archetype.MONARCH,
            synergyNotes = "Destrói 2 cartas de magia/armadilha de uma vez ao cair no campo.",
            commonSubstitutions = listOf("c_heavy_storm", "c_caius_monarch")
        ),
        TagForceCard(
            id = "c_thestalos_monarch",
            name = "Thestalos the Firestorm Monarch",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.FIRE,
            monsterType = MonsterType.PYRO,
            level = 6,
            attack = 2400,
            defense = 1000,
            description = "Quando este card for Invocado por Invocação-Tributo: descarte 1 card aleatório da mão do seu oponente e, se for um Monstro, cause dano igual ao Nível x 100.",
            packCode = "12",
            packName = "Rise of Destiny",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.MONARCH,
            synergyNotes = "Ataca a mão do oponente reduzindo as opções estratégicas dele.",
            commonSubstitutions = listOf("c_caius_monarch", "c_raiza_monarch")
        ),
        TagForceCard(
            id = "c_stratos",
            name = "Elemental HERO Stratos",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.WIND,
            monsterType = MonsterType.WARRIOR,
            level = 4,
            attack = 1800,
            defense = 300,
            description = "Quando este card for Invocado: você pode ativar 1 destes efeitos:\n• Destrua Magias/Armadilhas no campo até o número de outros monstros HERO que você controla.\n• Adicione 1 monstro HERO do seu Deck à sua mão.",
            packCode = "20",
            packName = "Strike of Neos",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SEARCHER,
            archetype = Archetype.HERO,
            synergyNotes = "O coração do arquétipo HERO. Busca qualquer HERO (Malicious, Prisma, Neos) ou limpa o campo de armadilhas.",
            commonSubstitutions = listOf("c_e_call", "c_reinforcement_of_army")
        ),
        TagForceCard(
            id = "c_d_hero_malicious",
            name = "Destiny HERO - Malicious",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.WARRIOR,
            level = 6,
            attack = 800,
            defense = 800,
            description = "Você pode banir este card do seu Cemitério; Invoque por Invocação-Especial 1 \"Destiny HERO - Malicious\" do seu Deck.",
            packCode = "19",
            packName = "Power of the Duelist",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.DESTINY_HERO,
            synergyNotes = "O pilar do Tele-DAD e decks Synchro do Tag Force 3. Fornece matéria nível 6 de graça do deck para Synchro 8 (Stardust).",
            commonSubstitutions = listOf("c_treeborn_frog", "c_cyber_dragon")
        ),
        TagForceCard(
            id = "c_destiny_draw",
            name = "Destiny Draw",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Descarte 1 carta \"Destiny HERO\"; compre 2 cartas.",
            packCode = "19",
            packName = "Power of the Duelist",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.DRAW_ENGINE,
            archetype = Archetype.DESTINY_HERO,
            synergyNotes = "Descarta Malicious para o cemitério e compra 2 cartas novas. Vantagem imensa!",
            commonSubstitutions = listOf("c_allure_of_darkness", "c_upstart_goblin")
        ),
        TagForceCard(
            id = "c_prisma",
            name = "Elemental HERO Prisma",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.WARRIOR,
            level = 4,
            attack = 1700,
            defense = 1100,
            description = "Uma vez por turno: revele 1 Monstro de Fusão do seu Deck Adicional, envie 1 das Matérias de Fusão daquele monstro do seu Deck para o Cemitério; o nome deste card se torna o daquela matéria até o final do turno.",
            packCode = "23",
            packName = "Gladiator's Assault",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.HERO,
            synergyNotes = "Envia Bestiari do deck para o cemitério em Gladiator Beast, ou Neos para fusões de contato! Extremamente versátil.",
            commonSubstitutions = listOf("c_stratos", "c_armageddon_knight")
        ),
        TagForceCard(
            id = "c_pot_of_avarice",
            name = "Pot of Avarice",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Escolha 5 monstros no seu Cemitério; embaralhe todos os 5 no Deck e, depois, compre 2 cartas.",
            packCode = "16",
            packName = "Elemental Energy",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.DRAW_ENGINE,
            archetype = Archetype.GENERIC,
            synergyNotes = "Recicla seus monstros chefes e compra 2 cartas. Dá sustentabilidade insana ao deck.",
            commonSubstitutions = listOf("c_allure_of_darkness", "c_destiny_draw")
        ),
        TagForceCard(
            id = "c_zombie_master",
            name = "Zombie Master",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.ZOMBIE,
            level = 4,
            attack = 1800,
            defense = 0,
            description = "Uma vez por turno: você pode enviar 1 monstro da sua mão para o Cemitério e, depois, escolher 1 monstro Zumbi de Nível 4 ou menor no Cemitério de qualquer duelista; Invoque o alvo por Invocação-Especial.",
            packCode = "22",
            packName = "Tactical Evolution",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.ZOMBIE,
            synergyNotes = "Revive Zumbis todo turno gerando enxame imediato para tributo ou Synchro.",
            commonSubstitutions = listOf("c_mezuki", "c_monster_reborn")
        ),
        TagForceCard(
            id = "c_grandmaster_six_samurai",
            name = "Grandmaster of the Six Samurai",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.WARRIOR,
            level = 5,
            attack = 2100,
            defense = 800,
            description = "Você só pode controlar 1 \"Grandmaster of the Six Samurai\". Se você controlar um monstro \"Six Samurai\", você pode Invocar este card por Invocação-Especial da sua mão. Se for destruído por efeito de carta do oponente: adicione 1 \"Six Samurai\" do Cemitério à mão.",
            packCode = "20",
            packName = "Strike of Neos",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.SIX_SAMURAI,
            synergyNotes = "Invocação especial gratuita que bate com 2100 ATK e recicla Samurais ao morrer.",
            commonSubstitutions = listOf("c_cyber_dragon", "c_zanji_samurai")
        ),
        TagForceCard(
            id = "c_gene_warped",
            name = "Gene-Warped Warwolf",
            cardType = CardType.NORMAL_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.BEAST_WARRIOR,
            level = 4,
            attack = 2000,
            defense = 100,
            description = "Este lobisomem de guerra recebeu poder genético aprimorado para devastar inimigos.",
            packCode = "20",
            packName = "Strike of Neos",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.GENERIC_BEATDOWN,
            synergyNotes = "O monstro normal de 4 estrelas com maior ATK de todo o Tag Force 3 (2000 ATK sem tributo nem desvantagem!).",
            commonSubstitutions = listOf("c_vorse_raider", "c_gemini_elf")
        ),

        // ==========================================
        // LEVEL 3: FIM DO JOGO / META 2008 (Packs 23-48)
        // ==========================================
        TagForceCard(
            id = "c_dark_armed_dragon",
            name = "Dark Armed Dragon",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.DRAGON,
            level = 7,
            attack = 2800,
            defense = 1000,
            description = "Não pode ser Invocado por Invocação-Normal/Baixado. Deve ser Invocado por Invocação-Especial da mão tendo exatamente 3 monstros de TREVAS no seu Cemitério. Você pode banir 1 monstro de TREVAS do seu Cemitério e, depois, escolher 1 carta no campo; destrua o alvo.",
            packCode = "24",
            packName = "Phantom Darkness",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "O monstro mais dominante do meta de 2008. Limpa o campo do oponente inteiro sem limite de ativações por turno!",
            commonSubstitutions = listOf("c_chaos_sorcerer", "c_caius_monarch", "c_snipe_hunter")
        ),
        TagForceCard(
            id = "c_judgment_dragon",
            name = "Judgment Dragon",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.DRAGON,
            level = 8,
            attack = 3000,
            defense = 2600,
            description = "Não pode ser Invocado por Invocação-Normal/Baixado. Deve ser Invocado por Invocação-Especial da sua mão se você tiver 4 ou mais monstros \"Lightsworn\" com nomes diferentes no seu Cemitério. Você pode pagar 1000 PV; destrua todas as outras cartas no campo.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.LIGHTSWORN,
            synergyNotes = "Paga 1000 PV e destrói TUDO no campo com 3000 de ATK. O finalizador de duelos definitivo.",
            commonSubstitutions = listOf("c_dark_armed_dragon", "c_demise_king")
        ),
        TagForceCard(
            id = "c_stardust_dragon",
            name = "Stardust Dragon",
            cardType = CardType.SYNCHRO_MONSTER,
            attribute = CardAttribute.WIND,
            monsterType = MonsterType.DRAGON,
            level = 8,
            attack = 2500,
            defense = 2000,
            description = "1 Regulador + 1+ monstros não-Reguladores.\nDurante o turno de qualquer duelista, quando uma carta ou efeito for ativado que destruiria uma ou mais cartas no campo: você pode oferecer este card como Tributo; negue a ativação e, se isso acontecer, destrua-o. Durante a Fase Final, se este efeito foi ativado neste turno: você pode Invocar este card por Invocação-Especial do seu Cemitério.",
            packCode = "26",
            packName = "The Duelist Genesis",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Protege todo o seu campo contra Mirror Force, Torrential Tribute e Judgment Dragon. Revive sozinho no final do turno!",
            commonSubstitutions = listOf("c_thought_ruler", "c_solemn_judgment")
        ),
        TagForceCard(
            id = "c_goyo_guardian",
            name = "Goyo Guardian",
            cardType = CardType.SYNCHRO_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.WARRIOR,
            level = 6,
            attack = 2800,
            defense = 2000,
            description = "1 Regulador + 1+ monstros não-Reguladores.\nQuando este card destruir um monstro do oponente em batalha e enviá-lo para o Cemitério: você pode Invocar aquele monstro por Invocação-Especial no seu campo em Posição de Defesa.",
            packCode = "26",
            packName = "The Duelist Genesis",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Monstro Sincro de Nível 6 com absurdos 2800 de ATK que ainda rouba os monstros destruídos do oponente para o seu campo.",
            commonSubstitutions = listOf("c_stardust_dragon", "c_caius_monarch")
        ),
        TagForceCard(
            id = "c_emergency_teleport",
            name = "Emergency Teleport",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Invoque por Invocação-Especial 1 monstro Psíquico de Nível 3 ou menor da sua mão ou Deck, mas bana-o durante a Fase Final deste turno.",
            packCode = "26",
            packName = "The Duelist Genesis",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Traz Krebons instantaneamente direto do deck sem gastar Invocação Normal. Permite Sincronizar no primeiro turno.",
            commonSubstitutions = listOf("c_reinforcement_of_army", "c_monster_reborn")
        ),
        TagForceCard(
            id = "c_krebons",
            name = "Krebons",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.PSYCHIC,
            level = 2,
            attack = 1200,
            defense = 400,
            description = "Quando esta carta for escolhida como alvo de um ataque: você pode pagar 800 PV; negue o ataque.",
            packCode = "26",
            packName = "The Duelist Genesis",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Regulador de Trevas supremo. Sincroniza com Malicious (2 + 6 = 8) para Stardust Dragon, ou com Nível 4 para Goyo Guardian (2 + 4 = 6).",
            commonSubstitutions = listOf("c_plaguespreader_zombie", "c_sangan")
        ),
        TagForceCard(
            id = "c_allure_of_darkness",
            name = "Allure of Darkness",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Compre 2 cartas e, depois, bana 1 monstro de TREVAS da sua mão, ou envie toda a sua mão para o Cemitério se você não tiver monstros de TREVAS.",
            packCode = "24",
            packName = "Phantom Darkness",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.DRAW_ENGINE,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Acelera qualquer deck com monstros de Trevas cavando 2 cartas no deck e ajustando contagem para DAD.",
            commonSubstitutions = listOf("c_destiny_draw", "c_pot_of_avarice", "c_upstart_goblin")
        ),
        TagForceCard(
            id = "c_caius_monarch",
            name = "Caius the Shadow Monarch",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.FIEND,
            level = 6,
            attack = 2400,
            defense = 1000,
            description = "Se este card for Invocado por Invocação-Tributo: escolha 1 card no campo; bana o alvo e, se for um Monstro de TREVAS, cause 1000 de dano ao seu oponente.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.MONARCH,
            synergyNotes = "O melhor Monarca de todos os tempos. Bane qualquer carta (monstro, magia, armadilha) sem mandar pro cemitério.",
            commonSubstitutions = listOf("c_raiza_monarch", "c_mobius_monarch", "c_smashing_ground")
        ),
        TagForceCard(
            id = "c_honest",
            name = "Honest",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.FAIRY,
            level = 4,
            attack = 1100,
            defense = 1900,
            description = "Durante a Etapa de Dano, quando um monstro de LUZ que você controla batalhar: você pode enviar este card da sua mão para o Cemitério; aquele monstro ganha ATK igual ao ATK do monstro do oponente até o final deste turno.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.LIGHTSWORN,
            synergyNotes = "Garante vitória em qualquer batalha para monstros LUZ como Judgment Dragon, Lumina ou Cyber Dragon.",
            commonSubstitutions = listOf("c_sakuretsu_armor", "c_mirror_force")
        ),
        TagForceCard(
            id = "c_solar_recharge",
            name = "Solar Recharge",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Descarte 1 monstro \"Lightsworn\"; compre 2 cartas e, depois, envie os 2 cards do topo do seu Deck para o Cemitério.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.DRAW_ENGINE,
            archetype = Archetype.LIGHTSWORN,
            synergyNotes = "Compra 2 cartas e envia 2 para o cemitério, acelerando Wulf e carregando os 4 nomes para Judgment Dragon.",
            commonSubstitutions = listOf("c_allure_of_darkness", "c_destiny_draw")
        ),
        TagForceCard(
            id = "c_lumina_lightsworn",
            name = "Lumina, Lightsworn Summoner",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.SPELLCASTER,
            level = 3,
            attack = 1000,
            defense = 1000,
            description = "Uma vez por turno: você pode descartar 1 carta e, depois, escolher 1 monstro \"Lightsworn\" de Nível 4 ou menor no seu Cemitério; Invoque-o por Invocação-Especial. Durante cada Fase Final: envie os 3 cards do topo do seu Deck para o Cemitério.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.LIGHTSWORN,
            synergyNotes = "Revive Garoth ou Lyla todo turno para enxame imediato.",
            commonSubstitutions = listOf("c_monster_reborn", "c_premature_burial")
        ),
        TagForceCard(
            id = "c_wulf_lightsworn",
            name = "Wulf, Lightsworn Beast",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.BEAST_WARRIOR,
            level = 4,
            attack = 2100,
            defense = 300,
            description = "Não pode ser Invocado por Invocação-Normal/Baixado. Se este card for enviado do seu Deck para o Cemitério por efeito de carta: Invoque-o por Invocação-Especial.",
            packCode = "25",
            packName = "Light of Destruction",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.LIGHTSWORN,
            synergyNotes = "Cai no cemitério pelo efeito de mill e entra em campo de graça com 2100 ATK!",
            commonSubstitutions = listOf("c_cyber_dragon", "c_gene_warped")
        ),
        TagForceCard(
            id = "c_gb_bestiari",
            name = "Gladiator Beast Bestiari",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.WIND,
            monsterType = MonsterType.WINGED_BEAST,
            level = 3,
            attack = 1500,
            defense = 800,
            description = "Se este card for Invocado por Invocação-Especial pelo efeito de um monstro \"Gladiator Beast\": escolha 1 Carta de Magia/Armadilha no campo; destrua o alvo. No final da Fase de Batalha se este card batalhou: devolva-o ao Deck; Invoque 1 \"Gladiator Beast\" do seu Deck.",
            packCode = "23",
            packName = "Gladiator's Assault",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.BACKROW_REMOVAL,
            archetype = Archetype.GLADIATOR_BEAST,
            synergyNotes = "Destrói magias/armadilhas ao entrar e é a matéria chave para invocar Gyzarus!",
            commonSubstitutions = listOf("c_mystical_space_typhoon", "c_dust_tornado")
        ),
        TagForceCard(
            id = "c_gb_laquari",
            name = "Gladiator Beast Laquari",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.FIRE,
            monsterType = MonsterType.BEAST_WARRIOR,
            level = 4,
            attack = 1800,
            defense = 400,
            description = "Se este card for Invocado por Invocação-Especial pelo efeito de um monstro \"Gladiator Beast\", seu ATK original se torna 2100. No final da Fase de Batalha se batalhou: devolva-o ao Deck; Invoque 1 \"Gladiator Beast\" do seu Deck.",
            packCode = "23",
            packName = "Gladiator's Assault",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.BEATSTICK,
            archetype = Archetype.GLADIATOR_BEAST,
            synergyNotes = "Beater principal de Gladiator Beast, alcança 2100 de ATK quando rotacionado pelo efeito.",
            commonSubstitutions = listOf("c_vorse_raider", "c_gene_warped")
        ),
        TagForceCard(
            id = "c_gb_gyzarus",
            name = "Gladiator Beast Gyzarus",
            cardType = CardType.FUSION_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.WINGED_BEAST,
            level = 6,
            attack = 2400,
            defense = 1500,
            description = "\"Gladiator Beast Bestiari\" + 1 monstro \"Gladiator Beast\". Deve ser Invocado por Invocação-Especial (do seu Deck Adicional) devolvendo os cards acima que você controla para o Deck. Quando este card for Invocado por Invocação-Especial: escolha até 2 cartas no campo; destrua os alvos.",
            packCode = "27",
            packName = "Champion Pack / Promo",
            packDpCost = 200,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.GLADIATOR_BEAST,
            synergyNotes = "Fusão de contato sem precisar de Polimerização! Entra e destrói 2 cartas quaisquer no campo do oponente.",
            commonSubstitutions = listOf("c_gb_heraklinos", "c_dark_armed_dragon")
        ),
        TagForceCard(
            id = "c_gb_war_chariot",
            name = "Gladiator Beast War Chariot",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Se você controlar um monstro \"Gladiator Beast\" com a face para cima, quando o efeito de um Monstro de Efeito for ativado: negue a ativação e destrua o monstro.",
            packCode = "27",
            packName = "Champion Pack / Promo",
            packDpCost = 200,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.GLADIATOR_BEAST,
            synergyNotes = "Contra-armadilha de custo zero que anula e destrói qualquer efeito de monstro enquanto tiver um Glad Beast no campo.",
            commonSubstitutions = listOf("c_solemn_judgment", "c_dark_bribe")
        ),
        TagForceCard(
            id = "c_solemn_judgment",
            name = "Solemn Judgment",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando um monstro for Invocado, OU uma Carta de Magia/Armadilha for ativada: pague metade dos seus PV; negue a Invocação ou ativação e, se isso acontecer, destrua a carta.",
            packCode = "08",
            packName = "Direct Attack",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.GENERIC,
            synergyNotes = "A negação suprema. Paga metade dos pontos de vida e diz NÃO a qualquer jogada decisiva do adversário.",
            commonSubstitutions = listOf("c_dark_bribe", "c_gb_war_chariot", "c_threat_roar")
        ),
        TagForceCard(
            id = "c_dark_bribe",
            name = "Dark Bribe",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando seu oponente ativar uma Carta de Magia ou Armadilha: negue a ativação e, se isso acontecer, destrua-a, e depois seu oponente compra 1 carta.",
            packCode = "24",
            packName = "Phantom Darkness",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.NEGATION_PROTECTION,
            archetype = Archetype.GENERIC,
            synergyNotes = "Excelente contra-armadilha para proteger ataques decisivos contra Mirror Force ou Torrential.",
            commonSubstitutions = listOf("c_solemn_judgment", "c_dust_tornado")
        ),
        TagForceCard(
            id = "c_monster_reborn",
            name = "Monster Reborn",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Escolha 1 monstro em qualquer Cemitério; Invoque-o por Invocação-Especial no seu campo.",
            packCode = "02",
            packName = "First Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Ressuscita qualquer monstro de qualquer cemitério sem custo. Uma das cartas mais icônicas da história.",
            commonSubstitutions = listOf("c_premature_burial", "c_call_of_the_haunted")
        ),
        TagForceCard(
            id = "c_premature_burial",
            name = "Premature Burial",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Pague 800 PV e escolha 1 monstro no seu Cemitério; Invoque-o por Invocação-Especial em Posição de Ataque e equipe-o com esta carta.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Magia de ressurreição rápida que comba com Giant Trunade ou Bestiari.",
            commonSubstitutions = listOf("c_monster_reborn", "c_call_of_the_haunted")
        ),
        TagForceCard(
            id = "c_call_of_the_haunted",
            name = "Call of the Haunted",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Escolha 1 monstro no seu Cemitério; Invoque-o por Invocação-Especial em Posição de Ataque.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Ressuscitação surpresa durante a Fase de Batalha ou turno do oponente.",
            commonSubstitutions = listOf("c_monster_reborn", "c_premature_burial")
        ),
        TagForceCard(
            id = "c_bottomless_trap_hole",
            name = "Bottomless Trap Hole",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando o oponente Invocar um monstro com 1500 ou mais de ATK: destrua aquele monstro e, se isso acontecer, bana-o.",
            packCode = "07",
            packName = "Step Up Spell-Trap",
            packDpCost = 100,
            requiredPlayerLevel = PlayerLevel.LEVEL_1_EARLY,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Bane monstros invocados impedindo efeitos no cemitério como Malicious ou Treeborn Frog.",
            commonSubstitutions = listOf("c_trap_hole", "c_sakuretsu_armor", "c_mirror_force")
        ),
        TagForceCard(
            id = "c_dimensional_prison",
            name = "Dimensional Prison",
            cardType = CardType.TRAP,
            attribute = CardAttribute.TRAP,
            description = "Quando um monstro do oponente declarar um ataque: escolha o monstro atacante; bana o alvo.",
            packCode = "23",
            packName = "Gladiator's Assault",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.MONSTER_REMOVAL,
            archetype = Archetype.GENERIC,
            synergyNotes = "Bane o atacante, contornando monstros que não podem ser destruídos por efeito ou que ativam no túmulo.",
            commonSubstitutions = listOf("c_sakuretsu_armor", "c_mirror_force")
        ),
        TagForceCard(
            id = "c_armageddon_knight",
            name = "Armageddon Knight",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.WARRIOR,
            level = 4,
            attack = 1400,
            defense = 1200,
            description = "Quando este card for Invocado: você pode enviar 1 monstro de TREVAS do seu Deck para o Cemitério.",
            packCode = "24",
            packName = "Phantom Darkness",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.SEMI_LIMITED,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.TELE_DAD,
            synergyNotes = "Envia Malicious ou Plaguespreader direto pro cemitério para ligar o motor do Tele-DAD.",
            commonSubstitutions = listOf("c_sangan", "c_reinforcement_of_army")
        ),
        TagForceCard(
            id = "c_mezuki",
            name = "Mezuki",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.EARTH,
            monsterType = MonsterType.ZOMBIE,
            level = 4,
            attack = 1700,
            defense = 800,
            description = "Você pode banir este card do seu Cemitério e, depois, escolher 1 monstro Zumbi no seu Cemitério; Invoque o alvo por Invocação-Especial.",
            packCode = "27",
            packName = "Champion Pack / Promo",
            packDpCost = 200,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            banStatus = BanStatus.LIMITED,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.ZOMBIE,
            synergyNotes = "Revive qualquer monstro Zumbi do cemitério banindo-se. O combustível infinito dos decks Zumbi.",
            commonSubstitutions = listOf("c_zombie_master", "c_monster_reborn")
        ),
        TagForceCard(
            id = "c_plaguespreader_zombie",
            name = "Plaguespreader Zombie",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.DARK,
            monsterType = MonsterType.ZOMBIE,
            level = 2,
            attack = 400,
            defense = 200,
            description = "Se este card estiver no seu Cemitério: você pode colocar 1 card da sua mão no topo do Deck; Invoque este card por Invocação-Especial, mas bana-o quando ele deixar o campo.",
            packCode = "27",
            packName = "Champion Pack / Promo",
            packDpCost = 200,
            requiredPlayerLevel = PlayerLevel.LEVEL_3_ENDGAME,
            tacticalRole = TacticalRole.COMBO_ENABLER,
            archetype = Archetype.ZOMBIE,
            synergyNotes = "O melhor Regulador Zumbi. Revive a si mesmo do cemitério para Sincronizar Stardust ou Goyo!",
            commonSubstitutions = listOf("c_krebons", "c_sangan")
        ),
        TagForceCard(
            id = "c_e_call",
            name = "E - Emergency Call",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Adicione 1 monstro \"Elemental HERO\" do seu Deck à sua mão.",
            packCode = "18",
            packName = "Enemy of Justice",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.SEARCHER,
            archetype = Archetype.HERO,
            synergyNotes = "Busca Stratos ou Prisma diretamente para a mão com consistência máxima.",
            commonSubstitutions = listOf("c_reinforcement_of_army", "c_sangan")
        ),
        TagForceCard(
            id = "c_miracle_fusion",
            name = "Miracle Fusion",
            cardType = CardType.SPELL,
            attribute = CardAttribute.SPELL,
            description = "Invoque por Invocação-Fusão 1 Monstro de Fusão \"Elemental HERO\" do seu Deck Adicional, banindo as Matérias de Fusão listadas nele do seu campo ou Cemitério.",
            packCode = "15",
            packName = "Cybernetic Revolution",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.SWARM_REVIVAL,
            archetype = Archetype.HERO,
            synergyNotes = "Fusão do cemitério que não gasta cartas da mão! Cria Shining Flare Wingman instantaneamente.",
            commonSubstitutions = listOf("c_polymerization", "c_overload_fusion")
        ),
        TagForceCard(
            id = "c_shining_flare_wingman",
            name = "Elemental HERO Shining Flare Wingman",
            cardType = CardType.FUSION_MONSTER,
            attribute = CardAttribute.LIGHT,
            monsterType = MonsterType.WARRIOR,
            level = 8,
            attack = 2500,
            defense = 2100,
            description = "\"Elemental HERO Flame Wingman\" + \"Elemental HERO Sparkman\". Deve ser Invocado por Invocação-Fusão. Ganha 300 de ATK para cada \"Elemental HERO\" no seu Cemitério. Se destruir um monstro em batalha: cause dano igual ao ATK daquele monstro.",
            packCode = "16",
            packName = "Elemental Energy",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.HERO,
            synergyNotes = "O monstro assinatura de Jaden Yuki. Chega facilmente a mais de 4000 de ATK e causa dano de efeito letal.",
            commonSubstitutions = listOf("c_cyber_twin_dragon", "c_dark_armed_dragon")
        ),
        TagForceCard(
            id = "c_nephthys",
            name = "Sacred Phoenix of Nephthys",
            cardType = CardType.EFFECT_MONSTER,
            attribute = CardAttribute.FIRE,
            monsterType = MonsterType.WINGED_BEAST,
            level = 8,
            attack = 2400,
            defense = 1600,
            description = "Se este card for destruído por um efeito de card: Invoque este card por Invocação-Especial do Cemitério durante sua próxima Fase de Espera. Se o fizer: destrua todas as Cartas de Magia e Armadilha no campo.",
            packCode = "13",
            packName = "Flaming Eternity",
            packDpCost = 150,
            requiredPlayerLevel = PlayerLevel.LEVEL_2_MID,
            tacticalRole = TacticalRole.BOSS_MONSTER,
            archetype = Archetype.GENERIC,
            synergyNotes = "Renasce infinitamente do cemitério limpando todas as magias e armadilhas do campo.",
            commonSubstitutions = listOf("c_heavy_storm", "c_mobius_monarch")
        )
    )

    fun getCardById(id: String): TagForceCard? {
        return allCards.find { it.id == id }
    }

    fun searchCards(
        query: String = "",
        playerLevelFilter: PlayerLevel? = null,
        cardTypeFilter: CardType? = null,
        archetypeFilter: Archetype? = null,
        tacticalRoleFilter: TacticalRole? = null
    ): List<TagForceCard> {
        return allCards.filter { card ->
            val matchesQuery = query.isBlank() ||
                card.name.contains(query, ignoreCase = true) ||
                card.description.contains(query, ignoreCase = true) ||
                card.packName.contains(query, ignoreCase = true)

            val matchesLevel = playerLevelFilter == null ||
                card.requiredPlayerLevel.levelNumber <= playerLevelFilter.levelNumber

            val matchesType = cardTypeFilter == null || card.cardType == cardTypeFilter
            val matchesArchetype = archetypeFilter == null || card.archetype == archetypeFilter
            val matchesRole = tacticalRoleFilter == null || card.tacticalRole == tacticalRoleFilter

            matchesQuery && matchesLevel && matchesType && matchesArchetype && matchesRole
        }
    }

    /**
     * Sistema de Substituição Inteligente:
     * Encontra alternativas viáveis para uma carta que o jogador não possui,
     * levando em conta função tática, nível de progressão (DP/Packs desbloqueados) e arquétipo.
     */
    fun findSubstitutionsFor(card: TagForceCard, currentPlayerLevel: PlayerLevel): List<CardSubstitution> {
        val substitutions = mutableListOf<CardSubstitution>()

        // 1. Procura primeiro nos commonSubstitutions catalogados
        card.commonSubstitutions.forEach { subId ->
            val replacement = getCardById(subId)
            if (replacement != null && replacement.id != card.id) {
                val isAvailable = replacement.requiredPlayerLevel.levelNumber <= currentPlayerLevel.levelNumber
                val reason = when {
                    replacement.tacticalRole == card.tacticalRole ->
                        "Mesma função tática (${card.tacticalRole.displayName}). Excelente alternativa!"
                    replacement.archetype == card.archetype ->
                        "Mesmo arquétipo (${card.archetype.displayName}) mantendo a sinergia."
                    else -> "Alternativa clássica da era Tag Force 3 com efeito correspondente."
                }
                substitutions.add(
                    CardSubstitution(
                        originalCard = card,
                        replacementCard = replacement,
                        matchReason = reason,
                        synergyScore = if (replacement.archetype == card.archetype) 95 else 85,
                        isAvailableAtCurrentLevel = isAvailable
                    )
                )
            }
        }

        // 2. Se houver poucas, busca por cartas da mesma função tática
        allCards.filter { it.tacticalRole == card.tacticalRole && it.id != card.id && substitutions.none { s -> s.replacementCard.id == it.id } }
            .take(4)
            .forEach { replacement ->
                val isAvailable = replacement.requiredPlayerLevel.levelNumber <= currentPlayerLevel.levelNumber
                val levelBonus = if (isAvailable) 10 else -10
                val reason = "Função tática idêntica: ${card.tacticalRole.displayName}."
                substitutions.add(
                    CardSubstitution(
                        originalCard = card,
                        replacementCard = replacement,
                        matchReason = reason,
                        synergyScore = (75 + levelBonus).coerceIn(40, 95),
                        isAvailableAtCurrentLevel = isAvailable
                    )
                )
            }

        // Ordena: disponíveis no nível do jogador primeiro, depois maior synergyScore
        return substitutions.sortedWith(
            compareByDescending<CardSubstitution> { it.isAvailableAtCurrentLevel }
                .thenByDescending { it.synergyScore }
        )
    }

    /**
     * Motor de Análise de Sinergia e Recomendação Baseada em Uma Carta:
     * Analisa arquétipo, atributos, tipo e tática de jogo para sugerir parceiros ideais
     * e gerar a base de um deck em torno da carta escolhida.
     */
    fun analyzeSynergyForCard(targetCard: TagForceCard): SynergyReport {
        // Encontra parceiros do mesmo arquétipo
        val archetypePartners = allCards.filter {
            it.id != targetCard.id &&
            (it.archetype == targetCard.archetype && targetCard.archetype != Archetype.GENERIC)
        }

        // Encontra parceiros por atributo ou tipo
        val attrOrTypePartners = allCards.filter {
            it.id != targetCard.id &&
            (it.attribute == targetCard.attribute || it.monsterType == targetCard.monsterType) &&
            it.tacticalRole in listOf(TacticalRole.SEARCHER, TacticalRole.DRAW_ENGINE, TacticalRole.COMBO_ENABLER)
        }

        // Suportes universais staples da época
        val staples = allCards.filter {
            it.id != targetCard.id &&
            it.tacticalRole in listOf(TacticalRole.MONSTER_REMOVAL, TacticalRole.BACKROW_REMOVAL, TacticalRole.NEGATION_PROTECTION)
        }.take(5)

        val bestPartners = (archetypePartners + attrOrTypePartners + staples).distinctBy { it.id }.take(8)

        val strategicRolesNeeded = when (targetCard.tacticalRole) {
            TacticalRole.BOSS_MONSTER -> listOf(TacticalRole.SEARCHER, TacticalRole.COMBO_ENABLER, TacticalRole.NEGATION_PROTECTION)
            TacticalRole.BEATSTICK -> listOf(TacticalRole.MONSTER_REMOVAL, TacticalRole.BACKROW_REMOVAL, TacticalRole.SEARCHER)
            TacticalRole.SEARCHER, TacticalRole.COMBO_ENABLER -> listOf(TacticalRole.BOSS_MONSTER, TacticalRole.DRAW_ENGINE, TacticalRole.SWARM_REVIVAL)
            else -> listOf(TacticalRole.BEATSTICK, TacticalRole.SEARCHER, TacticalRole.MONSTER_REMOVAL)
        }

        val strategyText = when (targetCard.archetype) {
            Archetype.HERO, Archetype.DESTINY_HERO ->
                "Construa com foco em Guerreiros e invocações de Fusão/Synchro. Use Stratos, ROTA e Destiny Draw para cavar o deck rapidamente e finalizar com Shining Flare ou Malicious."
            Archetype.TELE_DAD ->
                "Mantenha exatamente 3 monstros de TREVAS no Cemitério usando Armageddon Knight e Allure. Use Krebons e Teleport para invocar Stardust Dragon e Goyo Guardian."
            Archetype.LIGHTSWORN ->
                "Utilize efeito de descarte/mill com Solar Recharge e Lumina para colocar 4 nomes Lightsworn no túmulo e descer Judgment Dragon limpando o campo."
            Archetype.GLADIATOR_BEAST ->
                "Controle o ritmo com ataques seguros protegidos por War Chariot. Troque monstros no fim do turno para invocar Bestiari e fazer Fusão de Contato com Gyzarus!"
            Archetype.MONARCH ->
                "Gere tributos gratuitos todos os turnos com Treeborn Frog e Cyber Dragon para invocar Caius, Raiza e Mobius, controlando as cartas do oponente."
            Archetype.CYBER_DRAGON ->
                "OTK Agressivo: invoque Cyber Dragon de graça, ative Power Bond e invoque Cyber Twin Dragon com 5600 ATK batendo duas vezes para vencer no turno 2."
            Archetype.ZOMBIE ->
                "Reciclagem do cemitério com Zombie Master, Mezuki e Livro da Vida, sincronizando repetidamente com Plaguespreader Zombie."
            Archetype.GENERIC_BEATDOWN ->
                "Estratégia ideal para o Início do Jogo (Nível 1): Coloque monstros de 1900-2000 ATK (Vorse Raider, Gemini Elf) e proteja-os com Sakuretsu Armor, Trap Hole e Fissure."
            else ->
                "Combine ${targetCard.name} com cartas de suporte tático (${strategicRolesNeeded.joinToString { it.displayName }}) para manter controle do duelo."
        }

        val keyCombos = when (targetCard.archetype) {
            Archetype.TELE_DAD -> listOf(
                "Emergency Teleport -> Invoca Krebons (Regulador) -> Sincroniza com Malicious para Stardust Dragon Nível 8",
                "Armageddon Knight envia Malicious -> Bane Malicious para trazer outro -> Abre campo para Dark Armed Dragon"
            )
            Archetype.LIGHTSWORN -> listOf(
                "Solar Recharge descarta Wulf -> Compra 2 cartas + Envia 2 do topo -> Wulf revive de graça em campo com 2100 ATK!",
                "4 Lightsworns no túmulo -> Invocação Especial de Judgment Dragon -> Paga 1000 PV e aniquila todo o campo"
            )
            Archetype.GLADIATOR_BEAST -> listOf(
                "Prisma envia Bestiari do Deck -> Copia o nome -> Contato com Laquari para Gyzarus -> Destrói 2 cartas do rival!",
                "Ataque protegido por War Chariot -> No final do turno devolve Laquari e traz Bestiari destruindo magia/armadilha"
            )
            Archetype.CYBER_DRAGON -> listOf(
                "Oponente controla monstro -> Invocação Especial de Cyber Dragon -> Invocação Normal de outro monstro para pressão imediata",
                "Power Bond fundindo 2 Cyber Dragons -> Cyber Twin Dragon com 5600 ATK ataca 2x causando 11200 de dano"
            )
            Archetype.HERO -> listOf(
                "Invocação de Stratos -> Busca Prisma -> Prisma envia Neos ou Sparkman -> Miracle Fusion do cemitério",
                "ROTA busca Stratos -> Stratos busca monstro de fusão -> Fusão sem perder cartas na mão"
            )
            Archetype.MONARCH -> listOf(
                "Fase de Espera: Treeborn Frog volta do túmulo -> Tributo para Caius the Shadow Monarch -> Bane carta do oponente",
                "Soul Exchange seleciona monstro do oponente como tributo para seu Monarca"
            )
            else -> listOf(
                "Controle de campo: Atacar com monstro de alto ATK e manter Sakuretsu Armor / Trap Hole setados",
                "Remoção com Fissure ou Smashing Ground antes de declarar ataque"
            )
        }

        // Gera uma lista sugerida equilibrada (Monstros + Magias + Armadilhas) em torno da carta
        val sampleList = (listOf(targetCard) + bestPartners).distinctBy { it.id }

        return SynergyReport(
            card = targetCard,
            bestPartners = bestPartners,
            strategicRolesNeeded = strategicRolesNeeded,
            recommendedDeckStrategy = strategyText,
            keyCombos = keyCombos,
            sampleDecklist = sampleList
        )
    }
}
