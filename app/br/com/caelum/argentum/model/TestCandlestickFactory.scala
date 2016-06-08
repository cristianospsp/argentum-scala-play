package br.com.caelum.argentum.model

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
object TestCandlestickFactory extends App {


  val today = LocalDateTime.now();

  val n1 = Negotiation(BigDecimal(40.5), 100, today)
  val n2 = Negotiation(BigDecimal(45.0), 100, today)
  val n3 = Negotiation(BigDecimal(39.8), 100, today)
  val n4 = Negotiation(BigDecimal(42.3), 100, today)

  println(new CandlestickFactory().buildCandleToDate(today, List(n1, n2, n3, n4)))


}
