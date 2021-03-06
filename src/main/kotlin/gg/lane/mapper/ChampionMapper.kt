package gg.lane.mapper

import gg.lane.model.Champion
import gg.lane.remote.staticdata.dto.ChampionDTO
import gg.lane.service.GameVersionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ChampionMapper @Autowired constructor(val gameVersionService: GameVersionService){

  fun map(champion: ChampionDTO): Champion {
    val championImage = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champion.image.full.replace(".png", "_0.jpg")
    val championIcon = "https://ddragon.leagueoflegends.com/cdn/"+gameVersionService.getCurrentVersion()+"/img/champion/" + champion.image.full

    return Champion(champion.id, champion.name, championImage, championIcon)
  }

}
