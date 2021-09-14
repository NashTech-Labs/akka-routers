package routers

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import routers.ManualRouter.Child

object GroupRouterConfig extends App {

  val system = ActorSystem("RoutersDemo", ConfigFactory.load().getConfig("routersDemo"))

  (1 to 5).map(i => system.actorOf(Props[Child], s"child_$i")).toList

  val groupParent = system.actorOf(FromConfig.props(), "groupActor")
  for (i <- 1 to 10) {
    groupParent ! s"[$i] Hello from the world"
  }
}
