package xyz.casperkoning
package numberwang

import akka.actor._
import akka.stream._
import akka.http.scaladsl._
import com.typesafe.config._

class NumberwangService(val config: Config = ConfigFactory.load())
  extends ApiRoutes {

  def start() = {
    implicit val system = ActorSystem("numberwang", config)
    implicit val materializer = ActorMaterializer()

    Http().bindAndHandle(
      handler = routes,
      interface = config.getString("http.interface"),
      port = config.getInt("http.port")
    )
  }

  def stop() = {
    // Unbind
    // Terminate ActorSystem
  }
}
