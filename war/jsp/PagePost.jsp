<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>

<style>
body {
    background-color: #1E90FF;
}
</style>
</head>
<body>

<form action="/social/CreatePagePost" method="POST">
 Post content  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Content" /> <br>
 Page Name  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Name" /> <br>
 privacy: 
             <select name="privacy"  style="border-radius:20px;border:3px solid black ; width: 200px ;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10%">
                  <option > public </option>
				  <option > private</option>
		     </select>
		     
		     
  <input type="submit" style="margin-left:27%; margin-right:22%;border-radius:45px;width: 200px; background: #FFFACD;
       color: #000000;  font-size: 150%; " value="Post"/>
     
</form>
</body>
</html>