package gg.lane.providers

import gg.lane.model.Region
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import rx.Observable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

@Component
class DevelopementRateLimitedApiKeyProvider @Autowired constructor(@Value("\${riot.api.key}") private val apiKey: String): ApiKeyProvider {

  companion object{
    private data class ApiKeyValue(
      val key: String,
      val flag: AtomicBoolean
    )
  }

  private val observables = Region.values().map { Pair(it, buildRateLimited()) }.toMap()

  private fun buildRateLimited(): Observable<ApiKeyValue> {
    return Observable.interval(1200, TimeUnit.MILLISECONDS).map { (ApiKeyValue(apiKey, AtomicBoolean(false))) }.publish().autoConnect(0)

    /*Observable.merge(IntRange(1, 10).map {
      Observable.concat(Observable.just(-1L), Observable.interval(10, TimeUnit.SECONDS))
        .map { (ApiKeyValue(apiKey, AtomicBoolean(false))) }.publish().autoConnect()
    }, 10).publish().autoConnect()*/
  }

  override fun regionalApiKey(region: Region): Observable<String> {
    return observables[region]!!.filter{it.flag.compareAndSet(false, true)}.map { it.key }.limit(1);
  }

  override fun globalApiKey(): Observable<String> {
    return  Observable.just(apiKey).repeat()
  }
}
