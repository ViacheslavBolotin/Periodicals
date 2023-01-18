<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Приветственная страница" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_unloged.jsp"></jsp:include>

<div class="container">

    <img src="Periodicals_welcome.png" width="1150" height="670">

</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>

</body>
</html>
