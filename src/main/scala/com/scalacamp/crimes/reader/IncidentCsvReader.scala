package com.scalacamp.crimes.reader

import com.scalacamp.crimes.domain.Incident

import scala.io.Source

/**
  * Implementation of [[IncidentReader]] responsible for reading incidents from a CSV file.
  * @param fileName The path to the CSV file to be read.
  */
class IncidentCsvReader(val fileName: String) extends IncidentReader {
  override def readIncidents(): Seq[Incident] = {
    Source.fromFile(fileName).getLines()
      .drop(1) // header
      .map(Incident.parse)
      .filter(i => i.isSuccess)
      .map(_.get)
      .toSeq
  }
}
