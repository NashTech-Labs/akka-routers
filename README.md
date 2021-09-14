# Akka Routers

- The router is an actor that send messages in an efficient way to the destination actor known as a route.
- Different routers use different strategies to send or route messages or tasks.
- Routers can be used inside or outside of an actor.
- You can manage the routes yourself or use a self-contained router actor with configuration capabilities.

## Prerequisites
- Scala Build Tool(SBT), version 1.4.7
- Scala, version 2.12.12

## Commands for project execution
- sbt compile - To compile the project
- sbt clean - Delete all generated files
