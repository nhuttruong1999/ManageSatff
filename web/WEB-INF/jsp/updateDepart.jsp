<%-- 
    Document   : update
    Created on : Sep 24, 2020, 8:41:47 AM
    Author     : macosx
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </head>
    <body onload="load();">
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
                </ul>
            </div>
        </nav> 
        <br>    
    <center>
        <h1>UPDATE DEPART</h1>
        <table style="width: 35%" class="table table-condensed"> 
            <form action="updateDepart.htm" modelAttribute="depart" enctype="multipart/form-data" method="POST">
                <tr>
                    <th>
                        Depart-Id: 
                    </th>
                    <td>
                        <input type="text" name="departid" value="${depart1.getDepartid()}" />
                    </td>    
                </tr>
                <tr>
                    <th> 
                        Depart-Name:
                    </th>
                    <td>
                        <input type="text" name="departname" value="${depart1.getDepartname()}"/>
                    </td>    
                </tr>
                <tr>
                    <th> 
                        Choose-files: 
                    </th>
                    <td>
                        <input type="file" name="image" value="hinhcu"/>
                    </td>    
                </tr>
                <tr>
                    <th>
                        hinh-hien-tai:
                    </th> 
                    <td>
                        <img src="images/${depart1.getDepartimg()}" width="200px" height="200px"/>
                    </td>    
                </tr>
                <tr>
                    <th> 
                        Delete-Status: 
                    </th>
                    <td>
                        <select id="select12" name="DeleteStatus" >
                            <option  value="0">activated</option>
                            <option  value="1">not activated</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><input class="btn btn-success" type="submit" value="update" name="Action"/></th>
                </tr>   
            </form>              
        </table> 
    </center>    
</body>
</html>
<script>
    function load(){
        document.getElementById("select12").value="${value}";
    }
</script>
