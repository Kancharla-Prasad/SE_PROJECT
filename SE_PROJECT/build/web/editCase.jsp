<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Case</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            width: 50%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"], select, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<%
    String caseNumber = request.getParameter("caseNumber");
    String DB_URL = "jdbc:mysql://localhost:3306/JudiciaryDB";
    String DB_USER = "root";
    String DB_PASSWORD = "prasadk1729";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String caseTitle = "";
    String caseType = "";
    String description = "";
    String filedDate = "";
    String status = "";
    String assignedTo = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        String query = "SELECT * FROM cases WHERE caseNumber = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, caseNumber);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            caseTitle = rs.getString("caseTitle");
            caseType = rs.getString("caseType");
            description = rs.getString("description");
            filedDate = rs.getDate("filedDate").toString();
            status = rs.getString("status");
            assignedTo = rs.getString("assignedTo");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }
%>

<div class="form-container">
    <h2>Edit Case</h2>
    <form action="UpdateCaseServlet" method="post">
        <input type="hidden" name="caseNumber" value="<%= caseNumber %>"/>

        <div class="form-group">
            <label for="caseTitle">Case Title:</label>
            <input type="text" id="caseTitle" name="caseTitle" value="<%= caseTitle %>" required>
        </div>

        <div class="form-group">
            <label for="caseType">Case Type:</label>
            <input type="text" id="caseType" name="caseType" value="<%= caseType %>" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required><%= description %></textarea>
        </div>

        <div class="form-group">
            <label for="filedDate">Filed Date:</label>
            <input type="text" id="filedDate" name="filedDate" value="<%= filedDate %>" readonly>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" id="status" name="status" value="<%= status %>" required>
        </div>

        <div class="form-group">
            <label for="assignedTo">Assigned To:</label>
            <input type="text" id="assignedTo" name="assignedTo" value="<%= assignedTo %>" required>
        </div>

        <button type="submit">Update Case</button>
    </form>
</div>

</body>
</html>
