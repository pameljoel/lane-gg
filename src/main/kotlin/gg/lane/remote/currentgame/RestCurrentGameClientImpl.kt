package gg.lane.remote.currentgame

import gg.lane.model.Region
import gg.lane.remote.RestRiotClient
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import gg.lane.remote.summoner.RestSummonerClientImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder

@Component
class RestCurrentGameClientImpl @Autowired constructor(@Value("\${riot.api.key}") override val apiKey: String, val restOperations: RestOperations): RestRiotClient("v1.4", apiKey), RestCurrentGameClient{

  @Async
  override fun gameBySummoner(id: Long, region: Region): AsyncResult<CurrentGameInfoDTO?> {
    val uri = UriComponentsBuilder.fromHttpUrl("https://" + region.name.toLowerCase() + ".api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/" + region.spectatorRegion + "/" + id)
      .queryParam("api_key", apiKey)

    try{
      return AsyncResult(restOperations.getForObject(uri.toUriString(), CurrentGameInfoDTO::class.java));
    } catch (error: HttpClientErrorException){
      RestSummonerClientImpl.logger.warn("Error ${error.statusCode} for ${uri.build()}")
      return AsyncResult(null)
    }
  }
}
