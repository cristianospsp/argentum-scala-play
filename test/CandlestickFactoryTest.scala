import java.time.LocalDateTime

import br.com.caelum.argentum.modelo.{CandlestickFactory, Negociacao}
import org.specs2.mutable.Specification

/**
 * Created by Cristiano on 31/05/16.
 */
class CandlestickFactoryTest extends Specification {

  "Candlestick" should {

    val hoje = LocalDateTime.now();

    val n1 = Negociacao(BigDecimal(40.5), 100, hoje)
    val n2 = Negociacao(BigDecimal(45.0), 100, hoje)
    val n3 = Negociacao(BigDecimal(39.8), 100, hoje)
    val n4 = Negociacao(BigDecimal(42.3), 100, hoje)

    val candle = new CandlestickFactory().buildCandleToDate(hoje, List(n1, n2, n3, n4))

    "valor minimo deve ser 39.8" in {
      39.8 must_== (candle.minimo)
    }

    "valor maximo deve ser 45.0" in {
      45.0 must_== (candle.maximo)
    }

    "deve ser de alta" in {
      true equals (candle.isAlta())
    }

    "Nao deve ser de baixa" in {
      false equals (candle.isBaixa())
    }

  }

}
