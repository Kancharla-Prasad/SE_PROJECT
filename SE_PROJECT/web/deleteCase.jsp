<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Case</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Optional: Link to a CSS file for styling -->
</head>
<body>
    <h2>Delete Case</h2>
    
    <%
        // Get case number from the request
        String caseNumber = request.getParameter("caseNumber");
        if (caseNumber == null || caseNumber.isEmpty()) {
            out.println("<h3>Error: Case number is required!</h3>");
            return;
        }
    %>

    <p>Are you sure you want to delete case number <strong><%= caseNumber %></strong>?</p>

    <form action="DeleteCaseServlet" method="post">
        <input type="hidden" name="caseNumber" value="<%= caseNumber %>">
        <input type="submit" value="Delete Case">
        <a href="viewCases.jsp">Cancel</a>
    </form>
</body>
</html>
