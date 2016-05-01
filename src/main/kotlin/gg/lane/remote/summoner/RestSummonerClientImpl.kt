package gg.lane.remote.summoner;

import gg.lane.model.Region
import gg.lane.remote.RestRiotClient
import gg.lane.remote.summoner.dto.SummonerDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Component
import org.springframework.web.client.AsyncRestOperations
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations

@Component
class RestSummonerClientImpl @Autowired constructor(@Value("\${riot.api.key}") override val apiKey: String, val restOperations: RestOperations): RestRiotClient("v1.4", apiKey), RestSummonerClient {
  companion object {
    val logger = LoggerFactory.getLogger(RestSummonerClientImpl::class.java)
  }


  @Async
  override fun getSummonerByName(name: String, region: Region): AsyncResult<SummonerDTO?> {
    val uri = uri("/summoner/by-name/" + name, region)

    try{
      return AsyncResult(restOperations.exchange(uri.toUriString(), HttpMethod.GET, null, object : ParameterizedTypeReference<Map<String, SummonerDTO>>() {}).body[name.toLowerCase().replace(" ", "")])
    } catch (error: HttpClientErrorException){
      logger.warn("Error ${error.statusCode} for ${uri.build()}")
      return AsyncResult(null)
    }
  }
}
