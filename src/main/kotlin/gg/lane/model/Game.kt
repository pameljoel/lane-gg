package gg.lane.model

data class Game(
  val gameId: Long,
  val alliedTeam: List<Participant>,
  val ennemyTeam: List<Participant>
)
