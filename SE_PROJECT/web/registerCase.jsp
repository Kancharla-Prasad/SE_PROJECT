<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enter Case Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-container {
            width: 60%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            text-align: center;
        }
        .form-group {
            margin: 15px 0;
        }
        .form-group label {
            display: block;
            font-weight: bold;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .read-only {
            background-color: #f9f9f9; /* Light gray background for read-only fields */
            border: 1px solid #ccc;
            color: #555; /* Darker text color */
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Case Entry Form</h2>
        <form action="CaseServlet" method="post">
            <div class="form-group">
                <label for="caseNumber">Case Number:</label>
                <input type="text" id="caseNumber" name="caseNumber" required>
            </div>
            <div class="form-group">
                <label for="caseTitle">Case Title:</label>
                <input type="text" id="caseTitle" name="caseTitle" required>
            </div>
            <div class="form-group">
                <label for="caseType">Case Type:</label>
                <select id="caseType" name="caseType" required>
                    <option value="Civil">Civil</option>
                    <option value="Criminal">Criminal</option>
                    <option value="Family">Family</option>
                    <option value="Labor">Labor</option>
                    <!-- Add more case types as needed -->
                </select>
            </div>
            <div class="form-group">
                <label for="filedDate">Filed Date:</label>
                <input type="date" id="filedDate" name="filedDate" required>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status" required>
                    <option value="Pending">Pending</option>
                    <option value="Ongoing">Ongoing</option>
                    <option value="Closed">Closed</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="assignedTo">Assigned To (Judge's Email):</label>
                <input type="email" id="assignedTo" name="assignedTo" required>
            </div>
            
            <div class="form-group">
                <button type="submit">Submit Case</button>
            </div>
        </form>
    </div>
</body>
</html>
