package com.kodeco.flixflow.common

import com.kodeco.flixflow.R

val favouriteCategoriesDummyData = listOf(
  MovieCategory("cat-1", "Action"),
  MovieCategory("cat-4", "Fantasy"),
  MovieCategory("cat-6", "Sci-Fi")
)

val categoriesDummyData = listOf(
  MovieCategory("cat-1", "Action"),
  MovieCategory("cat-2", "Comedies"),
  MovieCategory("cat-3", "Dramas"),
  MovieCategory("cat-4", "Fantasy"),
  MovieCategory("cat-5", "Romance"),
  MovieCategory("cat-6", "Sci-Fi")
)

val moviesByCategoryDummyData: Map<String, List<Movie>> = mapOf(
  "Action" to listOf(
    Movie("mov-1", "Heroic Journey"),
    Movie("mov-2", "Explosive Adventure"),
    Movie("mov-3", "Combat for Justice"),
    Movie("mov-4", "Race Against Time"),
    Movie("mov-5", "Conspiracy Unveiled")
  ),
  "Comedies" to listOf(
    Movie("mov-6", "Unexpected Friendship"),
    Movie("mov-7", "Quest for Love"),
    Movie("mov-8", "Cultural Clash"),
    Movie("mov-9", "Life's Misadventures"),
    Movie("mov-10", "Chaos and Humor")
  ),
  "Dramas" to listOf(
    Movie("mov-11", "Tale of Loss"),
    Movie("mov-12", "Family Ties"),
    Movie("mov-13", "Self-Discovery Journey"),
    Movie("mov-14", "Ambition and Sacrifice"),
    Movie("mov-15", "Web of Lies")
  ),
  "Fantasy" to listOf(
    Movie("mov-16", "Epic Quest"),
    Movie("mov-17", "Forbidden Powers"),
    Movie("mov-18", "Allies Unite"),
    Movie("mov-19", "Forces Collide"),
    Movie("mov-20", "Hero's Journey")
  ),
  "Romance" to listOf(
    Movie("mov-21", "Star-Crossed Lovers"),
    Movie("mov-22", "Passion's Tale"),
    Movie("mov-23", "Unlikely Harmony"),
    Movie("mov-24", "Timeless Love Story"),
    Movie("mov-25", "Pursuit of Love")
  ),
  "Sci-Fi" to listOf(
    Movie("mov-26", "Futuristic Odyssey"),
    Movie("mov-27", "Alien Encounters"),
    Movie("mov-28", "Dystopian Survival"),
    Movie("mov-29", "Tech and Morality"),
    Movie("mov-30", "Space and Identity")
  )
)

val movieRatingsDummyData: Map<String, Int> = mapOf(
  "mov-1" to 5,
  "mov-2" to 4,
  "mov-3" to 3,
  "mov-4" to 5,
  "mov-5" to 2,
  "mov-6" to 4,
  "mov-7" to 1,
  "mov-8" to 3,
  "mov-9" to 5,
  "mov-10" to 4,
  "mov-11" to 3,
  "mov-12" to 2,
  "mov-13" to 5,
  "mov-14" to 4,
  "mov-15" to 3,
  "mov-16" to 2,
  "mov-17" to 1,
  "mov-18" to 5,
  "mov-19" to 4,
  "mov-20" to 3,
  "mov-21" to 2,
  "mov-22" to 1,
  "mov-23" to 5,
  "mov-24" to 4,
  "mov-25" to 3,
  "mov-26" to 2,
  "mov-27" to 1,
  "mov-28" to 4,
  "mov-29" to 3,
  "mov-30" to 5
)

val movieDescriptions: Map<String, String> = mapOf(
  "mov-1" to "A thrilling journey of heroism and bravery.",
  "mov-2" to "Explosive adventures and relentless pursuits.",
  "mov-3" to "High stakes and fierce combat for justice.",
  "mov-4" to "A race against time with breathtaking stunts.",
  "mov-5" to "Unveiling conspiracies with unmatched courage.",
  "mov-6" to "A hilarious mix-up leading to unexpected friendship.",
  "mov-7" to "Side-splitting antics in a quest for love.",
  "mov-8" to "A comedic clash of cultures and wit.",
  "mov-9" to "Misadventures and laughter at every turn.",
  "mov-10" to "Finding humor in the chaos of life.",
  "mov-11" to "A poignant tale of loss and redemption.",
  "mov-12" to "The complexities of family ties and secrets.",
  "mov-13" to "A journey of self-discovery and resilience.",
  "mov-14" to "The harsh realities of ambition and sacrifice.",
  "mov-15" to "Unraveling truths in a web of lies.",
  "mov-16" to "An epic quest in a land of myths and magic.",
  "mov-17" to "Forbidden powers and the fight against destiny.",
  "mov-18" to "Allies unite in a world of wonder and danger.",
  "mov-19" to "Dark forces and ancient prophecies collide.",
  "mov-20" to "A hero's journey to thwart unspeakable evil.",
  "mov-21" to "Star-crossed lovers defy odds for true love.",
  "mov-22" to "A tale of passion, heartbreak, and reconciliation.",
  "mov-23" to "Unlikely matches find harmony in love's melody.",
  "mov-24" to "Timeless love story weaving through trials and joy.",
  "mov-25" to "The pursuit of love amidst life's unexpected turns.",
  "mov-26" to "A futuristic odyssey beyond the bounds of reality.",
  "mov-27" to "Alien encounters that challenge human existence.",
  "mov-28" to "Navigating dystopian landscapes for survival and truth.",
  "mov-29" to "Technology and morality clash in a new world.",
  "mov-30" to "A journey through space, time, and identity."
)

val movieImages: Map<String, Int> = mapOf(
  "mov-1" to R.drawable.heroic_journey,
  "mov-2" to R.drawable.explosive_adventure,
  "mov-3" to R.drawable.combat_for_justice,
  "mov-4" to R.drawable.race_against_time,
  "mov-5" to R.drawable.conspiracy_unveiled,
  "mov-6" to R.drawable.unexpected_friendship,
  "mov-7" to R.drawable.quest_for_love,
  "mov-8" to R.drawable.culturcal_clash,
  "mov-9" to R.drawable.life_misadventures,
  "mov-10" to R.drawable.chaos_and_humor,
  "mov-11" to R.drawable.tale_of_loss,
  "mov-12" to R.drawable.family_ties,
  "mov-13" to R.drawable.self_discovery,
  "mov-14" to R.drawable.web_of_lies,
  "mov-15" to R.drawable.ambition_and_sacrifice,
  "mov-16" to R.drawable.epic_quests,
  "mov-17" to R.drawable.forbidden_powers,
  "mov-18" to R.drawable.allies_unite,
  "mov-19" to R.drawable.forces_collide,
  "mov-20" to R.drawable.hero_journey,
  "mov-21" to R.drawable.star_crossed_lovers,
  "mov-22" to R.drawable.passion_tale,
  "mov-23" to R.drawable.unlikely_harmony,
  "mov-24" to R.drawable.timeless_love_story,
  "mov-25" to R.drawable.pursuit_of_love,
  "mov-26" to R.drawable.futuristic_odyssey,
  "mov-27" to R.drawable.alien_encounters,
  "mov-28" to R.drawable.dystopian_survival,
  "mov-29" to R.drawable.tech_and_morality,
  "mov-30" to R.drawable.space_and_identity,
)
