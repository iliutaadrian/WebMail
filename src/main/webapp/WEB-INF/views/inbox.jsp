<!DOCTYPE html>
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
                        <li><a href="#"><img  class="img-circle" alt="Cinque Terre" style="width: 200px; height: auto;" src=${profile}>
                        </a></li>
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

<div class="container text-center" id="tasksDiv">
    <h3>Inbox</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th style="text-align: center">Marked</th>
                <th style="text-align: center">Details</th>
                <th style="text-align: center">Sender</th>
                <th style="text-align: center">Subject</th>
                <th style="text-align: center">Message</th>
                <th style="text-align: center">Date</th>
                <th style="text-align: center">Delete</th>
            </tr>

            </thead>
            <tbody>
            <pr:forEach var="email" items="${emails}">
                <tr>
                    <td><a href="/marked-mail?idMail=${email.idMail}&call=${"inbox"}"><span
                        class="glyphicon glyphicon-bookmark"></span></a></td>
                    <td><a href="/details-mail?idMail=${email.idMail}"><span
                            class="glyphicon glyphicon-info-sign"></span></a></td>
                    <td>${email.addressSender}</td>
                    <td>${email.subjectMail}</td>
                    <td>${email.bodyMail}</td>
                    <td>${email.dateSend}</td>
                    <td><a href="/delete-mail?idMail=${email.idMail}&call=${"inbox"}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </pr:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
