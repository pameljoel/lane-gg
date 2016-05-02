package gg.lane.mapper

import gg.lane.model.Participant
import gg.lane.model.Team
import gg.lane.remote.currentgame.dto.CurrentGameParticipantDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.stereotype.Component
import java.util.*

@Component
class ParticipantMapper{
  fun map(participant: CurrentGameParticipantDTO, champions: Map<Long, ChampionDTO>): Participant {
    val championName = champions[participant.championId]!!.name
    val championImage =  "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champions[participant.championId]!!.image.full.replace(".png", "_0.jpg")
    //FIXME
    val bestScore = "A+"
    val level = Random().nextInt(5)
    val progress =  Random().nextInt(100)
    val lastPlayed = "" + Random().nextInt(10) + "day(s)"
    return Participant(participant.summonerId, participant.summonerName, participant.championId, championName, championImage, Team.fromCode(participant.teamId), bestScore, level, progress, lastPlayed)
  }
}
