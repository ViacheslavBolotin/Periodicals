<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<c:set var="title" value="Приветственная страница" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_unloged.jsp"></jsp:include>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="container">
<br>
<div class="row">
	<aside class="col-sm-4">
<div class="card">
<article class="card-body">
<a href="frontController?action=new_user" class="float-right btn btn-outline-primary">
    <fmt:message key="mainmenu.button.register"/></a>
<h4 class="card-title mb-4 mt-1"><fmt:message key="mainmenu.button.login"/></h4>
<form action = "/Periodicals/frontController?action=login&main_menu=0" method ="post">
    <div class="form-group">
        <label for="login">
            <fmt:message key="account.menu.edit.label.login"/>
        </label>
        <input type="text" class="form-control" id="user_name" name="user_name"
            placeholder="<fmt:message key="account.menu.edit.placeholder.login"/>"
            minlength="2" maxlength="40" required>
    </div> <!-- form-group// -->

    <div class="form-group">
        <label for="password">
            <fmt:message key="account.menu.edit.label.password"/>
        </label>
        <input type="password" class="form-control" id="password" name="password"
            placeholder="<fmt:message key="account.menu.edit.placeholder.password"/>"
            minlength="1" maxlength="32" required>
    </div> <!-- form-group// -->
    <div class="form-group">
    </div> <!-- form-group// -->

    <c:if test="${not empty errorMessage}">
                    <h5 style="color:#ff0000"> <fmt:message key="${errorMessage}"/> </h5>
    </c:if>

    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">
         <fmt:message key="mainmenu.button.login"/></button>
         </button>
    </div> <!-- form-group// -->
</form>
</article>
</div> <!-- card.// -->

	</aside> <!-- col.// -->
	<aside class="col-sm-4">
	<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>