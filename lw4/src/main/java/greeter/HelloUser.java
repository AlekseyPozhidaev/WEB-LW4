package greeter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String lang = req.getParameter("lang");
        Locale locale;

        if ("ru".equalsIgnoreCase(lang)) {
            locale = new Locale("ru", "RU");
        } else {
            locale = new Locale("en", "US");
        }

        ResourceBundle res = ResourceBundle.getBundle("messages", locale);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Восстанавливаем нормальную HTML-разметку
        out.println("<html>");
        out.println("<head><meta charset=\"UTF-8\"><title>Greeting</title></head>");
        out.println("<body>");
        
        out.println("<h1>");
        String greeting = res.getString("greeting");
        out.print(greeting); // В Java 17 конвертация кодировки НЕ нужна!
        out.println(", " + name + "!</h1>");
        
        out.println("<p>");
        String message = res.getString("message");
        out.print(message);
        out.println("</p>");
        
        out.println("<p>");
        // Формируем правильную HTML-ссылку для переключения языка
        String nextLang = "ru".equals(lang) ? "en" : "ru";
        out.println("<a href=\"?lang=" + nextLang + "&name=" + name + "\">");
        
        String btnText = res.getString("lang_button");
        out.print(btnText);
        
        out.println("</a>");
        out.println("</p>");
        
        out.println("</body>");
        out.println("</html>");
    }
}