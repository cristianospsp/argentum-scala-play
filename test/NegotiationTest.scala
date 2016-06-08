import java.time.LocalDateTime

import br.com.caelum.argentum.model.Negotiation
import org.specs2.mutable.Specification

/**
 * Created by Cristiano on 03/06/16.
 */
class NegotiationTest extends Specification {

  "Negotiation" should {

    val today = LocalDateTime.now()
    val negotiation = Negotiation(BigDecimal(40.5), 100, today)
    val dateFromIntanceOfNegotiation = today

    "Date of negotiation must be imutable" in {
      today isEqual (dateFromIntanceOfNegotiation)
    }

    "The same day of mounth" in {
      negotiation.isSameDate(today)
    }

    "The same day of mounth and different month" in {
      val nextMonth = LocalDateTime.now().plusMonths(1)
      !negotiation.isSameDate(nextMonth)
    }

    "The same day and mounth and different year" in {
      val nextYear = LocalDateTime.now().plusYears(1)
      !negotiation.isSameDate(nextYear)
    }

  }

}
