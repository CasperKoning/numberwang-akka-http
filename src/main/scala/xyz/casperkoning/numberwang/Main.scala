package xyz.casperkoning
package numberwang

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent._
import scala.concurrent.duration._

object Main extends App {
  implicit val config = ConfigFactory.load()
  implicit val system = ActorSystem("numberwang", config)
  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val service = new NumberwangService(Settings(config))

  service.start()

  Runtime.getRuntime.addShutdownHook(new Thread(){
    override def run(): Unit = {
      service.stop()
      materializer.shutdown()
      Await.result(system.terminate(), 10.seconds)
    }
  })
}
