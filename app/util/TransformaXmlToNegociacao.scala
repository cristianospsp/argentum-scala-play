package util

import java.time.{ZoneOffset, Instant, LocalDateTime}

import br.com.caelum.argentum.model.Negotiation

/**
 * Created by Cristiano on 03/06/16.
 */
class TransformaXmlToNegociacao {

  def fromXML(node: scala.xml.Node): Negotiation = {
    val price = BigDecimal((node \ "preco").text.toDouble)
    val amount = (node \ "quantidade").text.trim.toInt
    val dateInMilliSeconds = (node \ "data" \ "time").text
    val instant = Instant.ofEpochMilli(dateInMilliSeconds.toLong)
    Negotiation(price , amount, LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
  }

}
