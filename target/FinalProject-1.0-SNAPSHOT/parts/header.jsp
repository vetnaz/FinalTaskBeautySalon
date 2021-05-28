<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="appLocalizations.app"/>



<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <div class="container">
        <a class="navbar-brand" href="#"><fmt:message key="header.logo_name"/></a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form>
                <input type="hidden" name="command" value="main">
                <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.main_page"/></button>
            </form>

            <c:if test="${sessionScope.userRole.name().equals('ADMIN')}">
                <form>
                    <input type="hidden" name="command" value="admin">
                    <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.admin_page"/></button>
                </form>
            </c:if>

            <c:if test="${sessionScope.userRole.name().equals('CLIENT')}">
                <form>
                    <input type="hidden" name="command" value="createRecordPage">
                    <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.create_record"/></button>
                </form>
                <form>
                    <input type="hidden" name="command" value="performedServices">
                    <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.performed_services"/></button>
                </form>
                <form>
                    <input type="hidden" name="command" value="confirmedServices">
                    <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.confirmed_records"/></button>
                </form>
            </c:if>

            <c:if test="${sessionScope.userRole.name().equals('MASTER')}">
                <form>
                    <input type="hidden" name="command" value="masterSchedule">
                    <button type="submit" class="btn btn-outline-success ms-2 me-2"><fmt:message key="header.master_page"/></button>
                </form>
            </c:if>
        </div>

        <c:if test="${sessionScope.user!=null}">
            <span class="navbar-text me-2">${sessionScope.user.name} ${sessionScope.user.surname}</span>
            <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="logout"/>
                <button class="btn btn-outline-success"><fmt:message key="header.log_out"/></button>
            </form>
        </c:if>

        <c:if test="${sessionScope.user ==null}">
            <form class="d-flex" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login"/>
                <button class="btn btn-outline-success"><fmt:message key="header.log_in"/></button>
            </form>
        </c:if>
        <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <fmt:message key="header.language"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                    <li>
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="language">
                            <button class="dropdown-item" type="submit" value="en" name="locale"><fmt:message key="header.english"/></button>
                        </form>
                    </li>
                    <li>
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="language">
                            <button class="dropdown-item" type="submit" value="uk" name="locale"><fmt:message key="header.ukrainian"/></button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>