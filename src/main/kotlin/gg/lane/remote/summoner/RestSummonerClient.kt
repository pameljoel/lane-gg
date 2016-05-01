package gg.lane.remote.summoner;

import gg.lane.model.Region
import gg.lane.remote.summoner.dto.SummonerDTO
import org.springframework.scheduling.annotation.AsyncResult

interface RestSummonerClient {

    fun getSummonerByName(name: String, region: Region): AsyncResult<SummonerDTO?>

}
