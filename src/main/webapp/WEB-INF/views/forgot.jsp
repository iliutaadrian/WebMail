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
    <h3>Forgot password</h3>
    <hr>
    <pr:choose>
        <pr:when test="${mode=='MODE_ERROR' }">
            <h5 style="color: red">Wrong info for recover password.</h5>
            <hr>
        </pr:when>
    </pr:choose>

    <pr:choose>
        <pr:when test="${mode=='MODE_OK' }">
            <h5 style="color: red">Password was sent to recovery address.</h5>
            <hr>
        </pr:when>
    </pr:choose>


    <form class="form-horizontal" method="POST" action="/forgot-user">
        <div class="form-group">
            <label class="control-label col-md-4">Email Address</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="addressAccount"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-4">Recovery Email Address</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="recoveryEmail" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-4">First Name</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="firstName" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-4">Last Name</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="lastName" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-4">Phone Number </label>
            <div class="col-md-5">
                <input type="number" class="form-control" name="phoneNumber"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-4">What's you lucky number? </label>
            <div class="col-md-5">
                <input type="number" class="form-control" name="luckyNumber" />
            </div>
        </div>

        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Change password" />
        </div>
    </form>
</div>

</body>
</html>
