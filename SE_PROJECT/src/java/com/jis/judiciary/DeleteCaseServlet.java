package com.jis.judiciary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCaseServlet extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "prasadk1729";

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve parameters from the request
        String caseNumber = request.getParameter("caseNumber");

        // Ensure that case details are not null or empty
        if (caseNumber == null || caseNumber.isEmpty()) {
            out.println("<html><body>");
            out.println("<h1>Error: Case number cannot be empty!</h1>");
            out.println("<a href='viewCases.jsp'>Go back to case list</a>");
            out.println("</body></html>");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL DELETE statement
            String query = "DELETE FROM cases WHERE caseNumber = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, caseNumber);

            // Execute the statement and check for affected rows
            int rowsAffected = pstmt.executeUpdate();
            out.println("<html><body>");
            if (rowsAffected > 0) {
                out.println("<h1>Case was deleted successfully!</h1>");
            } else {
                out.println("<h1>Error: No case found with the provided case number.</h1>");
            }
            out.println("<a href='viewCases.jsp'>Go back to case list</a>");
            out.println("</body></html>");

        } catch (ClassNotFoundException e) {
            out.println("<html><body><h3>Error: JDBC Driver not found!</h3></body></html>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
     
    @Override
    public String getServletInfo() {
        return "Servlet for creating user accounts in the Judiciary Information System.";
    }
}
