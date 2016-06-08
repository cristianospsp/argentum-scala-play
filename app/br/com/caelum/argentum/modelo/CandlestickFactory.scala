package br.com.caelum.argentum.modelo

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
class CandlestickFactory {

  def buildCandleToDate(data: LocalDateTime, negociacoes: List[Negociacao]): Candlestick = {

    val precos = negociacoes.map(_.preco)
    val maximo = precos.reduceOption(_ max _).getOrElse(BigDecimal(0.0))
    val minimo = precos.reduceOption(_ min _).getOrElse(BigDecimal(0.0))
    val volume = negociacoes.map(_.volume()).sum
    val primeiraDoDia = negociacoes.headOption.map(a => a.preco).getOrElse(BigDecimal(0.0))
    val ultimoDoDia = negociacoes.lastOption.map(a => a.preco).getOrElse(BigDecimal(0.0))

    new CandleBuilder()
      .comAbertura(primeiraDoDia)
      .comFechamento(ultimoDoDia)
      .comMinimo(minimo)
      .comMaximo(maximo)
      .comVolume(volume)
      .comData(data).gerarCandle()

  }

}
