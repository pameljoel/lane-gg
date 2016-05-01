package gg.lane.model

data class Summoner(
  val id:Long,
  val name: String,
  val profileIconId: Int,
  val revisionDate: Long,
  val summonerLevel: Int,
  val region: Region
)
