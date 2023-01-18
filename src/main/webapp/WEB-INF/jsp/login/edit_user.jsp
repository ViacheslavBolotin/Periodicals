<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>

<c:choose>
    <c:when test="${sessionScope.user.userRoleId==1}">
        <jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
    </c:when>
    <c:when test="${sessionScope.user.userRoleId==2}">
            <jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
        </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/templates/_menu_unloged.jsp"></jsp:include>
    </c:otherwise>
</c:choose>

<div class="container">

<%-- new user --%>
        <div class="tab-pane fade show active" id="new-user" role="tabpanel" aria-labelledby="new-user-tab">
            <form class="mt-2" method="post" action="frontController?action=update_user">

                <input type="hidden" name="user_id" value=${user.id}>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="firstName">
                            <fmt:message key="profile.modal.label.name"/>
                        </label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value=${user.firstName}
                               placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                               minlength="2" maxlength="50" required>
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="lastName">
                            <fmt:message key="profile.modal.label.last_name"/>
                        </label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value=${user.lastName}
                               placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                               minlength="2" maxlength="50" required>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="d-flex">
                            <div class="flex-fill">
                                <label for="email">
                                    <fmt:message key="account.menu.edit.label.email"/>
                                </label>
                                <input type="email" class="form-control" id="email" name="email" value="${user.email}"
                                       placeholder="my_email@gmail.com" minlength="2"
                                       maxlength="100" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="login">
                            <fmt:message key="account.menu.edit.label.login"/>
                        </label>
                        <input type="text" class="form-control" id="login" name="login" value="${user.userName}"
                               placeholder="<fmt:message key="account.menu.edit.placeholder.login"/>"
                               minlength="2" maxlength="40" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="password">
                            <fmt:message key="account.menu.edit.label.password"/>
                        </label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<fmt:message key="account.menu.edit.placeholder.password"/>"
                               minlength="6" maxlength="32" required>
                    </div>
                </div>
                <c:if test="${not empty errorMessage}">
                    <h5 style="color:#ff0000"> ${errorMessage}</h5>
                </c:if>
                <button type="submit" class="btn btn-dark">
                    <fmt:message key="account.menu.edit.button.save"/>
                </button>
            </form>
        </div>
    </div>
<%--</div>--%>
<%--</div>--%>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>

</body>
</html>
