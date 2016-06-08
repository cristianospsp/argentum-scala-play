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

    "Abertura deve ser igual ZERO" in {
      0.0 must_== (candleZero.abertura)
    }

    "Fechamento deve ser igual ZERO" in {
      0.0 must_== (candleZero.fechamento)
    }

    "Minimo deve ser igual ZERO" in {
      0.0 must_== (candleZero.minimo)
    }

    "Maximo deve ser igual ZERO" in {
      0.0 must_== (candleZero.maximo)
    }

    "Volume deve ser igual ZERO" in {
      0.0 must_== (candleZero.volume)
    }

    val n1 = Negotiation(BigDecimal(40.5), 100, hoje)
    val candle = new CandlestickFactory().buildCandleToDate(hoje, List(n1))

    "Minimo deve ser igual maximo" in {
      candle.minimo must_== candle.maximo
    }

    val n2 = Negotiation(BigDecimal(40.5), 100, hoje)

    "Lancar Exception quando Minimo > Maximo" in {
      Candlestick(BigDecimal(10.0), BigDecimal(12.0), BigDecimal(10.0), BigDecimal(9.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "Lancar Exception quando Abertura < 0" in {
      Candlestick(BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "Lancar Exception quando Fechamento < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "Lancar Exception quando Minimo < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(10.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "Lancar Exception quando Maximo < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), BigDecimal(100.0), hoje) must throwA[IllegalArgumentException]
    }

    "Lancar Exception quando Voume < 0" in {
      Candlestick(BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(10.0), BigDecimal(-1.0), hoje) must throwA[IllegalArgumentException]
    }



    "Negociacoes em ordem [crescente ou decrescente] para mesma data/hora devem ter os mesmos valores" in {
      val negociacoes = List(Negotiation(BigDecimal(40.5), 100, hoje), Negotiation(BigDecimal(45.0), 100, hoje), Negotiation(BigDecimal(39.8), 100, hoje), Negotiation(BigDecimal(42.3), 100, hoje))
      val crescente = negociacoes.sortBy(_.preco)
      val deCrescente = negociacoes.sortBy(_.preco).reverse
      val candleCrescente = new CandlestickFactory().buildCandleToDate(hoje, crescente)
      val candleDecrescente = new CandlestickFactory().buildCandleToDate(hoje, deCrescente)

      candleCrescente.maximo must_== candleDecrescente.maximo
    }


    "quandoAberturaIgualFechamentoEhAlta" in {
      val negociacoes = List(Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje), Negotiation(BigDecimal(10.0), 100, hoje))
      val candle = new CandlestickFactory().buildCandleToDate(hoje, negociacoes)

      candle.isAlta() equals(true)
    }


  }

}
