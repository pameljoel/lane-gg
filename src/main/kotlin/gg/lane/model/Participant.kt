package gg.lane.model

data class Participant(
  val summonerId: Long,
  val summonerName: String,
  val team: Team,
  val biggestThreat: Boolean,
  val weakestLink: Boolean,
  val champion: Champion,
  val championMastery: ChampionMastery,
  val mostPlayed: List<Champion>
)
