package routers

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinGroup
import routers.ManualRouter.Child

object GroupRouter extends App {

  val system = ActorSystem("RoutersDemo")

  val childList = (1 to 5).map(i => system.actorOf(Props[Child], s"child_$i")).toList

  val childPaths = childList.map(childRef => childRef.path.toString)

  val groupParent = system.actorOf(RoundRobinGroup(childPaths).props())
  for (i <- 1 to 10) {
    groupParent ! s"[$i] Hello from the world"
  }
}
