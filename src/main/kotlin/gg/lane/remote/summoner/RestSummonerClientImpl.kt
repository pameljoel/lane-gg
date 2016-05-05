package gg.lane.remote.summoner;

import gg.lane.model.Region
import gg.lane.providers.ApiKeyProvider
import gg.lane.remote.RestRiotClient
import gg.lane.remote.summoner.dto.SummonerDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations
import rx.Observable

@Component
class RestSummonerClientImpl @Autowired constructor(val apiKeyProvider: ApiKeyProvider, val restOperations: RestOperations): RestRiotClient("v1.4"), RestSummonerClient {
  companion object {
    val logger = LoggerFactory.getLogger(RestSummonerClientImpl::class.java)
  }

  override fun getSummonerByName(name: String, region: Region): Observable<SummonerDTO?> {
    return apiKeyProvider.regionalApiKey(region).map { apiKey ->
      val uri = uri("/summoner/by-name/" + name, region, apiKey).toUriString()
      logger.info("Started " + uri)
      val resp = restOperations.exchange(uri, HttpMethod.GET, null, object : ParameterizedTypeReference<Map<String, SummonerDTO>>() {}).body[name.toLowerCase().replace(" ", "")]
      logger.info("Started " + uri)
      resp
    }.onErrorReturn { error ->
      if (error is HttpClientErrorException){
        logger.warn("Error ${error.statusCode} for ${uri("/summoner/by-name/" + name, region, "").toUriString()}")
        null
      }
      else {
        logger.error("Error during summonerByName $name, $region", error)
        throw error
      }
    }
  }
}
