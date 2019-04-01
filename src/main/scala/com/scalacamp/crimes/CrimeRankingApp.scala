package com.scalacamp.crimes

import java.io.File

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

  val incidents = getListOfFiles(args(0))
    .flatMap(filePath => new IncidentCsvReader(filePath).readIncidents())
      .filter(!_.crimeId.isEmpty)

  new CrimeStatistic(incidents).getTopCrimeLocationsWithIncidents(10)
    .foreach(locWithIncidents => println(locWithIncidents.display))

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
}
