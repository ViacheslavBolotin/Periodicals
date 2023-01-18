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

<%-- edit publication --%>
        <div class="tab-pane fade show active" id="edit-publication" role="tabpanel" aria-labelledby="edit-publication-tab">
            <form class="mt-2" method="post" action="frontController?action=update_publication">

                <input type="hidden" name="publication_id" value=${publication.id}>
                <div class="form-row">

                    <div class="form-group col-md-6">

                        <label for="publication_topic"> <fmt:message key="publication.th.topic"/> </label>

                        <select name="publication_topic" id="publication_topic" class="form-control"
                                width = "150" >
                            <option value=""> <fmt:message key="publication.choose_topic"/> </option>

                            <c:forEach var="topic" items="${topics}">

                                <option
                                    <c:choose>
                                        <c:when test="${publication.topicId==topic.id}">
                                            selected="selected"
                                        </c:when>
                                    </c:choose>
                                    value="${topic.id}">${topic.name}</option>

                            </c:forEach>

                        </select>

                        <label for="publication_title"> <fmt:message key="publication.th.name"/> </label>
                        <input type="text" class="form-control" id="publication_title" name="publication_title" value="${publication.title}"
                               placeholder="<fmt:message key="publication.placeholder.title"/>"
                               minlength="1" maxlength="255" required>

                        <label for="publication_price" class="col-form-label">
                            <fmt:message key="publication.th.price"/> </label>
                            <input type="number" min="0" max="999999" minlength="1" maxlength="10" step="0.01"
                                class="form-control"
                                name="publication_price"
                                id="publication_price"
                                value=${publication.price} required>

                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label for="publication_price" class="col-form-label">
                                    <fmt:message key="publication.th.text"/>
                                </label>
                                <textarea
                                    name="publication_text"
                                    id="publication_text" required
                                    rows="7" cols="60">
                                    ${publication.text}
                                </textarea>
                            </div>
                        </div>

                    </div>

                </div>

                <c:if test="${not empty errorMessage}">
                    <h5 style="color:#ff0000"> ${errorMessage}</h5>
                </c:if>
                <button type="submit" class="btn btn-dark">
                    <fmt:message key="account.menu.edit.button.save"/>
                </button>

                <a href="frontController?action=publications"> <fmt:message key="account.menu.edit.button.cancel"/>
                </a>
            </form>
        </div>
    </div>
<%--</div>--%>
<%--</div>--%>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
