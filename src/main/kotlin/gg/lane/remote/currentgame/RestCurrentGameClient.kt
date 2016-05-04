package gg.lane.remote.currentgame

import gg.lane.model.Region
import gg.lane.remote.currentgame.dto.CurrentGameInfoDTO
import org.springframework.scheduling.annotation.AsyncResult
import rx.Observable

interface RestCurrentGameClient{

  fun gameBySummoner(id: Long, region: Region): Observable<CurrentGameInfoDTO?>

}
