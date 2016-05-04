package gg.lane.providers

import gg.lane.model.Region
import rx.Observable

interface ApiKeyProvider{
  fun regionalApiKey(region: Region): Observable<String>
  fun globalApiKey(): Observable<String>
}
