package com.scalacamp.crimes.stat

import com.scalacamp.crimes.domain.{Coordinates, Incident}

case class IncidentsByLocation(coordinates: Coordinates, incidents: Seq[Incident]) {
  def display = s"${coordinates}:${incidents.size}\nThefts:\n" +
    incidents.map(incident => incident.crimeId).mkString("\n")
}

class CrimeStat(incidents: Seq[Incident]) {
  def getTopCrimeLocationsWithIncidents(numberOfLocations: Int): Seq[IncidentsByLocation] = {
    incidents
      .groupBy(_.location.coordinates)
      .map(x => IncidentsByLocation(x._1, x._2)).toSeq
      .sortBy(-_.incidents.size)
      .take(numberOfLocations)
  }
}
