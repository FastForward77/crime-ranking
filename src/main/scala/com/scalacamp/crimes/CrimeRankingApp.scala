package com.scalacamp.crimes

import java.io.File

import com.scalacamp.crimes.reader.IncidentCsvReader
import com.scalacamp.crimes.stat.CrimeStat

object CrimeRankingApp extends App {
  val pathToCrimesDir = "d:\\tmp\\crimes"

  val incidents = getListOfFiles(pathToCrimesDir)
    .flatMap(filePath => new IncidentCsvReader(filePath).readIncidents())
      .filter(!_.crimeId.isEmpty)

  new CrimeStat(incidents).getTopCrimeLocationsWithIncidents(10)
    .foreach(locWithIncidents => println(locWithIncidents.display))

  def getListOfFiles(dir: String): List[String] = {
    val file = new File(dir)
    file.listFiles.filter(_.isFile)
      .map(_.getPath).toList
  }
}
