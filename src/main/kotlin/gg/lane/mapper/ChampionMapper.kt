package gg.lane.mapper

import gg.lane.model.Champion
import gg.lane.remote.staticdata.dto.ChampionDTO
import org.springframework.stereotype.Component

@Component
class ChampionMapper{

  fun map(champion: ChampionDTO): Champion {
    val championImage = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champion.image.full.replace(".png", "_0.jpg")
    val championIcon = "https://ddragon.leagueoflegends.com/cdn/6.9.1/img/champion/" + champion.image.full

    return Champion(champion.id, champion.name, championImage, championIcon)
  }

}
