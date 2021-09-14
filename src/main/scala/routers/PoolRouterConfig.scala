package routers

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import routers.ManualRouter.Child

object PoolRouterConfig extends App {

  val system = ActorSystem("RoutersDemo", ConfigFactory.load().getConfig("routersDemo"))

  val poolActor = system.actorOf(FromConfig.props(Props[Child]), "poolActor")
      for (i <- 1 to 10) {
        poolActor ! s"[$i] Hello from the world"
      }
}
