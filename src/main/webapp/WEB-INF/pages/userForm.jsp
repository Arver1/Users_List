<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                Users Details
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="userRegisterForm" cssClass="form-horizontal" modelAttribute="user" method="post" action="saveUser">

                <div class="form-group">
                    <div class="control-label col-xs-3"> <form:label path="name" >Name</form:label> </div>
                    <div class="col-xs-6">
                        <form:hidden path="id" value="${userObject.id}"/>
                        <form:input cssClass="form-control" path="name" value="${userObject.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="age" cssClass="control-label col-xs-3">Age</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="age" value="${userObject.age}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="admin">isAdmin</form:label></div>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="admin" value="${userObject.admin}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="createdDate">createdDate</form:label></div>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="createdDate" value="${userObject.createdDate}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveUser" class="btn btn-primary" value="Save" onclick="return submitUserForm();"/>
                        </div>
                        <div class="col-xs-4">
                        </div>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function submitUserForm() {
        var createdDate = $('#createdDate').val();
        // getting the employee form values
        var name = $('#name').val().trim();
        var age = $('#age').val();
        var salary = $('#salary').val();
        var isAdmin = $('#admin').val();
        var regex = /^[a-zA-Z ]{1,25}$/;
        //  var ru_regex = /^[А-ЯЁ][а-яё]{1,25}$/;
        if (name.length == 0 || !regex.test(name) || name.length>25) {
            //   if (!ru_regex.test(name)) {

            alert('Please enter proper name : only English letters ');
            $('#name').focus();
            return false;
            //  }
        }


        function isValid(dateString) {
            var minDate = new Date('1970-01-01 00:00:01');
            var maxDate = new Date('2038-01-19 03:14:07');
            var date = new Date(dateString);
            return date > minDate && date < maxDate;
        }
        if(!isValid(createdDate))
        {
            alert('Please enter proper Date');
            $('#createdDate').focus();
            return false;
        }



        if(age<=0 || isNaN(age) || age>100) {
            alert('Please enter proper age');
            $('#age').focus();
            return false;
        }


        if(isAdmin != 'true' && isAdmin != 'false') {
            alert('Please enter proper isAdmin: true/false');
            $('#admin').focus();
            return false;
        }
        return true;
    };
</script>

</body>
</html>