package pl.dominisz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.endsWith("/readcookies")) {
            readCookies(req, resp);
        } else if (requestURI.endsWith("/deletecookies")) {
            deleteCookies(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.endsWith("/createcookies")) {
            createCookies(req, resp);
        } else if (requestURI.endsWith("/readcookies")) {
            readCookies(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.endsWith("/deletecookies")) {
            deleteCookies(resp);
        }
    }

    private void createCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createCookie("language", req, resp);
        createCookie("background-color", req, resp);
        createCookie("text-color", req, resp);
        createCookie("text-size", req, resp);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3>cookies saved</h3>");
    }

    private void createCookie(String parameter, HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter(parameter);
        Cookie cookie = new Cookie(parameter, value);
        resp.addCookie(cookie);
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

    private static final String[] COOKIE_NAMES = {
            "language",
            "background-color",
            "text-color",
            "text-size"
    };

    private void deleteCookies(HttpServletResponse resp) {
        for (String cookie : COOKIE_NAMES){
            deleteCookie(cookie, resp);
        }
    }

    private void deleteCookie(String name, HttpServletResponse resp) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
