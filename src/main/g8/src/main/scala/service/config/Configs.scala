package service.config

import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._
import scala.collection.mutable

object Configs {
  private val config = ConfigFactory.defaultApplication()

  lazy val port: Int = config.getInt("port")
}
