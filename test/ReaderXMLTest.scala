
import org.specs2.mutable.Specification
import util.LeitorXML

/**
 * Created by Cristiano on 03/06/16.
 */
class ReaderXMLTest extends Specification {

  val xmlToTest =
    <list>
      <negociacao>
        <preco>43.5</preco>
        <quantidade>1000</quantidade>
        <data>
          <time>1322233344455</time>
        </data>
      </negociacao>
    </list>


  "ReaderXMLTest" should {

    val negociacoes = LeitorXML.getNegociacoes(xmlToTest)
    val negociacao = negociacoes.head

    "Amount of itens must be equals 1" in {
      negociacoes.size must_== 1
    }

    "Negotiation must be price equals 43.5 " in {
      negociacao.price must_== BigDecimal(43.5)
    }

    "Amount must be equals 1000  " in {
      negociacao.amount must_== 1000
    }

  }

}
