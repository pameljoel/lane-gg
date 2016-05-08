package gg.lane.mapper

import gg.lane.model.Game
import gg.lane.model.Participant
import gg.lane.model.Team
import gg.lane.remote.championmastery.dto.ChampionMasteryDTO
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GameMapper @Autowired constructor(val participantMapper: ParticipantMapper){

  fun map(game: CurrentGameInfoDTO, summonerId: Long, champions: Map<Long, ChampionDTO>, masteries: Map<Long, List<ChampionMasteryDTO>>): Game {
    val summonerTeam = game.participants.find { it.summonerId == summonerId }!!.teamId
    val mappedSummoners = game.participants.map { participantMapper.map(it, champions, masteries[it.summonerId]!!) }.partition { it.team == Team.fromCode(summonerTeam) }
    val allied = mappedSummoners.first
    val ennemies = mappedSummoners.second
    val alliedTotal = allied.sumBy { it.championMastery.score }
    val ennemyTotal = ennemies.sumBy { it.championMastery.score }
    val winningOds = (100 * alliedTotal) / (alliedTotal + ennemyTotal)

    return Game(game.gameId, winningOds, updateBestAndWorst(allied), updateBestAndWorst(ennemies))
  }

  fun updateBestAndWorst(participants: List<Participant>): List<Participant> {
    val sorted = participants.sortedBy { it.championMastery.score}
    val best = sorted.last()
    val worst = sorted.first()
    return participants.map { p ->
      if (p == best){
        p.copy(biggestThreat = true)
      }
      else if (p == worst){
        p.copy(weakestLink = true)
      }
      else{
        p
      }
    }
  }


}
