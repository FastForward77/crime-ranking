package com.scalacamp.crimes.stats

import com.scalacamp.crimes.domain.CrimeType.CrimeType
import com.scalacamp.crimes.domain.{Coordinates, CrimeType, Incident}

case class IncidentsByLocation(coordinates: Coordinates, incidents: Seq[Incident]) {
  def display: String = display(CrimeType.values)


  def display(allowedCrimeTypes: Set[CrimeType]) = s"${coordinates}:${incidents.size}\nThefts:\n" +
    incidents
      .filter(i => allowedCrimeTypes.contains(i.crimeType))
      .map(incident => incident.crimeId).mkString("\n")
}

/**
  * Responsible for computing statistic based on a [[Seq]] of [[Incident]]
  * @param incidents
  */
class CrimeStatistic(incidents: Seq[Incident]) {
  /**
    *
    * @param numberOfLocations the number locations with the largest number of incidents to be returned
    * @return  The Seq with the incidents grouped by location
    */
  def getTopCrimeLocationsWithIncidents(numberOfLocations: Int): Seq[IncidentsByLocation] = {
    incidents
      .groupBy(_.location.coordinates)
      .map(x => IncidentsByLocation(x._1, x._2)).toSeq
      .sortBy(-_.incidents.size)
      .take(numberOfLocations)
  }
}
