using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace front.Controllers
{
    public class QcomcnController : Controller
    {
        public ActionResult Quanzi()
        {
            ViewBag.Message = "Welcome to Qcomcn!";

            return View();
        }
    }
}
