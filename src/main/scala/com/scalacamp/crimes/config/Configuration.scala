package com.scalacamp.crimes.config

class Configuration(val numberOfLocations: Int)

object Configuration {
  def fromMap(properties: Map[String, String]) = new Configuration(properties("application.stats.number_of_locations").toInt)
}
