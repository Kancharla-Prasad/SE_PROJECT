package com.jis.judiciary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaseServlet extends HttpServlet {

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
        String caseTitle = request.getParameter("caseTitle");
        String caseType = request.getParameter("caseType");
        String description = request.getParameter("description");
        String filedDate = request.getParameter("filedDate");
        String status = request.getParameter("status");
        String assignedTo = request.getParameter("assignedTo");

        // Ensure that case details are not null or empty
        if (caseNumber == null || caseTitle == null || caseType == null || description == null ||
            filedDate == null || status == null || assignedTo == null ||
            caseNumber.isEmpty() || caseTitle.isEmpty() || caseType.isEmpty() || 
            description.isEmpty() || filedDate.isEmpty() || status.isEmpty() || assignedTo.isEmpty()) {
            out.println("<html><body>");
            out.println("<h1>Error: All fields are required!</h1>");
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

            // Prepare the SQL INSERT statement for cases
            String query = "INSERT INTO cases (caseNumber, caseTitle, caseType, description, filedDate, status, assignedTo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query); // Removed the incorrect cast
            pstmt.setString(1, caseNumber);
            pstmt.setString(2, caseTitle);
            pstmt.setString(3, caseType);
            pstmt.setString(4, description);
            pstmt.setString(5, filedDate);
            pstmt.setString(6, status);
            pstmt.setString(7, assignedTo);
            
            // Execute the statement and check for affected rows
            int rowsAffected = pstmt.executeUpdate();
            out.println("<html><body>");
            if (rowsAffected > 0) {
                out.println("<h1>Case was created successfully!</h1>");
            } else {
                out.println("<h1>Error: No case was created.</h1>");
            }
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
