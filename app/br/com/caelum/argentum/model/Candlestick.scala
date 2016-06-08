package br.com.caelum.argentum.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Cristiano on 31/05/16.
 */
final case class Candlestick(val opening: BigDecimal, val closing: BigDecimal, val minimum: BigDecimal,
                             val maximum: BigDecimal, val volume: BigDecimal, val date: LocalDateTime) {
  require(opening >= BigDecimal(0.0))
  require(closing >= BigDecimal(0.0))
  require(minimum >= BigDecimal(0.0))
  require(maximum >= BigDecimal(0.0))
  require(volume >= BigDecimal(0.0))
  require(maximum >= minimum)

  final var defaultDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")

  def isAlta(): Boolean = opening <= closing

  def isBaixa(): Boolean = opening > closing

  override def toString: String = {
    s"{ (Abertura: ${opening}) (Fechamento: ${closing}) (Minimo: ${minimum}) (Maximo: ${maximum}) (volume: ${volume}) (Data: ${date.format(defaultDateFormat)}) }"
  }
}
