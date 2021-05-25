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
    <title><fmt:message key="header.logo_name"/></title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
</head>
<bodyn class="fw-normal">

<jsp:include page="parts/header.jsp"/>

<div class="border border-light p-3 mb-4">

    <div class="d-flex align-items-center justify-content-center" style="height: 350px">
            <form style="width: 400px;" method="post" class="needs-validation" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login">
                <h3 class="fw-norma m-5" style="text-align: center"><fmt:message key="login.please_log_in"/></h3>
                <div class="m-2">
                    <input type="text" class="form-control m-2" name="login" pattern="^[0-9a-zA-Z-+_]{3,25}$" placeholder="<fmt:message key="login.login"/>" title="You can use latin symbols, figures,and -+_ .Length must be from 3 to 25 symbols." required>
                </div>
                <div class="m-2">
                    <input type="password" class="form-control m-2" pattern="^[a-z0-9_-]{6,18}$" name="password" placeholder="<fmt:message key="login.password"/>" title="You can use lowercase latin symbols, figures,and -_ .Length must be from 6 to 18 symbols." required>
                </div>
                <c:if test="${valid_message eq 'no_such_user'}">
                    <p class="text-center text-danger">No such user!</p>
                </c:if>
                <c:if test="${valid_message eq 'wrong_password'}">
                    <p class="text-center text-danger">Wrong password!</p>
                </c:if>
                <div class="text-center m-3">
                    <button type="submit" class="btn btn-primary m-3"><fmt:message key="login.sign_in"/></button>
                    <a class="btn btn-primary m-3" href="${pageContext.request.contextPath}/controller?command=registration">Registration</a>
                    <p class="mt-2 mb-3 text-muted">© 2021</p>
                </div>
            </form>
    </div>

</div>
</bodyn>
</html>