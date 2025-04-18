<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>

    <div class="login-container">
      <form method="post" action="login">
        <label>
          Username:
          <input type="text" name="user" />
        </label>

        <label>
          Password:
          <input type="password" name="pass" />
        </label>

        <button type="submit">Login</button>
      </form>
    </div>

    <style>
      .login-container {
        max-width: 320px;
        margin: 40px auto;
        padding: 16px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-family: Arial, sans-serif;
      }

      .login-container label {
        display: block;
        margin-bottom: 12px;
      }

      .login-container input[type="text"],
      .login-container input[type="password"] {
        width: 100%;
        padding: 6px;
        box-sizing: border-box;
        border: 1px solid #aaa;
        border-radius: 4px;
        margin-top: 4px;
      }

      .login-container button {
        margin-top: 16px;
        width: 100%;
        padding: 8px;
        border: none;
        border-radius: 4px;
        background-color: #007bff;
        color: white;
        font-size: 14px;
        cursor: pointer;
      }

      .login-container button:hover {
        background-color: #0056b3;
      }
    </style>

  </body>
</html>
