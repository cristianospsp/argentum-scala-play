package br.com.caelum.argentum.model

import java.time.LocalDateTime

/**
 * Created by Cristiano on 02/06/16.
 */
class CandleBuilder {

  var opening : BigDecimal = 0.0
  var closing : BigDecimal = 0.0
  var minimum : BigDecimal = 0.0
  var maximum : BigDecimal = 0.0
  var volume : BigDecimal = 0.0
  var date : LocalDateTime = LocalDateTime.now()

  def withOpening(firstOfDay: BigDecimal) : CandleBuilder = {
    this.opening = firstOfDay
    this
  }

  def withClosing(lasfOfDay: BigDecimal) : CandleBuilder = {
    this.closing = lasfOfDay
    this
  }

  def withMinimum(minimum: BigDecimal) : CandleBuilder = {
    this.minimum = minimum
    this
  }

  def withMaximum(maximum: BigDecimal) : CandleBuilder = {
    this.maximum = maximum
    this
  }

  def withVolume(volume: BigDecimal) : CandleBuilder = {
    this.volume = volume
    this
  }

  def withDate(date: LocalDateTime) : CandleBuilder = {
    this.date = date
    this
  }

  def buildCandle(): Candlestick = {
    Candlestick(opening, closing, minimum, maximum, volume, date)
  }


}
