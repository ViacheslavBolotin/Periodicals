<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
<div class="container">
    <div class="row">
    <div class="d-flex justify-content-end">

        <button type="button" class="btn btn-outline-secondary btn-sm mr-2"
                data-toggle="modal"
                data-target="#topicAddModal">
            <i class="material-icons">create</i>
            <fmt:message
                    key="topic.menu.private_office.modal.title"/>
        </button>
    </div>

    <!-- Modal add topic-->
    <div class="modal fade" id="topicAddModal" tabindex="-1" role="dialog"
         aria-labelledby="topicAddModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="frontController?action=add_topic" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            <fmt:message
                                    key="topic.menu.private_office.modal.title"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="topic_name">
                                    <fmt:message key="topic.menu.private_office.modal.topic_name"/>
                                    </label>
                                <input type="text" maxlength="255" class="form-control" id="topic_name" placeholder=""
                                       name="topic_name"
                                       required>
                                <div class="invalid-feedback">
                                    Topic name is required
                                </div>
                            </div>

                        </div>
                        <hr class="mb-4">
                        <input type="hidden" name="topic_id" value="0">
                        <button class="btn btn-dark btn-lg btn-block" name="btnAddTopic" type="submit">
                            <fmt:message key="topic.menu.private_office.modal.title"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    </div>

    <c:if test="${not empty errorMessage}">
        <h5 style="color:#ff0000"> ${errorMessage}</h5>
    </c:if>
<%-- Теми--%>
<div class="tab-pane fade show active" id="v-pills-users" role="tabpanel"
     aria-labelledby="v-topic-tab">
    <div class="tab-content" id="topicTabContent">
        <%-- Список тем --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="topic-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="topic.th.name"/>
                    </th>
                    <th scope="col"> </th>
                    <th scope="col"> </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="topic" items="${topics}">
                    <tr>
                        <td>${topic.name}</td>
                        <td> <a href="frontController?action=edit_topic&topicId=${topic.id}"> <fmt:message key="topic.th.edit"/> </a> </td>
                        <td> <a href="frontController?action=delete_topic&topicId=${topic.id}" style="color:#ff0000"> <fmt:message key="topic.th.delete"/> </a> </td>
                    </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
