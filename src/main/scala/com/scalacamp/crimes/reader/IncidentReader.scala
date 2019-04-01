package com.scalacamp.crimes.reader

import com.scalacamp.crimes.domain.Incident

/**
  * Trait responsible for reading/loading [[Incident]].
  */
trait IncidentReader {
  /**
    *
    * @return A [[Seq]] containing all incidents.
    */
  def readIncidents(): Seq[Incident]
}
