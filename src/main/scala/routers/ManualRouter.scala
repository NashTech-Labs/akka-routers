package routers

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}

object ManualRouter extends App{

  class Parent extends Actor {
    private val child = for(i <- 1 to 5) yield {
      val child = context.actorOf(Props[Child], s"child_$i")
      context.watch(child)
      ActorRefRoutee(child)
    }

    private val router = Router(RoundRobinRoutingLogic(), child)

    override def receive: Receive = {
      case message =>
        router.route(message, sender())
    }
  }

  class Child extends Actor with ActorLogging {
    override def receive: Receive = {
      case message => log.info(message.toString)
    }
  }

  val system = ActorSystem("RoutersDemo")
  val master = system.actorOf(Props[Parent])

    for (i <- 1 to 10) {
      master ! s"[$i] Hello from the world"
    }

}
