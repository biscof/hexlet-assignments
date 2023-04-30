package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get("build/resources/main/users.json").normalize();
        return objectMapper.readValue(
                Files.readString(path), new TypeReference<>() {});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        PrintWriter printWriter = response.getWriter();
        StringBuilder builder = new StringBuilder();

        builder.append("""
                <!doctype html>
                <html lang=\"en\">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                </head>
                <body>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">fullName</th>
                        </tr>
                        </thead>
                        <tbody>
                """);

        for (Map<String, String> user : users) {
            builder.append("<tr>")
                    .append("<td>")
                    .append(user.get("id"))
                    .append("</td>")
                    .append("<td>")
                    .append(String.format("<a href=\"/users/%s\">", user.get("id")))
                    .append(user.get("firstName"))
                    .append(" ")
                    .append(user.get("lastName"))
                    .append("</a>")
                    .append("</td>")
                    .append("</tr>");
        }

        builder.append("</tbody></table></body></html>");
        printWriter.print(builder);
        printWriter.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {
        // BEGIN
        List<Map<String, String>> users = getUsers();
        PrintWriter printWriter = response.getWriter();
        StringBuilder builder = new StringBuilder();

        builder.append("""
                <!doctype html>
                <html lang=\"en\">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                </head>
                <body>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">firstName</th>
                            <th scope="col">lastName</th>
                            <th scope="col">email</th>
                        </tr>
                        </thead>
                        <tbody>
                """);

        for (Map<String, String> user : users) {
            if (user.get("id").equals(id)) {
                builder.append(String.format("""
                        <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                        </tr>
                        """,
                        user.get("id"),
                        user.get("firstName"),
                        user.get("lastName"),
                        user.get("email")
                        ));
                builder.append("</tbody></table></body></html>");
                printWriter.print(builder);
                printWriter.close();
                return;
            }
        }

        response.sendError(404, "Not found");

        // END
    }
}
