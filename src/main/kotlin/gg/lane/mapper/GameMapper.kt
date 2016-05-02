package gg.lane.mapper

import gg.lane.model.Game
import gg.lane.model.Team
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GameMapper @Autowired constructor(val participantMapper: ParticipantMapper){

  fun map(game: CurrentGameInfoDTO, summonerId: Long, champions: Map<Long, ChampionDTO>): Game {
    val summonerTeam = game.participants.find { it.summonerId == summonerId }!!.teamId
    val mappedSummoners = game.participants.map { participantMapper.map(it, champions) }.partition { it.team == Team.fromCode(summonerTeam) }
    return Game(game.gameId, mappedSummoners.first, mappedSummoners.second)
  }

}
