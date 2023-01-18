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
            <h1 class="text-jumbo text-ginormous hide-sm">Ups!</h1>
            <h2>It seams we have a problem.</h2>
            <h2>${errorMessage}</td></h2>
            <h6>Return to <a href="/Periodicals/">Main</a></h6>
        </div>
        <div class="col-md-5 col-middle text-center">
<!--    <img src="https://c.tenor.com/OyUVgQi-l-QAAAAC/404.gif" width="313" height="428" class="hide-sm" alt="404"> -->

        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>