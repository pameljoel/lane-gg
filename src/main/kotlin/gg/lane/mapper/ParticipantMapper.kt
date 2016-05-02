package gg.lane.mapper

import gg.lane.model.ChampionMastery
import gg.lane.model.Participant
import gg.lane.model.Team
import gg.lane.remote.currentgame.dto.CurrentGameParticipantDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ParticipantMapper @Autowired constructor(val championMapper: ChampionMapper){

  fun map(participant: CurrentGameParticipantDTO, champions: Map<Long, ChampionDTO>): Participant {
    val champion = championMapper.map(champions[participant.championId]!!)
    //FIXME
    val mostPlayed = champions.toList().map{it.second}.map { championMapper.map(it) }.take(5)
    val biggestThreat = Random().nextBoolean()
    val weakestLink = !biggestThreat && Random().nextBoolean()
    val bestScore = "A+"
    val level = Random().nextInt(5)
    val progress =  Random().nextInt(100)
    val lastPlayed = "" + Random().nextInt(10) + " day(s)"
    val mastery = ChampionMastery(bestScore, level, progress, lastPlayed)
    return Participant(participant.summonerId, participant.summonerName, Team.fromCode(participant.teamId), biggestThreat, weakestLink, champion, mastery, mostPlayed)
  }
}
