package com.scalacamp.crimes.stats

import com.scalacamp.crimes.domain.{Coordinates, Incident}

case class IncidentsByLocation(coordinates: Coordinates, incidents: Seq[Incident]) {
  def display = s"${coordinates}:${incidents.size}\nThefts:\n" +
    incidents.map(incident => incident.crimeId).mkString("\n")
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
