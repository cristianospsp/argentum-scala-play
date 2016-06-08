package br.com.caelum.argentum.modelo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Cristiano on 31/05/16.
 */
final case class Candlestick(val abertura: BigDecimal, val fechamento: BigDecimal, val minimo: BigDecimal,
                             val maximo: BigDecimal, val volume: BigDecimal, val data: LocalDateTime) {
  require(abertura >= BigDecimal(0.0))
  require(fechamento >= BigDecimal(0.0))
  require(minimo >= BigDecimal(0.0))
  require(maximo >= BigDecimal(0.0))
  require(volume >= BigDecimal(0.0))
  require(maximo >= minimo)

  final var formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")
  def isAlta(): Boolean = abertura <= fechamento
  def isBaixa(): Boolean = abertura > fechamento

  override def toString: String = {
    s"{ (Abertura: ${abertura}) (Fechamento: ${fechamento}) (Minimo: ${minimo}) (Maximo: ${maximo}) (volume: ${volume}) (Data: ${data.format(formatoData)}) }"
  }
}
