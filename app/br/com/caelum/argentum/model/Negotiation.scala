package br.com.caelum.argentum.model

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
final case class Negotiation(val price: BigDecimal, val amount: Int, val date: LocalDateTime) {

  require(amount > 0, "Amount must be greater than 0")
  require(date != null, "Date of negotiation must be informed")

  def volume(): BigDecimal = price * amount

  def isSameDate(otherDate: LocalDateTime): Boolean = this.date.isEqual(otherDate)

}
