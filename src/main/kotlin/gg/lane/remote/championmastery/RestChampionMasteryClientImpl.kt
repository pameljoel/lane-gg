package gg.lane.remote.championmastery

import gg.lane.model.Region
import gg.lane.providers.ApiKeyProvider
import gg.lane.remote.championmastery.dto.ChampionMasteryDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder
import rx.Observable

@Component
class RestChampionMasteryClientImpl @Autowired constructor(val apiKeyProvider: ApiKeyProvider, val restOperations: RestOperations): RestChampionMasteryClient {
  companion object {
    val logger = LoggerFactory.getLogger(RestChampionMasteryClientImpl::class.java)
  }

  override fun championMasteryBySummoner(id: Long, region: Region): Observable<List<ChampionMasteryDTO>> {
    val builder = UriComponentsBuilder.fromHttpUrl("https://" + region.name.toLowerCase() + ".api.pvp.net/championmastery/location/" + region.spectatorRegion + "/player/" + id + "/champions")

    return apiKeyProvider.regionalApiKey(region)
      .map { apiKey ->
        logger.info("Started " + builder.toUriString())
        val resp = restOperations.exchange(builder.queryParam("api_key", apiKey).toUriString(), HttpMethod.GET, null, object : ParameterizedTypeReference<List<ChampionMasteryDTO>>() {}).body
        logger.info("Completed " + builder.toUriString())
        resp
      }
      .onErrorReturn { error ->
        if (error is HttpClientErrorException) {
          logger.warn("Error ${error.statusCode} for ${builder.toUriString()}")
          null
        } else {
          logger.error("Error during championMasteryBySummoner $id, $region", error)
          throw error
        }
      }
  }

}
