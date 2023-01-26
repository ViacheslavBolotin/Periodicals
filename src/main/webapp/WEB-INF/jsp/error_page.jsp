<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="error page" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<div class="page-container-responsive">
    <div class="row space-top-8 space-8">
        <div class="col-md-5 col-middle">
            <h1 class="text-jumbo text-ginormous hide-sm"> <fmt:message key="error.page.title"/> </h1>
            <h2> <fmt:message key="error.page.text"/> </h2>
            <h5 style="color:#ff0000"> <fmt:message key="${errorMessage}"/> </h5>
            <h6> <fmt:message key="error.page.return"/>
                 <a href="/Periodicals/"> <fmt:message key="error.page.main_page"/> </a>
            </h6>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>