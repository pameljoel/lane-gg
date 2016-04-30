package gg.lane.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
open class GlobalConfig{

  @Bean open fun restOperations(): RestOperations = RestTemplate()

  /*
  @Bean open fun objectMapper(): ObjectMapper {
    val mapper:ObjectMapper = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule()).build()
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    return mapper
  }*/
}
