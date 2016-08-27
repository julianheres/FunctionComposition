import java.util.Date

import org.joda.time.DateTime

case class LawSuit(issuingDate: DateTime, dueDate: DateTime, district: String)
case class Disease( code:String, name: String, description: String )
case class TemporaryDisability(beginning: DateTime,
                               end: DateTime,
                               disease: Disease,
                               lawSuit: Option[LawSuit])