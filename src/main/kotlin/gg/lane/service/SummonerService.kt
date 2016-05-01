package gg.lane.service

import gg.lane.model.Summoner
import rx.Observable

interface SummonerService{
  fun summonersByName(name: String): Observable<Summoner>

}
