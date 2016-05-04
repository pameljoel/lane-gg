package gg.lane.remote;

import gg.lane.model.Region
import org.springframework.web.util.UriComponentsBuilder

abstract class RestRiotClient(val version: String) {

  fun uri(urlPath: String, region: Region, apiKey: String): UriComponentsBuilder {
    return UriComponentsBuilder.fromHttpUrl("https://" + region.name.toLowerCase() + ".api.pvp.net/api/lol/" + region.name.toLowerCase() + "/" + version + "/" + urlPath)
      .queryParam("api_key", apiKey)
  }
}
