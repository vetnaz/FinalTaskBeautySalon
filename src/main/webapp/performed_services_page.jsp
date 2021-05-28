<%--
  Created by IntelliJ IDEA.
  User: tommy
  Date: 24.05.2021
  Time: 14:44
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

  <div class="d-flex justify-content-center m-4">
    <H3 class="fw-normal"><fmt:message key="performed_services.performed_services"/></H3>
  </div>

  <c:if test="${pageContext.request.getParameter(\"commentMessage\")eq 'succeed'}">
    <div class="d-flex justify-content-center m-4">
      <H6 class="fw-normal"><fmt:message key="performed_services.thanks"/></H6>
    </div>
  </c:if>

  <c:if test="${not empty performedRecords}">
    <table class="table">
      <thead>
      <tr>
        <th scope="col"><fmt:message key="performed_services.service"/></th>
        <th scope="col"><fmt:message key="performed_services.master"/></th>
        <th scope="col"><fmt:message key="performed_services.date"/></th>
        <th scope="col"><fmt:message key="performed_services.price"/></th>
        <th scope="col"><fmt:message key="performed_services.your_comment"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="record" items="${performedRecords}">
        <tr>
          <td>${record.service}</td>
          <td>${record.masterName} ${record.masterSurname}</td>
          <td>${record.date}</td>
          <td>${record.price}</td>
          <td>
            <c:if test="${empty record.comment}">
              <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" value="${record.recordId}" name="recordId">
                <input type="hidden" value="commentPage" name="command">
                <button class="btn btn-primary" type="submit"><fmt:message key="performed_services.leave_comment"/></button>
              </form>
            </c:if>
            <c:if test="${not empty record.comment}">
              ${record.comment}
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <c:if test="${empty performedRecords}">
    <div class="d-flex justify-content-center m-4">
      <H4 class="fw-normal"><fmt:message key="performed_services.no_services"/></H4>
    </div>
  </c:if>
</div>
</body>