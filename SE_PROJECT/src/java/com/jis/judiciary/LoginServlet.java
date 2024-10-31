package com.jis.judiciary;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author rushendra
 */
public class LoginServlet extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "prasadk1729";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
        @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String role = request.getParameter("role").toLowerCase(); // Assuming role is part of the form

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;

    try {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish a connection to the database
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Prepare the SQL query to validate user
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        pstmt.setString(3, role);

        // Execute the query
        result = pstmt.executeQuery();

        if (result.next()) {
            // User found, create a session
//            HttpSession session = request.getSession();
//            session.setAttribute("userEmail", email); // Store user email in session
//            session.setAttribute("userRole", role);   // Store user role in session
            
            // Optionally store other user details as needed
//            session.setAttribute("userName", result.getString("username")); // Assuming you have a username column

            // Redirect to the appropriate page based on role
            request.getRequestDispatcher(role + ".jsp").forward(request, response);
        } else {
            // Invalid credentials
            out.println("<html><body>");
            out.println("<h1>No user found or invalid credentials!</h1>");
            out.println("<a href='login.jsp'>Try Again</a>");
            out.println("</body></html>");
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
    } finally {
        // Close resources
        try {
            if (result != null) result.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//    @Override
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String role = request.getParameter("role").toLowerCase();
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        Statement statement = null;
//        ResultSet result = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            // Step 3: Create the SQL query
////            String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
//
////                String query = "SELECT * FROM use"
//            // Step 4: Prepare the statement
//            String query = "SELECT * FROM users WHERE email = '"+email+"' and password='"+password+"' and role='"+role+"'";
//            
////            statement = conn.createStatement();
////            statement.setString(1,email);
//               pstmt = conn.prepareStatement(query);
////            pstmt.setString(1,email);
////            pstmt.setString(1, username);
////            pstmt.setString(2, email);
//
//            // Step 5: Execute the query
//            
//            result = pstmt.executeQuery();
//            
//            out.println("<html><body>");
//            if(result.next()){
////                out.println("Role"+role);
//
//                    
//                   request.getRequestDispatcher(role+".jsp").forward(request, response);
////                   out.println("User Data checked");
//            }
//            else{
//                out.println("<h1>No user found</h1>");
//            }
////            while(result.next()){
////                out.println("<p>"+result.getString("email")+"&nbsp;"+result.getString("password")+"</p>");
////            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            out.println("<h3>Error: " + e.getMessage() + "</h3>");
//        } finally {
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


















//package com.jis.judiciary;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.*;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author rushendra
// */
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "prasadk1729";
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role").toLowerCase(); // Assuming role is part of the form
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet result = null;
//
//        try {
//            // Load JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // Establish a connection to the database
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            // Prepare the SQL query to validate user
//            String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = ?";
//            pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, email);
//            pstmt.setString(2, password);
//            pstmt.setString(3, role);
//
//            // Execute the query
//            result = pstmt.executeQuery();
//
//            if (result.next()) {
//                // User found, create a session
//                HttpSession session = request.getSession();
//                session.setAttribute("userEmail", email); // Store user email in session
//                session.setAttribute("userRole", role);   // Store user role in session
//
//                // Redirect to the appropriate page based on role
//                request.getRequestDispatcher(role + ".jsp").forward(request, response);
//            } else {
//                // Invalid credentials
//                out.println("<html><body>");
//                out.println("<h1>No user found or invalid credentials!</h1>");
//                out.println("<a href='login.jsp'>Try Again</a>");
//                out.println("</body></html>");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            out.println("<h3>Error: " + e.getMessage() + "</h3>");
//        } finally {
//            // Close resources
//            try {
//                if (result != null) result.close();
//                if (pstmt != null) pstmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Login servlet for user authentication";
//    }
//}
