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
import org.springframework.web.context.request.async.DeferredResult


@RestController
@RequestMapping("/api/summoner")
open class SummonerController @Autowired constructor(val summonerService: SummonerService){

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{name}"))
  fun summonerByName(@PathVariable name: String): DeferredResult<List<Summoner>> {
    val res = DeferredResult<List<Summoner>>()
    summonerService.summonersByName(name).toList().subscribe({ res.setResult(it)}, {res.setErrorResult(it)})
    return res
  }

  @RequestMapping(method = arrayOf(RequestMethod.GET), path = arrayOf("/{region}/{id}/match"))
  fun summonerMatch(@PathVariable region: Region, @PathVariable id: Long): DeferredResult<Game?> {
    val res = DeferredResult<Game?>()
    summonerService.gameBySummoner(id, region).subscribe({ res.setResult(it)}, {res.setErrorResult(it)}, {if (!res.isSetOrExpired) {throw RuntimeException()}}) //FIXME
    return res
  }
}
