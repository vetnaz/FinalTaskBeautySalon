<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 24.05.2021
  Time: 18:08
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
    <H3 class="fw-normal"><fmt:message key="confirmed_records.confirmed_records"/></H3>
  </div>

  <c:if test="${not empty confirmedRecords}">
    <table class="table">
      <thead>
      <tr>
        <th scope="col"><fmt:message key="confirmed_records.service"/></th>
        <th scope="col"><fmt:message key="confirmed_records.master"/></th>
        <th scope="col"><fmt:message key="confirmed_records.date"/></th>
        <th scope="col"><fmt:message key="confirmed_records.price"/></th>
        <th scope="col"><fmt:message key="confirmed_records.status"/></th>
        <th scope="col"><fmt:message key="confirmed_records.timeslot"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="record" items="${confirmedRecords}">
        <tr>
          <td>${record.service}</td>
          <td>${record.masterName} ${record.masterSurname}</td>
          <td>${record.date}</td>
          <td>${record.price}</td>
          <td>${record.status}</td>
          <td>${record.timeslot}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <c:if test="${empty confirmedRecords}">
    <div class="d-flex justify-content-center m-4">
      <H4 class="fw-normal"><fmt:message key="confirmed_records.no_records"/></H4>
    </div>
  </c:if>
</div>
</body>