package com.scalacamp.crimes.domain

import java.time.YearMonth

import com.scalacamp.crimes.domain.CrimeType.CrimeType

import scala.util.{Failure, Try}

case class Location(name: String, coordinates: Coordinates, lsoaCode: String, lsoaName: String)
case class Coordinates(latitude: Double, longitude: Double) {
  override def toString() = s"(${latitude},${longitude})"
}

object CrimeType extends Enumeration {
  type CrimeType = Value
  val ANTI_SOCIAL_BEHAVIOUR = Value("Anti-social behaviour")
  val CRIMINAL_DAMAGE_AND_ARSON = Value("Criminal damage and arson")
  val PUBLIC_ORDER = Value("Public order")
  val VIOLENCE_AND_SEXUAL_OFFENCES = Value("Violence and sexual offences")
  val BURGLARY = Value("Burglary")
  val OTHER_THEFT = Value("Other theft")
  val THEFT_FROM_THE_PERSON = Value("Theft from the person")
  val VEHICLE_CRIME = Value("Vehicle crime")
  val ROBBERY = Value("Robbery")
  val DRUGS = Value("Drugs")
  val SHOPLIFTING = Value("Shoplifting")
  val OTHER_CRIME = Value("Other crime")
  val BICYCLE_THEFT = Value("Bicycle theft")
  val POSSESSION_OF_WEAPONS = Value("Possession of weapons")
}
case class Incident(crimeId: String, month: YearMonth, reportedBy: String, fallsWithin: String,
                    location: Location, crimeType: CrimeType, lastOutcomeCategory: String, context: String)

object Incident {
  def parse(csv: String): Try[Incident] = {
    csv.split(",", -1).map(_.trim) match {
      case Array(crimeID, month, reportedBy, fallsWithin, longitude, latitude, location, lsoaCode,
      lsoaName, crimeType, lastOutcomeCategory, context) =>
        Try(new Incident(crimeID, YearMonth.parse(month), reportedBy, fallsWithin,
          Location(location, Coordinates(latitude.toDouble, longitude.toDouble), lsoaCode, lsoaName),
          CrimeType.withName(crimeType), lastOutcomeCategory, context))
      case _ => Failure(new IllegalStateException("Incorrect incident format"))
    }
  }
}
