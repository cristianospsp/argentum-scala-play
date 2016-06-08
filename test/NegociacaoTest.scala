import java.time.LocalDateTime

import br.com.caelum.argentum.modelo.Negociacao
import org.specs2.mutable.Specification

/**
 * Created by Cristiano on 03/06/16.
 */
class NegociacaoTest extends Specification {

  "Negociacao" should {
    val hoje = LocalDateTime.now();
    val n1 = Negociacao(BigDecimal(40.5), 100, hoje)

    "Data da negociacao deve ser imutavel" in {
      hoje isEqual(n1.data)
    }

  }

}
