package gg.lane.remote.staticdata

import gg.lane.model.Region
import gg.lane.remote.staticdata.dto.ChampionDTO
import gg.lane.remote.staticdata.dto.DataDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Component
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder

@Component
class RestStaticDataClientImpl @Autowired constructor(@Value("\${riot.api.key}") val apiKey: String, val restOperations: RestOperations): RestStaticDataClient {

  override fun champions(): AsyncResult<Map<Long, ChampionDTO>> {
    //https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion?dataById=true&champData=image&api_key=205d0bd9-6da5-46f6-a1d2-6d6c6747ad54

    val uri =  UriComponentsBuilder.fromHttpUrl("https://global.api.pvp.net/api/lol/static-data/" + Region.EUW.name.toLowerCase() + "/v1.2/champion")
      .queryParam("api_key", apiKey)
      .queryParam("dataById", true)
      .queryParam("champData", "image")

    return AsyncResult(
      restOperations.getForObject(uri.toUriString(), DataDTO::class.java)
        .data
        .map { entry -> Pair(entry.key.toLong(), entry.value)}
        .toMap())
  }

}
