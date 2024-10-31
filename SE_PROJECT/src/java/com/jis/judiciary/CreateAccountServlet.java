package com.jis.judiciary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "prasadk1729";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve parameters from the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Retrieve role before converting to lowercase
        
        // Ensure that email, password, and role are not null or empty
        if (email == null || password == null || role == null || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            out.println("<html><body>");
            out.println("<h1>Error: All fields are required!</h1>");
            out.println("</body></html>");
            return;
        }

        // Convert role to lowercase safely
        role = role.toLowerCase();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL INSERT statement
            String query = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            
            // Execute the statement and check for affected rows
            int rowsAffected = pstmt.executeUpdate();
            out.println("<html><body>");
            if (rowsAffected > 0) {
                out.println("<h1>User was created successfully!</h1>");
                out.println("<p>Role: " + role + "</p>");
            } else {
                out.println("<h1>Error: No user was created.</h1>");
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
