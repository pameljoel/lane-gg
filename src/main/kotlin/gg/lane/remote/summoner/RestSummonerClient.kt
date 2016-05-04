package gg.lane.remote.summoner;

import gg.lane.model.Region
import gg.lane.remote.summoner.dto.SummonerDTO
import rx.Observable

interface RestSummonerClient {

    fun getSummonerByName(name: String, region: Region): Observable<SummonerDTO?>

}
