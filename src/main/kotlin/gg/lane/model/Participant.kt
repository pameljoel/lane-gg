package gg.lane.model

data class Participant(
  val summonerId: Long,
  val summonerName: String,
  val championId: Long,
  val championName: String,
  val championImage: String,
  val team: Team,
  val bestScore: String,
  val level: Int,
  val progress: Int,
  val lastPlayed: String
)
