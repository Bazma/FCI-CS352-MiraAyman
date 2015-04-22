<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
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

<form action="/social/AddChat" method="POST">
 Conversation Name  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Title" /> <br>
 Friend Email   : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="EmailFriend" /> <br>
  <input type="submit" style="margin-left:27%; margin-right:21%;border-radius:40px;width: 200px; background: #FFFACD;
       color: #000000;  font-size: 150%; " value="Add"/>
       
</form>



</body>
</html>