package xyz.casperkoning
package numberwang

import akka.actor._
import akka.http.scaladsl.Http.ServerBinding
import akka.stream._
import akka.http.scaladsl._

import scala.concurrent._
import scala.concurrent.duration._

class NumberwangService(val settings: Settings)(implicit actorSystem: ActorSystem, executionContext: ExecutionContext, materializer: Materializer)
  extends ApiRoutes {
  private var serverBinding: Option[ServerBinding] = None

  def start() = {
    Http().bindAndHandle(
      handler = routes,
      interface = settings.Http.interface,
      port = settings.Http.port
    ) foreach {
      binding => serverBinding = Some(binding)
    }
  }

  def stop() = {
    serverBinding.foreach {b => Await.result(b.unbind(), 10.seconds) }
  }
}
