using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace System.Web.Mvc
{
    static public class UrlHelp
    {
        public static string Script(this HtmlHelper helper, string path)
        {
            if (helper.ViewContext.HttpContext.IsDebuggingEnabled)
            {
                return path;
            }
            else
            {
                return path.Replace("/scripts/", "/scripts-pub/");
            }
        }
    }
}