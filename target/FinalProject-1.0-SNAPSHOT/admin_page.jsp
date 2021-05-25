<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 13.05.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <div class="d-flex justify-content-center m-4">
        <H3 class="fw-normal">Admin panel</H3>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="admin.service"/></th>
            <th scope="col"><fmt:message key="admin.client_name"/></th>
            <th scope="col"><fmt:message key="admin.master_name"/></th>
            <th scope="col"><fmt:message key="admin.date"/></th>
            <th scope="col"><fmt:message key="admin.timeslot"/></th>
            <th scope="col"><fmt:message key="admin.price"/></th>
            <th scope="col"><fmt:message key="admin.status"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${allRecords}">
            <tr>
                <td>${record.service}</td>
                <td>${record.clientName} ${record.clientSurname}</td>
                <td>${record.masterName} ${record.masterSurname}</td>
                <td>${record.date}</td>
                <td>${record.timeslot}</td>
                <td>${record.price}</td>
                <td>${record.status}</td>

                <c:if test="${record.status eq 'done'}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="updateRecords">
                            <input type="hidden" name="orderPaid" value="${record.recordId}">
                            <button class="btn btn-primary btn-sm" type="submit"><fmt:message key="admin.paid"/></button>
                        </form>
                    </td>
                </c:if>

                <c:if test="${record.status eq 'opened'}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="cancelRecord">
                            <input type="hidden" name="cancelId" value="${record.recordId}">
                            <button class="btn btn-danger btn-sm" type="submit"><fmt:message key="admin.cancel"/></button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${record.status eq 'opened'}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="updateRecords">
                            <input type="hidden" name="orderConfirmed" value="${record.recordId}">
                            <button class="btn btn-success btn-sm" type="submit">Confirm</button>
                        </form>
                    </td>
                </c:if>

                <c:if test="${record.status eq 'opened' || record.status eq 'confirmed'}">
                    <td>
                        <p>
                            <button id="${record.recordId}" onclick="onclick()" class="btn btn-info btn-sm" type="button" data-bs-toggle="collapse" data-bs-target="#name" aria-expanded="false" aria-controls="collapseExample">
                                <fmt:message key="admin.change_timeslot"/>
                            </button>
                        </p>


                        <div class="collapse" id="name">
                            <div class="card card-body">
                                <form method="post" action="${pageContext.request.contextPath}/controller">
                                    <select class="form-select" name="timeslot" id="timeslot">
                                        <option>9:00-10:00</option>
                                        <option>10:00-11:00</option>
                                        <option>11:00-12:00</option>
                                        <option>12:00-13:00</option>
                                        <option>13:00-14:00</option>
                                        <option>14:00-15:00</option>
                                        <option>15:00-16:00</option>
                                        <option>16:00-17:00</option>
                                    </select>
                                    <div class="text-center">
                                        <input type="hidden" name="command" value="changeTimeslot">
                                        <input type="hidden" name="recordId" value="${record.recordId}">
                                    </div>
                                    <button class="btn btn-primary btn-sm text-center mt-2" type="submit"><fmt:message key="admin.save"/></button>
                                </form>
                            </div>
                        </div>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav>
        <ul class="pagination">

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only"></span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=admin&currentPage=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </ul>
    </nav>
</div>
</body>
</html>
