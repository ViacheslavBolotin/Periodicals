<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
<div class="container">

                <div class="form-row">

                    <div class="form-group col-md-6">

                        <label for="publication_topic"> <fmt:message key="publication.th.topic"/> </label>

                       <input type="text" class="form-control" id="publication_topic" name="publication_topic" value="${publication.topicName}"
                                                      minlength="1" maxlength="255" readonly>

                        <label for="publication_title"> <fmt:message key="publication.th.name"/> </label>
                        <input type="text" class="form-control" id="publication_title" name="publication_title" value="${publication.title}"
                               minlength="1" maxlength="255" readonly>

                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label for="publication_price" class="col-form-label">
                                    <fmt:message key="publication.th.text"/>
                                </label>
                                <textarea
                                    name="publication_text"
                                    id="publication_text"
                                    rows="7" cols="60" readonly>
                                    ${publication.text}
                                </textarea>
                            </div>
                        </div>

                    </div>

                </div>




<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
