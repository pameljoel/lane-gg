package gg.lane.config

import gg.lane.model.GameVersion
import gg.lane.remote.staticdata.RestStaticDataClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
open class GlobalConfig {

  val logger = LoggerFactory.getLogger(GlobalConfig::class.java)

  val gameVersion = GameVersion("6.10.1")

  @Autowired
  var restStaticDataClient: RestStaticDataClient? = null

  @Bean open fun restOperations(): RestOperations = RestTemplate()

  @Bean open fun gameVersion(): GameVersion {
    return gameVersion
  }

  @Scheduled(fixedRate = 3600000)
  open fun updateGameVersion() {
    restStaticDataClient?.gameVersions()?.addCallback({versions ->
        gameVersion.update(versions.first())
        logger.info("Updated game version with value ${gameVersion.currentVersion}")
      },{error ->
        logger.error("Could not retrieve game version", error)
      }
    )
  }

  /*
  @Bean open fun objectMapper(): ObjectMapper {
    val mapper:ObjectMapper = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule()).build()
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    return mapper
  }*/
}
