package xyz.casperkoning
package numberwang

import scala.concurrent._
import scala.util._

import akka.http.scaladsl.server.Directives._

trait ApiRoutes {
  def routes()(implicit ec: ExecutionContext) = 
    path("get") {
     get {
      complete {
        "That was a GET"
      }
     }
    } ~
    path("post_sync") {
      post {
        complete {
          "That was a synchronous POST"
        }
      }
    } ~ 
    path("post_async") {
      post {
        onComplete(doAsynchronousWork()) {
          case Success(_) ⇒ complete("That was an asynchronous POST")
          case Failure(ex) ⇒ complete("We failed to process your asychronous POST")
        }
      }
    }

  private def doAsynchronousWork()(implicit ec: ExecutionContext): Future[Unit] = Future {
    (): Unit
  }
}
