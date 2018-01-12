package xyz.casperkoning.numberwang

import org.scalatest._

class UtilSpec extends FlatSpec with Matchers {
  "Util" should "greet people" in {
    Util.constructGreeting("Tester") shouldBe "Hello Tester"
  }
}