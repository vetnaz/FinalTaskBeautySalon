<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 18.05.2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="appLocalizations.app"/>
<!DOCTYPE html>
<html>
<head>
    <head>
        <title><fmt:message key="header.logo_name"/></title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    </head>
</head>
<body>

<jsp:include page="parts/header.jsp"/>

    <div class="container">
        <h3 class="text-center fw-normal"><fmt:message key="registration.registration"/></h3>
        <form class="row g-3" method="post" action="${pageContext.request.contextPath}/controller">
            <div class="col-md-6">
                <label for="inputName" class="form-label"><fmt:message key="registration.name"/></label>
                <input name="name" type="text" class="form-control" id="inputName" pattern="^[^\s$/()]+$" maxlength="25" minlength="3" title="Use any symbols besides $/() and space. Length must be from 3 to 25 symbols." required>
            </div>
            <div class="col-md-6">
                <label for="inputSurname" class="form-label"><fmt:message key="registration.surname"/></label>
                <input name="surname" type="text" class="form-control" id="inputSurname" pattern="^[^\s$/()]+$" maxlength="25" minlength="3" title="Use any symbols besides $/() and space. Length must be from 3 to 25 symbols." required>
            </div>
            <div class="col-md-6">
                <label for="inputLogin" class="form-label" ><fmt:message key="registration.login"/></label>
                <input name="login" type="text" class="form-control" id="inputLogin" pattern="^[0-9a-zA-Z-+_]{3,25}$" maxlength="25" minlength="3" title="You can use latin symbols, figures,and -+_ . Length must be from 3 to 25 symbols." required>
            </div>
            <div class="col-md-6">
                <label for="inputPassword4" class="form-label"><fmt:message key="registration.password"/></label>
                <input name="password" type="password" class="form-control" id="inputPassword4" pattern="^[a-z0-9_-]{6,18}$" maxlength="18" minlength="6" title="You can use lowercase latin symbols, figures,and -_ . Length must be from 6 to 18 symbols." required>
            </div>
            <div class="col-12">
                <label for="inputEmail" class="form-label"><fmt:message key="registration.email"/></label>
                <input name="email" type="email" class="form-control" id="inputEmail" placeholder="email@email.com" pattern="^[^\s]+@[^\s]+\.[^\s]+$" maxlength="30" minlength="3" title="Use any symbols besides space. Length must be from 3 to 30 symbols." required>
            </div>
            <c:if test="${pageContext.request.getParameter(\"registration_message\") eq 'user_exist'}">
                <p class="text-center text-danger"><fmt:message key="registration.user_exist"/></p>
            </c:if>
            <c:if test="${pageContext.request.getParameter(\"registration_message\") eq 'have_problem'}">
                <p class="text-center text-danger"><fmt:message key="registration.try_later"/></p>
            </c:if>
            <c:if test="${pageContext.request.getParameter(\"registration_message\") eq 'incorrect_data'}">
                <p class="text-center text-danger"><fmt:message key="registration.incorrect_data"/></p>
            </c:if>


            <div class="col-12">
                <input type="hidden" name="command" value="saveUser">
                <button type="submit" class="btn btn-primary"><fmt:message key="registration.save"/></button>
            </div>
        </form>
    </div>
</body>
</html>
