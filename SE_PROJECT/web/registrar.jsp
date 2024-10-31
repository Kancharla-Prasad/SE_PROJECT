<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Page</title>
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

        p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to the Registrar Page</h1>
    </header>
    <nav>
        <ul>
            <li><a href="home.jsp">Home</a></li>
            <li><a href="viewCases.jsp">View Cases</a></li>
            <li><a href="registerCase.jsp">Register New Case</a></li>
            <li><a href="caseHistory.jsp">Case History</a></li>
            <li><a href="manageAccount.jsp">Manage Account</a></li> <!-- New Manage Account link -->
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
    </nav>
    <section>
        <h2>Registrar Dashboard</h2>
        <p>Welcome, Registrar! Use the navigation links above to manage cases, account settings, and access relevant information.</p>
    </section>
</body>
</html>
