package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> filtered;
        List<String> companies = getCompanies();
        String queryString = request.getQueryString();
        PrintWriter pw = response.getWriter();

        if (queryString != null && queryString.contains("search")) {
            String strToSearch = request.getParameter("search");
            if (strToSearch.isEmpty()) {
                companies.forEach(pw::println);
            } else {
                filtered = companies.stream()
                        .filter(company -> company.toLowerCase().contains(strToSearch.toLowerCase()))
                        .toList();
                if (filtered.isEmpty()) {
                    pw.println("Companies not found");
                } else {
                    filtered.forEach(pw::println);
                }
            }
        } else {
            companies.forEach(pw::println);
        }
        pw.close();
        // END
    }
}
