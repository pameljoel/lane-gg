package gg.lane.mapper

import gg.lane.model.Game
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GameMapper @Autowired constructor(val participantMapper: ParticipantMapper){

  fun map(game: CurrentGameInfoDTO, champions: Map<Long, ChampionDTO>): Game {
    return Game(game.gameId, game.participants.map { participantMapper.map(it, champions) })
  }

}
