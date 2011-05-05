using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace front.Controllers
{
    public class QcomcnController : Controller
    {
        public ActionResult Group()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult GroupCate()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult AtMe()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult Fav()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult ForgetPwd()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult SignIn()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult SignUp()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult Setting()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult Friends()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult Replies()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View();
        }
        public ActionResult MsgSend()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/message/MsgSend.cshtml");
        }
        public ActionResult MsgInbox()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/message/MsgInbox.cshtml");
        }
        public ActionResult MsgItem()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/message/MsgItem.cshtml");
        }
        public ActionResult SearchPeople()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/search/SearchPeople.cshtml");
        }
        public ActionResult SearchWeibo()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/search/SearchWeibo.cshtml");
        }
        public ActionResult SearchGroup()
        {
            ViewBag.Message = "Welcome to Qcomcn!";
            return View("~/views/qcomcn/search/SearchGroup.cshtml");
        }
    }
}
