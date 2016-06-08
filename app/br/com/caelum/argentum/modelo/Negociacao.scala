package br.com.caelum.argentum.modelo

import java.time.LocalDateTime

/**
 * Created by Cristiano on 31/05/16.
 */
final case class Negociacao(val preco: BigDecimal, val quantidade: Int, val data: LocalDateTime) {

  require(quantidade > 0, "Quantidade deve ser maior que 0")
  require(data != null, "Data de negociacao deve ser informada")

  def volume(): BigDecimal = preco * quantidade

}
