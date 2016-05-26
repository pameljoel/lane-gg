package gg.lane.service

import gg.lane.remote.staticdata.RestStaticDataClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class GameVersionServiceImpl @Autowired constructor(val restStaticDataClient: RestStaticDataClient): GameVersionService {

  companion object {
    val logger = LoggerFactory.getLogger(GameVersionServiceImpl::class.java)
  }

  private var currentVersion = "6.10.1"

  override fun getCurrentVersion(): String {
    return currentVersion
  }

  @Scheduled(fixedRate = 3600000)
  fun updateGameVersion() {
    restStaticDataClient.gameVersions().addCallback({ versions ->
      currentVersion = versions.first()
      logger.info("Updated game version with value $currentVersion")
    }, { error ->
      logger.error("Could not retrieve game version", error)
    }
    )
  }

}
