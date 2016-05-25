package gg.lane

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class LaneGgApplication

fun main(args: Array<String>) {
    SpringApplication.run(LaneGgApplication::class.java, *args)
}
