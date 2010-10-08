package com.jayway.akkarr

import org.scalatra.ScalatraServlet
import se.scalablesolutions.akka.actor.Actor._

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: Oct 8, 2010
 * Time: 6:42:09 PM
 * To change this template use File | Settings | File Templates.
 */

class TheServlet extends ScalatraServlet {

  // send a text/html content type back each time
  before {
    contentType = "text/html"
  }

  get("/helloAkka") {
    val helloActor = actorOf[HelloActor].start
    val hello =  (helloActor !! "sayHello").getOrElse("couldn't say hello")
    helloActor.stop
    hello
  }

   get("/redis/put/:key/:value") {
    val redisActor = actorOf[RedisActor].start
    val result =  (redisActor !! Write({params("key")}, {params("value")}) ).getOrElse("Failed to put")
    redisActor.stop
    result
  }

    get("/redis/get/:key/") {
    val redisActor = actorOf[RedisActor].start
    val value =  (redisActor !! Read({params("key")})).getOrElse("Failed to get")
    redisActor.stop
    value
  }

  // parse matching requests, saving things prefixed with ':' as params
  get("/date/:year/:month/:day") {
    <ul>
      <li>Year: {params("year")}</li>
      <li>Month: {params("month")}</li>
      <li>Day: {params("day")}</li>
    </ul>
  }

  // handle POSTs from the form generated above
  post("/post") {
    <h1>You posted: {params("submission")}</h1>
  }
  
  notFound {
    response.setStatus(404)
    "Not found"
  }
}