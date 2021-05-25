<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 11.05.2021
  Time: 20:38
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
<body>
<jsp:include page="parts/header.jsp"/>
<div class="container">
    <h3 class="text-center fw-normal m-4"><fmt:message key="create_record.choose_service"> </fmt:message></h3>
    <form method="post" action="${pageContext.request.contextPath}/controller">
        <div class="border border-light p-3 mb-4">
            <c:forEach items="${serviceMasters}" var="entry">
                <div class="form-check m-2">
                    <div class="row g-0">
                        <div class="col-sm-10 col-md-1">
                            <input class="form-check-input" type="radio" name="chosenProcedure" id="flexRadioDefault2" value="${entry.key}">
                            <label class="form-check-label" for="flexRadioDefault2">
                                    ${entry.key}
                            </label>
                        </div>
                        <div class="col">
                            <select class="form-select" id="${entry.key}" name="${entry.key}" aria-label="Default select example">
                                <c:forEach var="el" items="${entry.value}">
                                    <option>${el.masterName} ${el.masterSurname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="form-group row m-2">
                <label for="datetime" class="col-2 col-form-label"><fmt:message key="create_record.date"/></label>
                <div class="col-10">
                    <input class="form-control" type="date" name="date" value="${currentDate}" id="datetime" min="${currentDate}">
                </div>
            </div>

            <div class="form-group row m-2">
                <label for="timeslot" class="col-2 col-form-label"><fmt:message key="create_record.timeslot"/></label>
                <div class="col-10">
                    <select class="form-select" name="timeslot" id="timeslot">
                        <option>09:00-10:00</option>
                        <option>10:00-11:00</option>
                        <option>11:00-12:00</option>
                        <option>12:00-13:00</option>
                        <option>13:00-14:00</option>
                        <option>14:00-15:00</option>
                        <option>15:00-16:00</option>
                        <option>16:00-17:00</option>
                    </select>
                </div>
            </div>

            <c:if test="${createRecordMessage eq 'taken'}">
                <p class="text-center text-warning">This timeslot no longer available! Please choose another timeslot!</p>
            </c:if>
            <c:if test="${createRecordMessage eq 'successful'}">
                <p class="text-center text-success">Record has created!</p>
            </c:if>
            <c:if test="${createRecordMessage eq 'incorrect'}">
                <p class="text-center text-danger">Incorrect data, please pay attention we don`t work at the weekend!</p>
            </c:if>

            <input type="hidden" name="command" value="createOrder"/>
            <button type="submit" class="btn btn-primary"><fmt:message key="create_record.create"/></button>
        </div>
    </form>
</div>
</body>
</html>
