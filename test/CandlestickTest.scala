import java.time.LocalDateTime

import br.com.caelum.argentum.model.{Candlestick, CandlestickFactory, Negotiation}
import org.specs2.mutable.Specification

/**
 * Created by Cristiano on 02/06/16.
 */
class CandlestickTest extends Specification {

  "Candlestick" >> {
    val hoje = LocalDateTime.now();
    val candleZero = new CandlestickFactory().buildCandleToDate(hoje, List())

    "opening must be equals ZERO" in {
      0.0 must_== (candleZero.opening)
    }

    "closing must be equals ZERO" in {
      0.0 must_== (candleZero.closing)
    }

    "Minimum must be equals ZERO" in {
      0.0 must_== (candleZero.minimum)
    }

    "Maximum must be equals ZERO" in {
      0.0 must_== (candleZero.maximum)
    }

    "Volume must be equals ZERO" in {
      0.0 must_== (candleZero.volume)
    }

    val n1 = Negotiation(BigDecimal(40.5), 100, hoje)
    val candle = new CandlestickFactory().buildCandleToDate(hoje, List(n1))

    "Minimum must be equals maximum" in {
      candle.minimum must_== candle.maximum
    }

    val n2 = Negotiation(BigDecimal(40.5), 100, hoje)

    "throws exceptions when minimum > maximum" in {
      Candlestick(BigDecimal(10.0), BigDecimal(12.0), BigDecimal(10.0), BigDecimal(9.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "throws exceptions when opening < 0" in {
      Candlestick(BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "throws exceptions when closing < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "throws exceptions when Minimum < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "throws exceptions when Maximum < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "throws exceptions when Voume < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), hoje) must throwA[IllegalArgumentException]
    }

    "Negoctiations ordered [asc or desc] to the same date/hour must have the same values" in {
      val negociacoes = List(Negotiation(BigDecimal(40.5), 100, hoje), Negotiation(BigDecimal(45.0), 100, hoje), Negotiation(BigDecimal(39.8), 100, hoje), Negotiation(BigDecimal(42.3), 100, hoje))
      val crescente = negociacoes.sortBy(_.price)
      val deCrescente = negociacoes.sortBy(_.price).reverse
      val candleCrescente = new CandlestickFactory().buildCandleToDate(hoje, crescente)
      val candleDecrescente = new CandlestickFactory().buildCandleToDate(hoje, deCrescente)

      candleCrescente.maximum must_== candleDecrescente.maximum
    }

    "when opening equals closing the candle is high" in {
      val negociacoes = List(Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje))
      val candle = new CandlestickFactory().buildCandleToDate(hoje, negociacoes)

      candle.isAlta() equals (true)
    }

    val today = LocalDateTime.now()
    val neg1 = Negotiation(BigDecimal(40.5), 100, today)
    val neg2 = Negotiation(BigDecimal(45.0), 100, today)
    val neg3 = Negotiation(BigDecimal(49.8), 100, today)
    val neg4 = Negotiation(BigDecimal(42.3), 100, today)
    val neg5 = Negotiation(BigDecimal(48.8), 100, today.plusDays(1))
    val neg6 = Negotiation(BigDecimal(49.3), 100, today.plusDays(1))
    val neg7 = Negotiation(BigDecimal(51.8), 100, today.plusDays(2))
    val neg8 = Negotiation(BigDecimal(52.3), 100, today.plusDays(2))
    val candles = new CandlestickFactory().buildCandle(List(neg1, neg2, neg3, neg4, neg5, neg6, neg7, neg8))

    "mustCreateListWith3Candles" in {
      candles.size must_== 3
    }

    "the first candle must be opening Equals 40.5" >> {
      candles(0).opening must_== 40.5
    }

    "the first candle must be closing Equals 42.3" >> {
      candles(0).closing must_== 42.3
    }

    "the second candle must be opening Equals 48.8" >> {
      candles(1).opening must_== 48.8
    }

    "the second candle must be closing Equals 49.3" >> {
      candles(1).closing must_== 49.3
    }

    "the third candle must be opening Equals 51.8" >> {
      candles(2).opening must_== 51.8
    }

    "the third candle must be closing Equals 52.3" >> {
      candles(2).closing must_== 52.3
    }


  }

}
