/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jis.judiciary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apiiit123
 */
public class UpdateCaseServlet extends HttpServlet {
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
            out.println("<title>Servlet UpdateCaseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCaseServlet at " + request.getContextPath() + "</h1>");
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

        // Prepare the SQL UPDATE statement for cases
        String query = "UPDATE cases SET caseTitle = ?, caseType = ?, description = ?, filedDate = ?, status = ?, assignedTo = ? WHERE caseNumber = ?";
        pstmt = conn.prepareStatement(query);

        // Set parameters for the update query
        pstmt.setString(1, caseTitle);
        pstmt.setString(2, caseType);
        pstmt.setString(3, description);
        pstmt.setString(4, filedDate);
        pstmt.setString(5, status);
        pstmt.setString(6, assignedTo);
        pstmt.setString(7, caseNumber);  // This is the condition to find the specific case

        // Execute the statement and check for affected rows
        int rowsAffected = pstmt.executeUpdate();  // Correct method for updates
        out.println("<html><body>");
        if (rowsAffected > 0) {
//            out.println("<h1>Case was updated successfully!</h1>");
                response.sendRedirect("viewCases.jsp");
            
        } else {
            out.println("<h1>Error: No case was updated.</h1>");
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
