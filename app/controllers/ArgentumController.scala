package controllers

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import br.com.caelum.argentum.model.Negotiation
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}
import util.WebServiceClient


/**
 * Created by Cristiano on 09/06/16.
 */
@Singleton
class ArgentumController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  implicit val implicitNegocicaoWrites = new Writes[Negotiation] {
    override def writes(o: Negotiation): JsValue = {
      Json.obj(
        "price" -> o.price,
        "amount" -> o.amount,
        "date" -> o.date.toString
      )
    }
  }

  def negociacoes = Action {
    val today = LocalDateTime.now()
    val n = Negotiation(BigDecimal(40.5), 100, today)
    val n2 = Negotiation(BigDecimal(44.5), 100, today)
    val n3 = Negotiation(BigDecimal(55.5), 100, today)
    val negs = List(n, n2, n3)
    val negociacoes_ = new WebServiceClient().getNegotiationsWebService()
    //val json = Json.toJson(negociacoes_)
    Ok(views.html.home(negociacoes_))
  }

  def about = Action {
    Ok(views.html.about())
  }

  def contact = Action {
    Ok(views.html.contact())
  }

}
