<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
<div class="container">

<div>
<c:if test="${not empty errorMessage}">
                    <h5 style="color:#ff0000">
                    <fmt:message key="${errorMessage}"/></h5>
</c:if>
</div>

<%-- Список користувачів--%>
<div class="tab-pane fade show active" id="v-pills-users" role="tabpanel"
     aria-labelledby="v-pills-users-tab">
    <ul class="nav nav-tabs" id="usersTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="users-tab" data-toggle="tab" href="#users"
               role="tab"
               aria-controls="users" aria-selected="true">
                <fmt:message key="main.tab.users"/>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="new-user-tab" data-toggle="tab" href="#new-user" role="tab"
               aria-controls="new-user"
               aria-selected="false">
                <fmt:message key="main.tab.new_user"/>
            </a>
        </li>
    </ul>
    <div class="tab-content" id="usersTabContent">
        <%-- Список абонентов --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="internet-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="table.th.login"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.last_name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.email"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.create_time"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.status"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="table.th.management"/>
                    </th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lUser" items="${fullUser}">
                    <tr>
                        <td>${lUser.userName}</td>
                        <td>${lUser.firstName}</td>
                        <td>${lUser.lastName}</td>
                        <td>${lUser.email}</td>
                        <td>${lUser.getCreateTime()}</td>
                        <td><tags:isActive parameter="${lUser.blocked}"/></td>
                        <td>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <form action="frontController?action=block_unblock_user"
                                          method="post">
                                        <input type="hidden" name="user_id"
                                               value="${lUser.id}">
                                        <button type="submit"
                                                class="btn btn-outline-secondary btn-sm"
                                                name="btnLock">
                                                ${lUser.blocked ? '<i class="material-icons">lock_open</i>' : '<i class="material-icons">lock</i>'}
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<%-- Новый абонент --%>
    <div class="tab-pane fade" id="new-user" role="tabpanel" aria-labelledby="new-user-tab">
        <form class="mt-2" method="post" action="frontController?action=registration&page=users">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="firstName">
                        <fmt:message key="profile.modal.label.name"/>
                    </label>
                    <input type="text" class="form-control" id="firstName" name="firstName"
                           placeholder="<fmt:message key="profile.modal.placeholder.name"/>"
                           minlength="2" maxlength="40" required>
                </div>

            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="lastName">
                        <fmt:message key="profile.modal.label.last_name"/>
                    </label>
                    <input type="text" class="form-control" id="lastName" name="lastName"
                           placeholder="<fmt:message key="profile.modal.placeholder.last_name"/>"
                           minlength="2" maxlength="40" required>
                </div>
                <div class="form-group col-md-6">
                    <div class="d-flex">
                        <div class="flex-fill">
                            <label for="email">
                                <fmt:message key="account.menu.edit.label.email"/>
                            </label>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="my_email@gmail.com" minlength="2"
                                   maxlength="40" required>
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
                           minlength="6" maxlength="40" required>
                </div>
            </div>

            <button type="submit" class="btn btn-dark">
                <fmt:message key="account.menu.edit.button.save"/>
            </button>
        </form>
    </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
