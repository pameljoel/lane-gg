package gg.lane.web

import com.fasterxml.jackson.databind.ObjectMapper
import gg.lane.model.Region
import gg.lane.model.Summoner
import gg.lane.remote.summoner.RestSummonerClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/summoner")
open class SummonerController @Autowired constructor(val restSummonerClient: RestSummonerClient, val objectMapper: ObjectMapper){

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{name}"))
  fun summonerByName(@PathVariable name: String): List<Summoner> {
    val summoners = mutableListOf<Summoner>()
    for (region in Region.values()){
      val summoner =  restSummonerClient.getSummonerByName(name, region)
      if (summoner != null){
        summoners.add(Summoner(summoner.id, summoner.name, summoner.profileIconId, summoner.revisionDate, summoner.summonerLevel, region))
      }
    }
    return summoners
  }
}
