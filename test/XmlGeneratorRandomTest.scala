import org.specs2.mutable.Specification
import util.{LeitorXML, XmlGeneratorRandom}

/**
 * Created by Cristiano on 08/06/16.
 */
class XmlGeneratorRandomTest  extends Specification {

  "XmlGeneratorRandomTest" should {

    "generate a random xml with size of negotiations > 0" in {
      new XmlGeneratorRandom()
      val nodeSeq = scala.xml.XML.load("negociacoes.xml")

      val negotiations = LeitorXML.getNegociacoes(nodeSeq)

      negotiations.size > 0
    }
  }
}
