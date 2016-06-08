package util

import java.time.{ZoneOffset, Instant, LocalDateTime}

import br.com.caelum.argentum.model.Negotiation

/**
 * Created by Cristiano on 03/06/16.
 */
class TransformaXmlToNegociacao {

  def fromXML(node: scala.xml.Node): Negotiation = {
    val p = BigDecimal((node \ "preco").text.toDouble)
    val q = (node \ "quantidade").text.toInt
    val dataMilliSeconds = (node \ "data" \ "time").text
    val instant = Instant.ofEpochMilli(dataMilliSeconds.toLong)
    Negotiation(p , q, LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
  }

}
