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

<form action="/social/CreatePost" method="POST">
 Post content  : <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Content" /> <br>
 Tag :     <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Tag" /> <br>
 HashTag :     <input type="text"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10% " name="Hash" /> <br>
 Feeling: 
           <select name="Feeling"  style="border-radius:20px;border:3px solid black ; width: 200px;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10%">
                  <option > great </option>
				  <option > lucky</option>
				  <option > thankful</option>
				  <option > important </option>
				  <option > wonderful</option>
				  <option > optimistic</option>
				  <option > ALIVE </option>
				  <option > calm</option>
				  <option > HAPPY</option>
				  <option >clever</option>
				  <option > quiet</option>
				  <option > relaxed</option>
				  <option > great </option>
				  <option > GOOD</option>
				  <option > encouraged</option>
				  <option > angry</option>
				  <option > crazy </option>
				  <option > concerned</option>
				  <option > caring</option>
				  <option > dislike</option>
				  <option > like </option>
				  <option > lazy</option>
				  <option > optimism</option>
				  <option > love</option>
				  
		   </select>
  privacy: 
             <select name="privacy"  style="border-radius:20px;border:3px solid black ; width: 200px ;margin-top:5%; margin-bottom:3%; margin-left:20%; margin-right:10%">
                  <option > public </option>
				  <option > private</option>
				  <option > custom</option>
		     </select>
		     
		     
  <input type="submit" style="margin-left:27%; margin-right:22%;border-radius:45px;width: 200px; background: #FFFACD;
       color: #000000;  font-size: 150%; " value="Post"/>
     
</form>
</body>
</html>