<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_unloged.jsp"></jsp:include>
<div class="container">

<%-- new user --%>
        <div class="tab-pane fade show active" id="new-user" role="tabpanel" aria-labelledby="new-user-tab">
            <form class="mt-2" method="post" action="frontController?action=registration&page=login&main_menu=1">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="firstName">
                            <fmt:message key="profile.modal.label.name"/>
                        </label>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                               minlength="2" maxlength="50" required>
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="lastName">
                            <fmt:message key="profile.modal.label.last_name"/>
                        </label>
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                               minlength="2" maxlength="50" required>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="d-flex">
                            <div class="flex-fill">
                                <label for="email">
                                    <fmt:message key="account.menu.edit.label.email"/>
                                </label>
                                <input type="email" class="form-control" id="email" name="email"
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
                        <input type="text" class="form-control" id="login" name="login"
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
                    <div>
                        <h5 style="color:#ff0000"> <fmt:message key="${errorMessage}"/> </h5>
                    </div>
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
