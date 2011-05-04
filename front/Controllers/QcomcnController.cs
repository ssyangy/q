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
    }
}
