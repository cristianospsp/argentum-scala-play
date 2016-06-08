package util

import java.time.{ZoneOffset, Instant, LocalDateTime}

import br.com.caelum.argentum.model.Negotiation

/**
 * Created by Cristiano on 03/06/16.
 */
class TransformaXmlToNegociacao {

  def fromXML(node: scala.xml.Node): Negotiation = {
    val price = BigDecimal((node \ "price").text.toDouble)
    val amount = (node \ "amount").text.trim.toInt
    val dateInMilliSeconds = (node \ "date" \ "time").text
    val instant = Instant.ofEpochMilli(dateInMilliSeconds.toLong)
    Negotiation(price , amount, LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
  }

}
