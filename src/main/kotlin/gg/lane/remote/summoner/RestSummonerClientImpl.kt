package gg.lane.remote.summoner;

import gg.lane.model.Region
import gg.lane.remote.RestRiotClient
import gg.lane.remote.summoner.dto.SummonerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestOperations

@Component
class RestSummonerClientImpl @Autowired constructor(@Value("\${riot.api.key}") override val apiKey: String, val restOperations: RestOperations): RestRiotClient("v1.4", apiKey), RestSummonerClient {

  override fun getSummonerByName(name: String, region: Region): SummonerDTO? {
    val uri = uri("/summoner/by-name/" + name, region)

    return restOperations.exchange(uri.toUriString(), HttpMethod.GET, null, object: ParameterizedTypeReference<Map<String, SummonerDTO>>(){}).body[name.toLowerCase().replace(" ", "")]
  }
}
