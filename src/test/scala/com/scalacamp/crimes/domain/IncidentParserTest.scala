package com.scalacamp.crimes.domain

import java.time.YearMonth

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.util.{Success}

class IncidentParserTest extends FunSuite {
  test("parse csv line") {
    val csv = "53975fc0e75b47e64d703a7cba08ae8636b3131b0f6c61319ea1f51952aa5692,2018-12,Avon and Somerset Constabulary," +
      "Avon and Somerset Constabulary,-2.509126,51.416137,On or near St Francis Road,E01014399," +
      "Bath and North East Somerset 001A,Criminal damage and arson,Under investigation,"
    val incident = Incident.parse(csv)
    incident shouldBe Success(Incident("53975fc0e75b47e64d703a7cba08ae8636b3131b0f6c61319ea1f51952aa5692",
      YearMonth.parse("2018-12"), "Avon and Somerset Constabulary", "Avon and Somerset Constabulary",
      Location("On or near St Francis Road", Coordinates(51.416137, -2.509126), "E01014399", "Bath and North East Somerset 001A"),
      CrimeType.withName("Criminal damage and arson"), "Under investigation", ""))
  }

  test("parse csv line with missed coordinates") {
    val csv = "53975fc0e75b47e64d703a7cba08ae8636b3131b0f6c61319ea1f51952aa5692,2018-12,Avon and Somerset Constabulary," +
      "Avon and Somerset Constabulary,-,,On or near St Francis Road,E01014399," +
      "Bath and North East Somerset 001A,Criminal damage and arson,Under investigation,"
    intercept[NumberFormatException] {
      Incident.parse(csv).get
    }
  }
}
