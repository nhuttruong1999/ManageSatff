<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
       
        <center>
            <form action="login.htm" modelAttribute="user" method="POST">
                <div id="aaa">
                    ${thongbao}
                    <h1 style="color: white">LOGIN</h1>     
                <table style="width: 20%" class="table table-condensed">
                    <tr>
                        <th style="color: white"> Username:
                            <input type="text" style="color: black" name="username" placeholder="username"/><br/></th>
                    </tr>
                    <tr>
                        <th style="color: white">Password:
                            <input type="password" style="color: black" name="password" placeholder="password"/></th>
                    </tr>
                    <tr>
                        <th><input type="submit" class="btn btn-success" value="Login" style="width: 100%" name="Action"/></th>
                    </tr>
                    
                </table>
                </div>
            </form>
        </center>
        
    </body>
</html>
<style>
    #aaa {
        padding-top: 5%;
        width: 400px;
      height: 400px;
      margin-top: 15%;
      border-radius: 10px;
      background-color: slategrey;
      box-shadow: 1px 1px 10px #FFF;
    }
    body{
        background-image: url('images/br.jpg') ;
        background-size: 100% ;
    }
    
</style>
