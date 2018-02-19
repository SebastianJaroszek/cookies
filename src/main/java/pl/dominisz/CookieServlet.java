package pl.dominisz;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/createcookies", "/readcookies", "/deletecookies"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //TODO na podstawie requestURI wywołać jedną z poniższych metod
        String requestURI = req.getRequestURI();
        String path = req.getContextPath();

        if (requestURI.endsWith("/createcookies")) {
            createCookies(req, resp);
        } else if (requestURI.endsWith("/readcookies")) {
            readCookies(req, resp);
        } else if (requestURI.endsWith("/deletecookies")) {
            deleteCookies(req, resp);
        }


    }

    private void createCookies(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void readCookies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print("<h1>Cookies from request</h1>");
        Cookie[] cookies = req.getCookies();
        out.println("<ul>");
        for (Cookie cookie : cookies) {
            out.println("<li>");
            out.println("cookie name: " + cookie.getName());
            out.println("cookie value: " + cookie.getValue());
            out.println("cookie domain: " + cookie.getDomain());
            out.println("</li>");
        }
        out.println("</ul>");
    }

    private void deleteCookies(HttpServletRequest req, HttpServletResponse resp) {

    }
}
