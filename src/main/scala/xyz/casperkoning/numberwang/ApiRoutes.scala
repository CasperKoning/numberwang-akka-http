package xyz.casperkoning
package numberwang

import akka.http.scaladsl.server.Directives._

trait ApiRoutes {
  val routes = get {
    complete {
      "That is Numberwang!"
    }
  }
}
