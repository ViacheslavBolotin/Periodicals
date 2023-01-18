<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
<div class="container">

${session.setAttribute("current_action", "new_user")}

<%-- edit topic --%>
        <div class="tab-pane fade show active" id="edit-topic" role="tabpanel" aria-labelledby="edit-topic-tab">
            <form class="mt-2" method="post" action="frontController?action=update_topic">

                <input type="hidden" name="topic_id" value=${topic.id}>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="topic_name">
                            <fmt:message key="topic.menu.private_office.modal.topic_name"/>
                        </label>
                        <input type="text" class="form-control" id="topic_name" name="topic_name" value="${topic.name}"
                               placeholder="<fmt:message key="topic.placeholder.name"/>"
                               minlength="1" maxlength="255" required>
                    </div>

                </div>

                <c:if test="${not empty errorMessage}">
                    <h5 style="color:#ff0000"> ${errorMessage}</h5>
                </c:if>
                <button type="submit" class="btn btn-dark">
                    <fmt:message key="account.menu.edit.button.save"/>
                </button>

                <a href="frontController?action=topics"> <fmt:message key="account.menu.edit.button.cancel"/>

            </form>
        </div>
    </div>
<%--</div>--%>
<%--</div>--%>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
