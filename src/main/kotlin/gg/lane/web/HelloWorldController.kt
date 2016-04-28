package gg.lane.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController{

  @RequestMapping(method = arrayOf(GET),path = arrayOf("/hello-world"))
  fun helloWorld(): String {
    return "hello world"
  }
}

