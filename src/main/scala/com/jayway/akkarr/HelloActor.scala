package com.jayway.akkarr

import se.scalablesolutions.akka.actor.Actor

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: Oct 8, 2010
 * Time: 7:27:56 PM
 * To change this template use File | Settings | File Templates.
 */

class HelloActor extends Actor {
	def receive = {
		case "sayHello" => self reply(<h1>Hello, World from actor</h1>.toString)
	}
}