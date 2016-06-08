package br.com.caelum.argentum.model

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
class CandlestickFactory {

  def buildCandleToDate(date: LocalDateTime, negotiations: List[Negotiation]): Candlestick = {

    val prices = negotiations.map(_.price)
    val max = prices.reduceOption(_ max _).getOrElse(BigDecimal(0.0))
    val min = prices.reduceOption(_ min _).getOrElse(BigDecimal(0.0))
    val volume = negotiations.map(_.volume()).sum
    val firstOfDay = negotiations.headOption.map(a => a.price).getOrElse(BigDecimal(0.0))
    val lastOfDay = negotiations.lastOption.map(a => a.price).getOrElse(BigDecimal(0.0))

    new CandleBuilder()
      .withOpening(firstOfDay)
      .withClosing(lastOfDay)
      .withMinimum(min)
      .withMaximum(max)
      .withVolume(volume)
      .withDate(date).buildCandle()

  }

  def buildCandle(negociations: List[Negotiation]): List[Candlestick] = {
    val negotiationByDate = negociations
      .groupBy(n => LocalDateTime.of(n.date.getYear, n.date.getMonth, n.date.getDayOfMonth, 0, 0, 0))
    negotiationByDate
      .map((v) => buildCandleToDate(v._1, v._2))
      .toList
      .sortWith((c1, c2) => c1.date.isBefore(c2.date))
  }

}
