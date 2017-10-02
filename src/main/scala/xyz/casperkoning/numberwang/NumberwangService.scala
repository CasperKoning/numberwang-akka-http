package xyz.casperkoning
package numberwang

import akka.actor._
import akka.http.scaladsl.Http.ServerBinding
import akka.stream._
import akka.http.scaladsl._
import com.typesafe.config._

import scala.concurrent._
import scala.concurrent.duration._

class NumberwangService(val config: Config)(implicit actorSystem: ActorSystem, executionContext: ExecutionContext, materializer: Materializer)
  extends ApiRoutes {
  private var serverBinding: Option[ServerBinding] = None

  def start() = {
    Http().bindAndHandle(
      handler = routes,
      interface = config.getString("http.interface"),
      port = config.getInt("http.port")
    ) onSuccess {
      case binding => serverBinding = Some(binding)
    }
  }

  def stop() = {
    serverBinding.foreach {b => Await.result(b.unbind(), 10.seconds) }
  }
}
