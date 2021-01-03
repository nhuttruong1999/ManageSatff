
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to ASM-JAVA 5</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </head>

    <body>
        
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="home.htm"><img src="images/Logo.png" width="80px" height="80px"/></a>
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="home.htm">Home <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#3" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Manage
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="showAccount.htm">Show Account</a>
                        <a class="dropdown-item" href="showDepart.htm">Show Depart</a>
                        <a class="dropdown-item" href="showStaff.htm">Show Staff</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#3" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Statistics
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown"> 
                        <a class="dropdown-item" href="showStatisticsTOP10.htm">TOP 10 Best Player</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="showStatistics.htm">ALL Statistics</a>
                    </div>
                </li> 
                <li style="position: relative; left: 1100px ; padding-top: 2%; " class="nav-item">
                     <b style="color: white;">xinchao,${tenuser}</b> <a href="login.htm" >Exit</a>
                </li>
            </ul>
        </div>
    </nav>  
    <h1 align="center">List Depart</h1>
    <br>
    <form action="findDepart.htm" method="post">
        Find: <input type="text" name="Departid" placeholder="Depart-ID"/> 
        <input type="submit" class="btn btn-success" value="find" name="find"/>
    </form>
    <br>
    ${thongbaoDepart}
    <form action="deleteallDepart.htm" modelAttribute="depart" enctype="multipart/form-data" method="post">
        <table class="table">
            <tr class="success">
                <th>Depart-Id</th>
                <th>Depart-Name</th>
                <th>Depart-Image</th>
                <th>Delete-Status</th>
                <th>Update</th>
                <th>Delete</th>
                <th>Select</th>
            </tr>
            <c:forEach var="d" items="${Depart}">
                <tr>
                    <td>${d.getDepartid()}</td>
                    <td>${d.getDepartname()}</td>                                      
                    <td><img src="images/${d.getDepartimg()}" width="200px" height="200px"/></td> 
                    <td>${d.getDelete()}</td>                                      
                    <td><a href="updateDepart.htm?thongtinDepart=${d.getDepartid()}" class="btn btn-success">UPDATE</a></td>
                    <td><a href="deleteDepart.htm?xoathongtinDepart=${d.getDepartid()}" class="btn btn-success">DELETE</a></td>
                    <td><input type="checkbox" name="select" value="${d.getDepartid()}"/></td>
                </tr>
            </c:forEach>
        </table>
        <a href="insertDepart.htm" class="btn btn-success">INSERT</a>
        <input class="btn btn-success" type="submit" name="" value="DELETE ALL" onclick="check();" />   
    </form>    
    <br>

</body>
</html>

