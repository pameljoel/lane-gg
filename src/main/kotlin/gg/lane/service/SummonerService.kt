package gg.lane.service

import gg.lane.model.Game
import gg.lane.model.Region
import gg.lane.model.Summoner
import rx.Observable

interface SummonerService{
  fun summonersByName(name: String): Observable<Summoner>

  fun gameBySummoner(id: Long, region: Region): Observable<Game>

}
