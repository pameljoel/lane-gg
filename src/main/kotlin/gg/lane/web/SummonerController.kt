package gg.lane.web

import com.fasterxml.jackson.databind.ObjectMapper
import gg.lane.model.Region
import gg.lane.remote.summoner.RestSummonerClient
import gg.lane.remote.summoner.dto.SummonerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/summoner")
open class SummonerController @Autowired constructor(val restSummonerClient: RestSummonerClient, val objectMapper: ObjectMapper){

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{region}/{name}"))
  fun summonerByName(@PathVariable region: Region, @PathVariable name: String): SummonerDTO? {
    return restSummonerClient.getSummonerByName(name, region)
  }
}
