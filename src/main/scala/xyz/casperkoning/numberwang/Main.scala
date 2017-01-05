package xyz.casperkoning
package numberwang

object Main extends App {
  val service = new NumberwangService()
  service.start()
  Runtime.getRuntime.addShutdownHook(new Thread(){
    override def run(): Unit = service.stop()
  })
}
