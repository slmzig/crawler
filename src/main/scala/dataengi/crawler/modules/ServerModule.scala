package dataengi.crawler.modules

import com.typesafe.config.{Config, ConfigFactory}

trait ServerModule {
  lazy val config: Config = ConfigFactory.load()
}

