<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Accounts</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body */
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            color: #333;
        }

        /* Header */
        header {
            background: #007BFF;
            color: white;
            padding: 20px;
            text-align: center;
        }

        /* Navigation */
        nav {
            background: #333;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            padding: 10px 0;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            transition: background 0.3s;
        }

        nav ul li a:hover {
            background: #575757;
        }

        /* Section */
        section {
            padding: 20px;
            max-width: 800px;
            margin: auto;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        button {
            background: #007BFF;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background: #0056b3;
        }

        /* User List Table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background: #f2f2f2;
        }

        /* Message Styles */
        p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Managing Accounts by Registrar</h1>
    </header>
    <nav>
        <ul>
            <li><a href="home.jsp">Home</a></li>
            <li><a href="viewCases.jsp">View Cases</a></li>
            <li><a href="registerCase.jsp">Register New Case</a></li>
            <li><a href="caseHistory.jsp">Case History</a></li>
            <li><a href="manageAccount.jsp">Manage Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
    </nav>
    
    <section>
        <h2>Manage Accounts</h2>
        <p>Use the options below to manage user accounts in the Judiciary Information System.</p>

        <!-- Form to add a new user -->
        <h3>Create New User</h3>
        <form action="CreateAccountServlet" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="registrar">Registrar</option>
                <option value="judge">Judge</option>
                <option value="culprit">Culprit</option>
                <option value="lawyer">Lawyer</option>
                <!-- Add other roles as needed -->
            </select>
            
            <button type="submit">Add User</button>
        </form>

        <!-- Section to retrieve all users -->
        <h3>Retrieve Users</h3>
        <form action="viewAllUsers.jsp" method="get">
            <a href="viewAllUsers.jsp"><button> All Users </button> </a>
        </form>

        <!-- Area to display user information in a table format -->
       
    </section>
</body>
</html>
