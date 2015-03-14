
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

<p> <h1> List Of Request  </h1> </p>
<p> <h2> 1- ${it.emailto} </h2> </p>

 <form action="/social/acceptRequest" method="POST" style="margin: 80px ;font:40px/60px Arial; box-shadow:7px 7px 6px" >
    <input type="text"  style="border-radius:40px; border:3px solid black ;margin-top:5%; margin-bottom:3%; margin-left:19%; margin-right:10%;width:300px" name="emailto" />
    <input type="submit"  style=" border-radius:40px;margin-top:3%; margin-bottom:2%; margin-left:10%; margin-right:5%;width:300px; background: #1E90FF;
           color: #000000;  font-size: 100%; " value="accept Reguest"/>
 </form>
  
<form action="/social/rejectRequest" method="POST" style="margin: 80px ;font:40px/60px Arial; box-shadow:7px 7px 6px">
   <input type="text"   style="border-radius:40px; border:3px solid black ;margin-top:5%; margin-bottom:3%; margin-left:19%; margin-right:10%;width:300px"  name="emailto" />
   <input type="submit"  style="border-radius:40px; margin-top:3%; margin-bottom:2%; margin-left:10%; margin-right:5%;width:300px; background: #1E90FF;
          color: #000000;  font-size: 100%; " value="reject Reguest"/>
</form>
  
<form action="/social" method="POST" style="margin: 80px ;font:40px/60px Arial; box-shadow:7px 7px 6px">
   <input type="submit" style="border-radius:40px; margin-top:3%; margin-bottom:2%; margin-left:10%; margin-right:5%; width:300px;background: #1E90FF;
          color: #000000;  font-size: 100%; "  value="Log Out"/>
</form>
  
</body>
</html>