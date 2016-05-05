package gg.lane.service

import gg.lane.mapper.GameMapper
import gg.lane.model.Game
import gg.lane.model.Region
import gg.lane.model.Summoner
import gg.lane.remote.championmastery.RestChampionMasteryClient
import gg.lane.remote.championmastery.dto.ChampionMasteryDTO
import gg.lane.remote.currentgame.RestCurrentGameClient
import gg.lane.remote.staticdata.RestStaticDataClient
import gg.lane.remote.summoner.RestSummonerClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Observable

@Service
class SummonerServiceImpl @Autowired constructor(
  val restSummonerClient: RestSummonerClient,
  val restCurrentGameClient: RestCurrentGameClient,
  val restStaticDataClient: RestStaticDataClient,
  val restChampionMasteryClient: RestChampionMasteryClient,
  val gameMapper: GameMapper): SummonerService{

  companion object {
    val logger = LoggerFactory.getLogger(SummonerServiceImpl::class.java)
  }

  override fun summonersByName(name: String): Observable<Summoner> {
    val calls = Region.values()
      .map {region -> searchSummonerByNameAndRegion(name, region)}

    return Observable.merge(calls)
  }

  fun searchSummonerByNameAndRegion(name: String, region: Region): Observable<Summoner> {
    logger.info("Searching summoner $name from $region")

    return restSummonerClient.getSummonerByName(name, region)
        .filter { it != null }
        .map {
          logger.info("Completed summoner $name from $region")
          it!!
        }
        .map { it -> Summoner(it.id, it.name, it.profileIconId, it.revisionDate, it.summonerLevel, region) }

  }

  override fun gameBySummoner(id: Long, region: Region): Observable<Game> {
    val staticData = Observable.from(restStaticDataClient.champions())
    val gameAndMasteries = restCurrentGameClient.gameBySummoner(id, region)
      .filter { it != null }
      .map{ it!!}
      .flatMap{ game ->
        Observable.zip(
          this.masteriesBySummoners(game.participants.map { it.summonerId }, region),
          Observable.just(game),
          {masteries, game -> Pair(game, masteries)}
        )
      }

    return Observable.zip(
      staticData,
      gameAndMasteries,
      {staticData, gameWithMasteries -> gameMapper.map(gameWithMasteries.first, id, staticData, gameWithMasteries.second)}
    )
  }

  private fun masteriesBySummoners(ids: List<Long>,region: Region): Observable<Map<Long, List<ChampionMasteryDTO>>> {
    val masteries = ids.map { summonerId ->
      restChampionMasteryClient.championMasteryBySummoner(summonerId, region).map { it -> Pair(summonerId, it) }
    }
    return Observable.merge(masteries).toList().map { it.toMap() }
  }

}
