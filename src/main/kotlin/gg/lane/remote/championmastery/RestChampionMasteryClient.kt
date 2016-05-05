package gg.lane.remote.championmastery

import gg.lane.model.Region
import gg.lane.remote.championmastery.dto.ChampionMasteryDTO
import rx.Observable

interface RestChampionMasteryClient {

  fun championMasteryBySummoner(id: Long, region: Region): Observable<List<ChampionMasteryDTO>>
}
