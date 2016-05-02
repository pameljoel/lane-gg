package gg.lane.model

data class Game(
  val gameId: Long,
  val winningOdds: Int,
  val alliedTeam: List<Participant>,
  val ennemyTeam: List<Participant>
)
