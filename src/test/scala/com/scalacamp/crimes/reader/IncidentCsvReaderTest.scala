package com.scalacamp.crimes.reader

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class IncidentCsvReaderTest extends FunSuite {
  test("parse csv file") {
    val incidentReader = new IncidentCsvReader(getClass.getClassLoader().getResource("crimes.csv").getPath)
    val incidents = incidentReader.readIncidents()
    incidents.size shouldBe 3
  }
}
