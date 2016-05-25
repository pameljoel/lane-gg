package gg.lane.remote.staticdata

import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.scheduling.annotation.AsyncResult

interface RestStaticDataClient {

  fun champions(): AsyncResult<Map<Long, ChampionDTO>>

  fun gameVersions(): AsyncResult<List<String>>
}
