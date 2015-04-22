<%@page import="sun.dc.DuctusRenderingEngine"%>
<%@page import ="com.FCI.SWE.Controller.UserController" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>

<style>
body {
    background-color: #FFFACD;
}
</style>
</head>
<body>
<p> <h1>Welcome </h1> </p>

<c:forEach items="${it.data}" var="data">

<p> <h1>post from : <c:out value="${data}"> 
</c:out></h1> </p>
</c:forEach>




<form action="/social/reply" method="POST">
Reply  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="msg" /> <br>
<input type="hidden"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Title" value="${it.data}"/> <br>

  <input type="submit" style="margin-left:27%; margin-right:21%;border-radius:40px;width: 200px; background: #FFFACD;
       color: #000000;  font-size: 150%; " value="Send"/>
       
</form>



</body>
</html>