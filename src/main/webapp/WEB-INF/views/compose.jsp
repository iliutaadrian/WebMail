<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="pr"%>
<html>
<head>
    <meta charset="utf-8">

    <title>WebMail</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
<div role="navigation">
    <div class="navbar navbar-inverse">
        <a href="#" class="navbar-brand">WebMail</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/compose"><span class="glyphicon glyphicon-pencil"></span>  Compose</a></li>
                <li><a href="/inbox"><span class="glyphicon glyphicon-envelope"></span>  Inbox</a></li>
                <li><a href="/sent"><span class="glyphicon glyphicon-upload"></span>  Sent</a></li>
                <li><a href="/marked"><span class="glyphicon glyphicon-heart"></span>  Favourites</a></li>
                <li><a href="/anonymous"><span class="glyphicon glyphicon-question-sign"></span>  Anonymous</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-user"></span> Account: ${account}
                        <span class="caret"></span>
                    </a>

                    <ul style="width: 250px; text-align: center" class="dropdown-menu">
                        <li><a href="#"><img  class="img-circle" alt="Cinque Terre" style="width: 200px; height: auto;" src=${profile}></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#">First Name: <b>${firstName}</b></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#">Last Name: <b>${lastName}</b></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#">Phone: <b>${phone}</b></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="/change"><b>Change password</b></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </li>

                <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <h3>Compose</h3>
    <hr>
    <pr:choose>
        <pr:when test="${mode=='MODE_ERROR' }">
            <h5 style="color: red">Wrong receiver password info</h5>
            <hr>
        </pr:when>
    </pr:choose>
    <form class="form-horizontal" method="POST" action="/send-email">

        <div class="form-group">
            <label class="control-label col-md-2">To</label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="addressReceiver" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Subject</label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="subjectMail"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Message</label>
            <div class="col-md-8" >
                <textarea  style="height: 250px; resize: none" class="form-control" name="bodyMail"></textarea>
            </div>
        </div>

        <div class="checkbox" style="margin-bottom: 10px">
            <label><input type="checkbox" name="anonymous">Send anonymous</label>
        </div>

        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Send" />
        </div>
    </form>
</div>
</body>
</html>
