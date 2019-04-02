package com.scalacamp.crimes

import java.io.File
import java.util.Properties

import com.scalacamp.crimes.config.Configuration

import scala.io.Source._
import scala.collection.JavaConverters._
import com.scalacamp.crimes.domain.CrimeType
import com.scalacamp.crimes.reader.IncidentCsvReader
import com.scalacamp.crimes.stats.CrimeStatistic

/**
  * The main executable class of application
  */
object CrimeRankingApp extends App {
  if (args.length == 0) {
    Console.err.println("Path to directory with csvs wasn't specified")
    sys.exit(1)
  }

  val configuration: Configuration = readConfiguration

  val incidents = getListOfFiles(args(0))
    .flatMap(filePath => new IncidentCsvReader(filePath).readIncidents())
      .filter(!_.crimeId.isEmpty)

  val theftCrimeTypes = Set(CrimeType.BICYCLE_THEFT, CrimeType.THEFT_FROM_THE_PERSON, CrimeType.OTHER_THEFT)

  new CrimeStatistic(incidents).getTopCrimeLocationsWithIncidents(configuration.numberOfLocations)
    .foreach(locWithIncidents => println(locWithIncidents.display(theftCrimeTypes)))

  /**
    *
    * @param dir the directory with crimes csvs
    * @return [[List]] with paths to all csv files in directory
    */
  def getListOfFiles(dir: String): List[String] = {
    val file = new File(dir)
    file.listFiles.filter(_.isFile)
      .map(_.getPath).toList
  }

  def readConfiguration: Configuration = {
    val prop = new Properties()
    val reader = fromURL(getClass.getResource("/application.properties")).bufferedReader()
    prop.load(reader)
    Configuration.fromMap(prop.asScala.toMap)
  }
}
