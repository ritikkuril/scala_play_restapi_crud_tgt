package controllers

import javax.inject.Inject
import models.{UserDao, UserModels}

import play.api.libs.json._
import play.api.mvc._



class RestController @Inject()(cc: ControllerComponents, userDao: UserDao) extends AbstractController(cc) {

 def all(): Action[AnyContent] = Action { implicit request =>
    val users = userDao.all()
    Ok(Json.toJson(users))

  }

 def findById(id: Int): Action[AnyContent] = Action { implicit request =>
    val user = userDao.findById(id)
    user match {
      case Some(u) => Ok(Json.toJson(u))
      case None => NotFound
    }
  }

  def TfindById(cid:String,eid:String): Action[AnyContent] = Action { implicit request =>
    val user = userDao.TfindById(cid,eid)
    user match {
      case Some(u) => Ok(Json.toJson(u))
      case None => NotFound
    }
  }



 def create(cid:String,eid:String,details:String): Action[AnyContent] = Action  { implicit request =>

   userDao.create(cid,eid,details)
     Ok("Inserted")
   }

   def update(cid:String,eid:String,details:String): Action[AnyContent] = Action{ implicit request =>
     userDao.update(cid,eid,details)
     Ok("updated")
   }

  def delete(cid: String,eid:String): Action[AnyContent] = Action { implicit request =>
    userDao.delete(cid,eid)
    Ok("Deleted")
  }



}



