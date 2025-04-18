<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>JS 2 PDF</title>
  </head>

  <body>

    <div class="link-buttons">
      <a href="${pageContext.request.contextPath}/plan">View Plan</a>
      <a href="${pageContext.request.contextPath}/print">Open Plan page with HTTP client</a>
    </div>

    <style>
      .link-buttons {
        max-width: 400px;
        margin: 20px auto;
        font-family: Arial, sans-serif;
      }

      .link-buttons a {
        display: block;
        margin-bottom: 10px;
        padding: 10px 14px;
        text-decoration: none;
        background-color: #007bff;
        color: white;
        border-radius: 4px;
        text-align: center;
        transition: background-color 0.2s;
      }

      .link-buttons a:hover {
        background-color: #0056b3;
      }
    </style>
  </body>

</html>
