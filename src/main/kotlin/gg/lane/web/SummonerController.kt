package gg.lane.web

import gg.lane.model.Game
import gg.lane.model.Region
import gg.lane.model.Summoner
import gg.lane.service.SummonerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/summoner")
open class SummonerController @Autowired constructor(val summonerService: SummonerService){

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{name}"))
  fun summonerByName(@PathVariable name: String): List<Summoner> {
    return summonerService.summonersByName(name).toList().toBlocking().first()
  }

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{region}/{id}/match"))
  fun summonerMatch(@PathVariable region: Region, @PathVariable id: Long): Game? {
    return summonerService.gameBySummoner(id, region).toBlocking().first()
  }
}
