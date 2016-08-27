import org.joda.time.DateTime
import org.scalatest.FunSuite

class NaiveTest extends FunSuite {

  test("Is a high priority immediately payable") {
    val td1 = TemporaryDisability(DateTime.now().withTimeAtStartOfDay(),
      DateTime.now().plusDays(12), Disease("ET45", "LIVER_CANCER", "Liver Cancer"),
      Some( LawSuit(DateTime.now().minusDays(3), DateTime.now().plusMonths(24), "Columbia")) )
    assert( td1.equals( TemporaryDisabilityChecker.isTemporaryDisabilityValid((td1))))
  }

}