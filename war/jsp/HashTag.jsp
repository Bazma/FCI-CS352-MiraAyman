<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>

<style>
body {background-color: #FFFACD;}
    form{
          margin-top:60px;
          margin-bottom:60px;
          margin-right:400px;
          margin-left:400px;
          border:3px solid black ;
          padding: 9px 35px;
          background: #6495ED;
          border-radius:20px;
          box-shadow:7px 7px 6px;
    }
</style>
</head>
<body>


<form action="/social/ViewHashTag" method="POST">
Name Of HashTag  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="HashTag" /> <br>
  <input type="submit" style="margin-left:27%; margin-right:21%;border-radius:40px;width: 200px; background: #FFFACD;
       color: #000000;  font-size: 150%; " value="Search"/>
       
</form>


</body>
</html>