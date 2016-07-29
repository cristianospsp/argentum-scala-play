package util

import br.com.caelum.argentum.model.Negotiation

import scala.io.Source
import scala.xml.{XML}
import scala.util.{Failure, Success, Try}

/**
 * Created by Cristiano on 09/06/16.
 */
class WebServiceClient {

  def getNegotiationsWebService(): List[Negotiation] = {
      Try(Source.fromURL("http://argentumws.caelum.com.br/negociacoes").bufferedReader()) match {
      case Success(reader) => LeitorXML.getNegociacoes(XML.load(reader))
      case Failure(f) => {
        print(f)
        Nil
      }
    }

  }

}
