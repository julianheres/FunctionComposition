import java.util.concurrent.TimeUnit

import org.joda.time.Days

import scalaz.{NonEmptyList, Validation}
import scalaz._
import scalaz.Scalaz._

object TemporaryDisabilityChecker {

  type TemporaryDisabilityValidation[T] = Validation[NonEmptyList[String], T]

  def isTemporaryDisabilityValid(temporaryDisability: TemporaryDisability) = {

    def withinRetirementPolicy(t: TemporaryDisability) : TemporaryDisabilityValidation[TemporaryDisability] = {
      val MAX_TEMP_DIS_TIMESPAN = TimeUnit.MILLISECONDS.convert(540, TimeUnit.DAYS)
      if( Days.daysBetween(t.end.toInstant, t.beginning.toInstant).getDays < MAX_TEMP_DIS_TIMESPAN ) {
        Failure(NonEmptyList("Cannot add more days than retirement policy"))
      } else {
        t.success
      }
    }

    def highPriorityDisease(t: TemporaryDisability) : TemporaryDisabilityValidation[TemporaryDisability] = {
      if( t.disease.name match {
        case "BROKEN_ARM" => true
        case "CATARACTS" => true
        case _ => false
      } ) {
        t.success
      } else {
        Failure(NonEmptyList("Can only add high priority diseases"))
      }
    }

    withinRetirementPolicy(temporaryDisability) |@| highPriorityDisease(temporaryDisability)
  }
}