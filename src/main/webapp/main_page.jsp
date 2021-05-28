<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 10.05.2021
  Time: 17:11
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
    <title><fmt:message key="header.logo_name"/></title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
</head>
<body class="fw-normal">

<jsp:include page="parts/header.jsp"/>

<div class="container">
    <c:if test="${access_message eq 'is_not_allowed'}">
        <div class="d-flex justify-content-center m-4">
            <H5 class="text-danger fw-normal">You do not have permission to access the requested resource!</H5>
        </div>
    </c:if>

    <div class="d-flex justify-content-center m-4">
        <H3 class="fw-normal"><fmt:message key="main.catalog"/></H3>
    </div>

    <form method="post">
        <div class="row">
            <div class="col">
                <h5 class="fw-normal m-2"><fmt:message key="main.sort_by"/></h5>
                <input type="hidden" name="command" value="main">
                <div class="form-check m-2">
                    <input class="form-check-input" type="radio" name="filter" id="flexRadioDefault1" value="name" ${name}>
                    <label class="form-check-label" for="flexRadioDefault1">
                        <fmt:message key="main.by_master_name"/>
                    </label>
                </div>

                <div class="form-check m-2">
                    <input class="form-check-input" type="radio" name="filter" id="flexRadioDefault2" value="rating" ${rating}>
                    <label class="form-check-label" for="flexRadioDefault2">
                        <fmt:message key="main.by_master_rating"/>
                    </label>
                </div>
            </div>

            <div class="col">
                <h5 class="fw-normal m-2"><fmt:message key="main.filter"/></h5>

                <p>
                    <button class="btn btn-primary m-2" type="button" data-bs-toggle="collapse" data-bs-target="#nameFilterCollapse" aria-expanded="false" aria-controls="collapseExample">
                        <fmt:message key="main.masters"/>
                    </button>
                </p>
                <div class="collapse m-2" id="nameFilterCollapse">
                    <div class="card card-body">
                        <c:forEach var="el" items="${masters}">
                            <div class="form-check">
                                <input class="form-check-input" name="masters" type="checkbox" value="${el.masterName} ${el.masterSurname}" id="flexCheckDefault1">
                                <label class="form-check-label" for="flexCheckDefault1">
                                        ${el.masterName} ${el.masterSurname}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <p>
                    <button class="btn btn-primary m-2" type="button" data-bs-toggle="collapse" data-bs-target="#serviceFilterCollapse" aria-expanded="false" aria-controls="collapseExample">
                        <fmt:message key="main.services"/>
                    </button>
                </p>
                <div class="collapse m-2" id="serviceFilterCollapse">
                    <div class="card card-body">
                        <c:forEach var="el" items="${servicesName}">
                            <div class="form-check">
                                <input class="form-check-input" name="services" type="checkbox" value="${el}" id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                        ${el}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

        <button class="m-2 btn btn-primary" type="submit" value="Submit"><fmt:message key="main.show"/></button>
    </form>



    <table class="table table-striped m-2">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="main.service"/></th>
            <th scope="col"><fmt:message key="main.description"/></th>
            <th scope="col"><fmt:message key="main.price"/></th>
            <th scope="col"><fmt:message key="main.master_name"/></th>
            <th scope="col"><fmt:message key="main.rating"/></th>
        </tr>
        </thead>
        <c:forEach var="val" items="${services}" >
            <tr>
                <td>${val.title}</td>
                <td>${val.description}</td>
                <td>${val.price}</td>
                <td>${val.masterName} ${val.masterSurname}</td>
                <td>${val.rating}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
