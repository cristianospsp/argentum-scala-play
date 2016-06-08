package br.com.caelum.argentum.model

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
final case class Negotiation(val preco: BigDecimal, val quantidade: Int, val data: LocalDateTime) {

  require(quantidade > 0, "Quantidade deve ser maior que 0")
  require(data != null, "Data de negociacao deve ser informada")

  def volume(): BigDecimal = preco * quantidade

  def isSameDate(otherDate: LocalDateTime): Boolean = this.data.isEqual(otherDate)

}
