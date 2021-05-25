<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 13.05.2021
  Time: 13:55
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
    <h3 class="fw-normal text-center m-4"><fmt:message key="master_page.schedule"/></h3>

    <c:forEach var="rec" items="${schedule}">
        <div class="card m-3">
            <div class="card-header">
                <b>${rec.key}</b>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">9:00-10:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('09:00-10:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                            <c:if test="${time.status eq 'confirmed'}">
                                                <form action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="serviceDone">
                                                    <input type="hidden" name="orderDone" value="${time.recordId}">
                                                    <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                                </form>
                                            </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">10:00-11:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('10:00-11:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">11:00-12:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('11:00-12:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">12:00-13:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('12:00-13:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">13:00-14:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('13:00-14:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">14:00-15:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('14:00-15:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">15:00-16:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('15:00-16:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <p class="col mt-3">16:00-17:00</p>
                            <c:forEach var="time" items="${rec.value}">
                                <c:if test="${time.timeslot.equals('16:00-17:00')}">
                                    <div class="col mt-3"><p><fmt:message key="master_page.status"/>: ${time.status}</p></div>
                                    <div class="col mt-3"><fmt:message key="master_page.service"/>: ${time.service}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.client"/>: ${time.clientName} ${time.clientSurname}</div>
                                    <div class="col mt-3"><fmt:message key="master_page.price"/>: ${time.price}</div>
                                    <div class="col mt-3">
                                        <c:if test="${time.status eq 'confirmed'}">
                                            <form action="${pageContext.request.contextPath}/controller">
                                                <input type="hidden" name="command" value="serviceDone">
                                                <input type="hidden" name="orderDone" value="${time.recordId}">
                                                <button type="submit" class="btn btn-primary mb-2"><fmt:message key="master_page.done"/></button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
