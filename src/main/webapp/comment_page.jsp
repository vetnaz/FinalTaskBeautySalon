<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 24.05.2021
  Time: 15:21
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
    <H3 class="fw-normal">Leave your comment, please!</H3>
  </div>

  <table class="table m-2">
    <thead>
    <tr>
      <th scope="col">Service</th>
      <th scope="col">Master</th>
      <th scope="col">Date</th>
      <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <tr class="table-success">
      <td class="table-success">${record.service}</td>
      <td class="table-success">${record.masterName} ${record.masterSurname}</td>
      <td class="table-success">${record.date}</td>
      <td class="table-success">${record.price}</td>
    </tr>
    </tbody>
  </table>

<form method="post" action="${pageContext.request.contextPath}/controller">
  <div class="input-group m-2">
    <span class="input-group-text">Feedback</span>
    <textarea name="comment" class="form-control" aria-label="With textarea"></textarea>
  </div>

  <input type="hidden" name="command" value="saveFeedback">
  <input type="hidden" name="recordId" value="${record.recordId}">
  <button type="submit" class="btn btn-primary m-4">Send feedback</button>
</form>
</div>

</body>

