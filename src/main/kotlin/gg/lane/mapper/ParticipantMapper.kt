package gg.lane.mapper

import gg.lane.model.ChampionMastery
import gg.lane.model.Participant
import gg.lane.model.Team
import gg.lane.remote.championmastery.dto.ChampionMasteryDTO
import gg.lane.remote.currentgame.dto.CurrentGameParticipantDTO
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class ParticipantMapper @Autowired constructor(val championMapper: ChampionMapper){

  fun map(participant: CurrentGameParticipantDTO, champions: Map<Long, ChampionDTO>, masteries: List<ChampionMasteryDTO>): Participant {
    val champion = championMapper.map(champions[participant.championId]!!)
    val championMastery = masteries.find { it.championId == champion.id }
    val mostPlayed = masteries.sortedBy { it.championPoints }.reversed().take(5).map { championMapper.map(champions[it.championId]!!) }

    return Participant(participant.summonerId, participant.summonerName, Team.fromCode(participant.teamId), false, false, champion, mastery(championMastery), mostPlayed)
  }

  fun mastery(mastery: ChampionMasteryDTO?): ChampionMastery {
    if (mastery!= null) {
      val bestScore: String? = mastery.highestGrade
      val level = mastery.championLevel
      val score =  mastery.championPoints
      val lastPlayed = Instant.ofEpochMilli(mastery.lastPlayTime)
      val duration = Duration.between(lastPlayed, Instant.now())

      return  ChampionMastery(bestScore ?: "N/A", level, score, "${duration.toDays()} day(s) ago")
    }
    return ChampionMastery("N/A", 0, 0, "Unknown")
  }
}
