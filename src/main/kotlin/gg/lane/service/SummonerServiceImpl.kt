package gg.lane.service

import gg.lane.model.Region
import gg.lane.model.Summoner
import gg.lane.remote.summoner.RestSummonerClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Observable
import rx.schedulers.Schedulers

@Service
class SummonerServiceImpl @Autowired constructor(val restSummonerClient: RestSummonerClient): SummonerService{
  companion object {
    val logger = LoggerFactory.getLogger(SummonerServiceImpl::class.java)
  }

  override fun summonersByName(name: String): Observable<Summoner> {
    val calls = Region.values()
      .map {region -> searchSummonerByNameAndRegion(name, region)}

    return Observable.merge(calls).filter { it != null}.map { it!! }
  }

  fun searchSummonerByNameAndRegion(name: String, region: Region): Observable<Summoner?> {
    logger.info("Searching summoner $name from $region")

    return Observable.defer {
      Observable
        .from(restSummonerClient.getSummonerByName(name, region))
        .filter { it != null }
        .map {
          logger.info("Completed summoner $name from $region")
          it!!
        }
        .map { it -> Summoner(it.id, it.name, it.profileIconId, it.revisionDate, it.summonerLevel, region) }
    }.subscribeOn(Schedulers.io())
  }

}
