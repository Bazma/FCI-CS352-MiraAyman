<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<p>aywa b2a </p>
<p> Welcome b2a ya ${it.emailto} </p>

 <form action="/social/acceptRequest" method="POST">
  <input type="submit" value="accept Reguest"/>
    <input type="text"   name="emailto" />
  </form>
  
 <form action="/social/rejectRequest" method="POST">
  <input type="submit" value="reject Reguest"/>
  </form>
  
</body>
</html>