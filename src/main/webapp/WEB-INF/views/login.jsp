<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="pr"%>
<html>
<head>
    <meta charset="utf-8">

    <title>WebMail</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
<body>
<div role="navigation">
    <div class="navbar navbar-inverse">
        <a href="#" class="navbar-brand">WebMail</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/"><span class="glyphicon glyphicon-home"></span>  Home</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span>  Login</a></li>
                <li><a href="/register"><span class="glyphicon glyphicon-user"></span>  Register</a></li>
                <li><a href="/forgot"><span class="glyphicon glyphicon-question-sign"></span>  Forgot password</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/about">About  <span class="glyphicon glyphicon-signal"></span></a> </li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <h3>User Login</h3>
    <hr>

    <pr:choose>
        <pr:when test="${mode=='MODE_ERROR' }">
            <h5 style="color: red">Wrong login info</h5>
        <hr>
        </pr:when>
    </pr:choose>

    <form class="form-horizontal" method="POST" action="/login-user">
        <div class="form-group">
            <label class="control-label col-md-4">Username</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="addressAccount" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4">Password</label>
            <div class="col-md-5">
                <input type="password" class="form-control" name="passwordAccount" />
            </div>
        </div>
        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Login" />
        </div>
    </form>
</div>

</body>
</html>
