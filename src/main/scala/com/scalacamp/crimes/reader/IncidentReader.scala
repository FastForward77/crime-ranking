package com.scalacamp.crimes.reader

import com.scalacamp.crimes.domain.Incident

trait IncidentReader {
  def readIncidents(): Seq[Incident]
}
