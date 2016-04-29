package gg.lane.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController("/api/hello-world")
class HelloWorldController{

  @RequestMapping(method = arrayOf(GET))
  fun helloWorld(): String {
    return "hello world"
  }
}

