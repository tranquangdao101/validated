<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <title>Validation Project</title>
    <head><h2>Student form validation</h2></head>
    <body>
  <form action="validation" method="post">
    <span class="error">${messages.foo}</span><br>
      <span class="error">${messages.bar}</span><br>
      <span class="succes">${messages.succes}</span><br>
    <input type="text" name="foo" value="${param.foo}" ${not empty messages.succes ? 'disabled' : ''}>
  
    <input type="text" name="bar" value="${param.bar}" ${not empty messages.succes ? 'disabled' : ''}>
  
    <input type="submit">
   
</form>
</body>
</html>