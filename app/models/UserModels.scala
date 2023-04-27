package models


import javax.inject.Inject
import play.api.db.Database
import play.api.libs.json.{JsObject, Json, Writes}

import scala.collection.mutable.ListBuffer

case class UserModels(id: Int, company_id:String, employee_id:String,details: String)

object UserModels{
  implicit val writes: Writes[UserModels] = new Writes[UserModels] {
    override def writes(o: UserModels): JsObject = Json.obj(
      "id"->o.id,
      "company_id" -> o.company_id,
      "employee_id" -> o.employee_id,
      "details" -> o.details

    )
  }
}

class UserDao @Inject()(db: Database) {

  def all(): List[UserModels] = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery("SELECT * FROM emp")
      val users = new ListBuffer[UserModels]()
      while (rs.next()) {
        val id = rs.getInt("id")
        val company_id = rs.getString("company_id")
        val employee_id = rs.getString("employee_id")
        val details = rs.getString("details")

        users += UserModels(id,company_id, employee_id, details)
      }
      users.toList
    } finally {
      conn.close()
    }
  }

 def findById(id: Int): Option[UserModels] = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery(s"SELECT * FROM emp WHERE id=$id")
      if (rs.next()) {
        val id = rs.getInt("id")
        val company_id = rs.getString("company_id")
        val employee_id = rs.getString("employee_id")
        val details = rs.getString("details")
        Some(UserModels(id, company_id, employee_id,details))
      } else {
        None
      }
    } finally {
      conn.close()
    }
  }


  def TfindById(cid: String,eid:String): Option[UserModels] = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement()
      val rs = stmt.executeQuery(s"SELECT * FROM emp WHERE company_id='${cid}' and employee_id='${eid}'")
      if (rs.next()) {
        val id = rs.getInt("id")
        val company_id = rs.getString("company_id")
        val employee_id = rs.getString("employee_id")
        val details = rs.getString("details")
        Some(UserModels(id, company_id, employee_id, details))
      } else {
        None
      }
    } finally {
      conn.close()
    }
  }



    def create(cid:String,eid:String,details:String): Unit = {
      val conn = db.getConnection()
      try {
        val stmt = conn.createStatement()
        stmt.executeUpdate(s"INSERT INTO emp (company_id, employee_id,details) VALUES ('${cid}', '${eid}','${details}')")
      } finally {
        conn.close()
      }
    }

    def update(cid:String,eid:String,details:String): Unit = {
      val conn = db.getConnection()
      try {
        val stmt = conn.createStatement()
        stmt.executeUpdate(s"UPDATE emp SET details='${details}' WHERE company_id='${cid}' and employee_id ='${eid}'")
      } finally {
        conn.close()
      }
    }

  def delete(id: Int): Unit = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement()
      stmt.executeUpdate(s"DELETE FROM emp WHERE id=$id")
    } finally {
      conn.close()
    }
  }

}