<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Case Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        .table-container {
            width: 80%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            padding: 5px 10px;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            font-size: 0.9em;
        }
        .btn-edit {
            background-color: #4CAF50;
        }
        .btn-delete {
            background-color: #f44336;
        }
    </style>
</head>
<body>

<div class="table-container">
    <h2>Case Details</h2>
    <table>
        <thead>
            <tr>
                <th>Case Number</th>
                <th>Case Title</th>
                <th>Case Type</th>
                <th>Description</th>
                <th>Filed Date</th>
                <th>Status</th>
                <th>Assigned To</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
                String DB_USER = "root";
                String DB_PASSWORD = "prasadk1729";

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    String query = "SELECT caseNumber, caseTitle, caseType, description, filedDate, status, assignedTo FROM cases";
                    pstmt = conn.prepareStatement(query);
                    rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String caseNumber = rs.getString("caseNumber");
                        String caseTitle = rs.getString("caseTitle");
                        String caseType = rs.getString("caseType");
                        String description = rs.getString("description");
                        Date filedDate = rs.getDate("filedDate");
                        String status = rs.getString("status");
                        String assignedTo = rs.getString("assignedTo");
            %>
            <tr>
                <td><%= caseNumber %></td>
                <td><%= caseTitle %></td>
                <td><%= caseType %></td>
                <td><%= description %></td>
                <td><%= filedDate != null ? filedDate.toString() : "N/A" %></td>
                <td><%= status %></td>
                <td><%= assignedTo %></td>
                <td>
                    <a href="editCase.jsp?caseNumber=<%= caseNumber %>" class="btn btn-edit">Edit</a>
                    <a href="deleteCase.jsp?caseNumber=<%= caseNumber %>" class="btn btn-delete">Delete</a>
                </td>
            </tr>
            <%
                    }
                } catch (ClassNotFoundException e) {
                    out.println("<h3>Error: JDBC Driver not found!</h3>");
                    e.printStackTrace();
                } catch (SQLException e) {
                    out.println("<h3>Error: " + e.getMessage() + "</h3>");
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
