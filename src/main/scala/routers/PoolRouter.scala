package routers

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import routers.ManualRouter.Child

object PoolRouter extends App {

  val system = ActorSystem("RoutersDemo")

  val simplePoolActor = system.actorOf(RoundRobinPool(5).props(Props[Child]), "simplePoolActor")
    for (i <- 1 to 10) {
      simplePoolActor ! s"[$i] Hello from the world"
    }

}
