import java.time.LocalDateTime

import br.com.caelum.argentum.model.{CandlestickFactory, Negotiation}
import org.specs2.mutable.Specification

/**
 * Created by Cristiano on 31/05/16.
 */
class CandlestickFactoryTest extends Specification {

  "Candlestick" should {

    val hoje = LocalDateTime.now();

    val n1 = Negotiation(BigDecimal(40.5), 100, hoje)
    val n2 = Negotiation(BigDecimal(45.0), 100, hoje)
    val n3 = Negotiation(BigDecimal(39.8), 100, hoje)
    val n4 = Negotiation(BigDecimal(42.3), 100, hoje)

    val candle = new CandlestickFactory().buildCandleToDate(hoje, List(n1, n2, n3, n4))

    "value minimum must be 39.8" in {
      39.8 must_== (candle.minimum)
    }

    "value maximum must be 45.0" in {
      45.0 must_== (candle.maximum)
    }

    "must be high" in {
      true equals (candle.isAlta())
    }

    "shoul not br low" in {
      false equals (candle.isBaixa())
    }

  }

}
