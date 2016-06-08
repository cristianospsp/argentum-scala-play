package util

import java.time.{LocalDateTime, Month, ZoneOffset}
import java.util.Date


import br.com.caelum.argentum.model.Negotiation

import scala.util.Random
import scala.xml.Node

/**
 * Created by Cristiano on 08/06/16.
 */
class XmlGeneratorRandom {

  val random = Random

  val negociacoes = generatorDays(30,Month.JANUARY).map(geraListaNegociosParaDia(_))
    .reduce((l1,l2) => l1:::l2)
  salvarXml(gerarXml(negociacoes))

  def generatorDays(days: Int, month: Month) = (1 to days).map(dia => LocalDateTime.of(2016, month, dia, 0, 0))

  def gerarXml(negotiations: List[Negotiation]) = {
    <list>
      {negotiations.map(n => {
      <negotiation>
        <price>
          {n.price}
        </price>
        <amount>
          {n.amount}
        </amount>
        <date>
          <time>{toMillis(n.date)}</time>
        </date>
      </negotiation>
    })}
    </list>
  }

  def salvarXml(xml: Node): Unit = {
    scala.xml.XML.save("negociacoes.xml", xml, "UTF-8")
  }


  def toMillis(data: LocalDateTime): Long = {
    val instant = data.toInstant(ZoneOffset.UTC)
    val dataComoDate = Date.from(instant)
    dataComoDate.getTime
  }

  def geraListaNegociosParaDia(data: LocalDateTime): List[Negotiation] = {
    val quantidadeNegociosDia = random.nextInt(20)

    def geraValor() = {
      val valor = (random.nextInt(200) - 100) / 100.0
      if (valor < 5.0) 5.0 else valor
    }

    def geraQuantidade() = {
      500 + random.nextInt(1000)
    }

    (0 to quantidadeNegociosDia).map(v => {
      Negotiation(geraValor(), geraQuantidade(), data)
    }).toList
  }

}
