package xyz.casperkoning.numberwang

import com.typesafe.config.{Config, ConfigFactory}

case class Settings(config: Config = ConfigFactory.load()) {
  object Http {
    private val httpConfig = config.getConfig("http")
    val interface = httpConfig.getString("interface")
    val port = httpConfig.getInt("port")
  }
}
