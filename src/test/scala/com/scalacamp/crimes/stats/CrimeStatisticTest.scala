package com.scalacamp.crimes.stats

import java.time.YearMonth

import com.scalacamp.crimes.domain.{Coordinates, CrimeType, Incident, Location}
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CrimeStatisticTest extends FunSuite {

  test("get top 2 locations with incidents") {
    val location1 = Coordinates(1.0, 1.0)
    val location2 = Coordinates(1.0, 2.0)
    val location3 = Coordinates(1.0, 3.0)

    val incidents = Seq(
      createIncidentStub(location1, "1"),
      createIncidentStub(location1, "2"),
      createIncidentStub(location1, "3"),
      createIncidentStub(location2, "4"),
      createIncidentStub(location2, "5"),
      createIncidentStub(location3, "6"))

    val incidentsByLocation = new CrimeStatistic(incidents).getTopCrimeLocationsWithIncidents(2)
    incidentsByLocation.size shouldBe 2
    incidentsByLocation(0).coordinates shouldBe location1
    incidentsByLocation(0).incidents.size shouldBe 3
    incidentsByLocation(1).coordinates shouldBe location2
    incidentsByLocation(1).incidents.size shouldBe 2
  }

  test("get top 2 locations from locations with the same number of incidents") {
    val location1 = Coordinates(1.0, 1.0)
    val location2 = Coordinates(1.0, 2.0)
    val location3 = Coordinates(1.0, 3.0)

    val incidents = Seq(
      createIncidentStub(location1, "1"),
      createIncidentStub(location1, "2"),
      createIncidentStub(location2, "3"),
      createIncidentStub(location2, "4"),
      createIncidentStub(location3, "5"),
      createIncidentStub(location3, "6"))

    val incidentsByLocation = new CrimeStatistic(incidents).getTopCrimeLocationsWithIncidents(2)
    incidentsByLocation.size shouldBe 2
    incidentsByLocation(0).incidents.size shouldBe 2
    incidentsByLocation(1).incidents.size shouldBe 2
  }

  def createIncidentStub(coordinates: Coordinates, crimeId: String): Incident = {
    Incident(crimeId, YearMonth.now(), "", "", Location("", coordinates, "", ""), CrimeType.withName("Criminal damage and arson"),
    "", "")
  }
}
