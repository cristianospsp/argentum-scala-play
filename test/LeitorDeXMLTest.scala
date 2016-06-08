
import org.specs2.mutable.Specification
import util.LeitorXML

/**
 * Created by Cristiano on 03/06/16.
 */
class LeitorDeXMLTest extends Specification {

  val xmlDeTeste =
    <list>
      <negociacao>
        <preco>43.5</preco>
        <quantidade>1000</quantidade>
        <data>
          <time>1322233344455</time>
        </data>
      </negociacao>
    </list>


  "LeitorDeXMLTest" should {

    val negociacoes = LeitorXML.getNegociacoes(xmlDeTeste)
    val negociacao = negociacoes.head

    "Quantidade de itens deve ser igual 1" in {
      negociacoes.size must_== 1
    }

    "a negociação deve ter preco 43.5 " in {
      negociacao.preco must_== BigDecimal(43.5)
    }

    "a quantidade deve ser igual a 1000  " in {
      negociacao.quantidade must_== 1000
    }

  }

}
