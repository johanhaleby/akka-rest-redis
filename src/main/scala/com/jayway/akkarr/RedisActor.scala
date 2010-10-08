package com.jayway.akkarr

import se.scalablesolutions.akka.actor.Transactor
import se.scalablesolutions.akka.persistence.redis.RedisStorage
import RedisActor._

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: Oct 8, 2010
 * Time: 7:27:56 PM
 * To change this template use File | Settings | File Templates.
 */

case class Write(key : String, value : String)
case class Read(key : String)

class RedisActor extends Transactor with RedisHelpers {
  val redisStorage = RedisStorage.getMap("stuff")


  def receive = {
    case Write(key, value) => redisStorage.put(key, value); self reply("<h1>OK</h1>")
    case Read(key) => val value : Array[Byte] = redisStorage.get(key).getOrElse(notFound(key)); self reply("<h1>%s</h1>".format(new String(value, "UTF-8")))
    case _ => self reply("<h1>Not found</h1>")
  }
}

object RedisActor {
  def notFound(key : String) = key + "not found".getBytes("UTF-8")
}