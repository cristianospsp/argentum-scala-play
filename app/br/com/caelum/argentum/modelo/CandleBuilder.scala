package br.com.caelum.argentum.modelo

import java.time.LocalDateTime

/**
 * Created by Cristiano on 02/06/16.
 */
class CandleBuilder {

  var abertura : BigDecimal = 0.0
  var fechamento : BigDecimal = 0.0
  var minimo : BigDecimal = 0.0
  var maximo : BigDecimal = 0.0
  var volume : BigDecimal = 0.0
  var data : LocalDateTime = LocalDateTime.now()

  def comAbertura(primeiroDoDia: BigDecimal) : CandleBuilder = {
    this.abertura = primeiroDoDia
    this
  }

  def comFechamento(ultimoDoDia: BigDecimal) : CandleBuilder = {
    this.fechamento = ultimoDoDia
    this
  }

  def comMinimo(minimo: BigDecimal) : CandleBuilder = {
    this.minimo = minimo
    this
  }

  def comMaximo(maximo: BigDecimal) : CandleBuilder = {
    this.maximo = maximo
    this
  }

  def comVolume(volume: BigDecimal) : CandleBuilder = {
    this.volume = volume
    this
  }

  def comData(data: LocalDateTime) : CandleBuilder = {
    this.data = data
    this
  }

  def gerarCandle(): Candlestick = {
    Candlestick(abertura, fechamento, minimo, maximo, volume, data)
  }


}
