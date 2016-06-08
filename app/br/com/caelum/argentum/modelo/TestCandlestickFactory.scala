package br.com.caelum.argentum.modelo

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
object TestCandlestickFactory extends App {


  val hoje = LocalDateTime.now();

  val n1 = Negociacao(BigDecimal(40.5), 100, hoje)
  val n2 = Negociacao(BigDecimal(45.0), 100, hoje)
  val n3 = Negociacao(BigDecimal(39.8), 100, hoje)
  val n4 = Negociacao(BigDecimal(42.3), 100, hoje)

  println(new CandlestickFactory().buildCandleToDate(hoje, List(n1, n2, n3, n4)))


}
