package gg.lane.remote.currentgame

import gg.lane.model.Region
import gg.lane.providers.ApiKeyProvider
import gg.lane.remote.RestRiotClient
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import gg.lane.remote.summoner.RestSummonerClientImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder
import rx.Observable
import rx.schedulers.Schedulers

@Component
class RestCurrentGameClientImpl @Autowired constructor(val apiKeyProvider: ApiKeyProvider, val restOperations: RestOperations): RestRiotClient("v1.4"), RestCurrentGameClient{
  companion object {
    val logger = LoggerFactory.getLogger(RestSummonerClientImpl::class.java)
  }

  override fun gameBySummoner(id: Long, region: Region): Observable<CurrentGameInfoDTO?> {
    val builder = UriComponentsBuilder.fromHttpUrl("https://" + region.name.toLowerCase() + ".api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/" + region.spectatorRegion + "/" + id)

    return apiKeyProvider.regionalApiKey(region)
      .map { apiKey ->
        restOperations.getForObject(builder.queryParam("api_key", apiKey).toUriString(), CurrentGameInfoDTO::class.java)
      }
      .onErrorReturn { error ->
        if (error is HttpClientErrorException) {
          logger.warn("Error ${error.statusCode} for ${builder.toUriString()}")
          null
        } else {
          logger.error("Error during gameBySummoner $id, $region", error)
          throw error
        }
      }.limit(1)
  }
}
