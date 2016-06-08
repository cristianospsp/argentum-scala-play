package util

import br.com.caelum.argentum.model.Negotiation

import scala.xml.NodeSeq

/**
 * Created by Cristiano on 03/06/16.
 */
object LeitorXML {

  def getNegociacoes(xml : NodeSeq) : List[Negotiation] = {
    (xml \\ "negociacao").map(n => new TransformaXmlToNegociacao().fromXML(n)).toList
  }

}
