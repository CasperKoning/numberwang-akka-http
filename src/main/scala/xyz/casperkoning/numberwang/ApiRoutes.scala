package xyz.casperkoning
package numberwang

import scala.concurrent._
import scala.util._

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import scala.xml._

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
    } ~
    path("post_xml") {
      post {
        entity(as[NodeSeq]) { xml ⇒
          complete(s"That was XML: $xml")
        }
      }
    } ~ 
    path("post_promise") {
      post {
        onComplete(doPromiseWork()) {
          case Success(_) ⇒ complete("That was an asynchronous POST involving promises")
          case Failure(ex) ⇒ complete("We failed to process your asychronous POST involving promises")
        }
      }
    } ~
    path("post_custom_response") {
      post {
        complete(HttpResponse(StatusCodes.OK, entity = "This is a custom response"))
      }
    }

  private def doAsynchronousWork()(implicit ec: ExecutionContext): Future[Unit] = Future {
    (): Unit
  }

  private def doPromiseWork()(implicit ec: ExecutionContext): Future[Unit] = {
    val p = Promise[Unit]
    p.success((): Unit)
    p.future
  }
}
