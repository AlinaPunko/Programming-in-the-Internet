import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DbConnector", urlPatterns = "/database")
public class DbConnector extends HttpServlet {

    private Connection connection;
    private String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "database=WSSPAA;"
                    + "user=sa;"
                    + "password=123;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            try {
                Integer id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                CallableStatement cstmt = null;
                int countRows = 0;

                try {
                    if (!connection.isClosed()) {
                        cstmt = connection.prepareCall(
                                "{call dbo.insertToT1 (?, ?, ?)}");
                        cstmt.setInt("id", id);
                        cstmt.setString("name", name);
                        cstmt.registerOutParameter("countRows", Types.INTEGER);
                        cstmt.executeUpdate();
                        countRows = cstmt.getInt("countRows");
                        response.getWriter().println(countRows);
                    }
                } catch (SQLException s) {
                    s.printStackTrace();
                    response.getWriter()
                            .println(s.getMessage());
                }

                cstmt.close();
            } catch (Exception e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                response.getWriter()
                        .println(e.getMessage());
            }
        } finally {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Integer id;
        String name;
        String query;

        query = "SELECT * FROM Table_1";

        if (request.getParameter("id") != null && request.getParameter("name") == null) {
            id = Integer.parseInt(request.getParameter("id"));
            query = String.format("SELECT * FROM Table_1 WHERE id=%d", id);
        }

        if (request.getParameter("name") != null && request.getParameter("id") == null) {
            name = request.getParameter("name");
            query = String.format("SELECT * FROM Table_1 WHERE name='%s'", name);
        }

        if (request.getParameter("id") != null && request.getParameter("name") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            query = String.format("SELECT * FROM Table_1 WHERE id=%d && name='%s'", id, name);
        }

        Statement stmt = null;
        try {
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                StringBuilder resultMessage = new StringBuilder();
                while (rs.next()) {
                    resultMessage
                            .append("<div>")
                            .append(rs.getInt("id")).append("   ").append(rs.getString("name"))
                            .append("</div>");
                }
                // close ResultSet rs
                rs.close();
                System.out.println(resultMessage);
                response.getWriter().println(resultMessage);
            } catch (SQLException s) {
                s.printStackTrace();
                response.getWriter()
                        .println(s.getMessage());
            }

            stmt.close();
        } catch (Exception e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            response.getWriter()
                    .println(e.getMessage());
        }
    }

        @Override
        public void destroy() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}