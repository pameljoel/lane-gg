package gg.lane.remote;

import gg.lane.model.Region
import org.springframework.web.util.UriComponentsBuilder

interface RestRiotClient {

  fun version(): String

  fun apiKey(): String

  fun uri(urlPath: String, region: Region): UriComponentsBuilder {
    return UriComponentsBuilder.fromHttpUrl("https://" + region.name.toLowerCase() + ".api.pvp.net/api/lol/" + region.name.toLowerCase() + "/" + version() + "/" + urlPath)
      .queryParam("api_key", apiKey())
  }
}
