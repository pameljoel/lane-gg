package gg.lane.mapper

import gg.lane.model.Participant
import gg.lane.remote.currentgame.dto.CurrentGameParticipantDTO
import org.springframework.stereotype.Component

@Component
class ParticipantMapper{
  fun map(participant: CurrentGameParticipantDTO): Participant {
    return Participant(participant.summonerId, participant.summonerName, participant.championId)
  }
}
