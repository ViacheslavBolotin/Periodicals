<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<%@ taglib prefix = "ex" uri = "/WEB-INF/customTag"%>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>

<div class="container">

<div class="row">

    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="payment.sort"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="<c:out value="${'frontController?action=publications' += '&'+= 'sort=' += 'ws'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.ws"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=publications' += '&'+= 'sort=' += 'nms'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.nms"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=publications' += '&'+= 'sort=' += 'nmf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.nmf"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=publications' += '&'+= 'sort=' += 'bs'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="publication.sort.prices"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=publications' += '&'+= 'sort=' += 'bf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="publication.sort.pricef"/> </a>
        </div>
    </div>

</div>

    <c:if test="${not empty errorMessage}">
        <h5 style="color:#ff0000"> ${errorMessage}</h5>
    </c:if>

<%-- Reports--%>

<div class="tab-pane fade show active" id="v-pills-users" role="tabpanel"
     aria-labelledby="v-pills-users-tab">
    <div class="tab-content" id="usersTabContent">
        <%-- Список публікацій --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="internet-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="publication.th.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="publication.th.topic"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="publication.th.price"/>
                    </th>
                    <c:if test="${sessionScope.user != null}">
                        <th scope="col"></th>
                    </c:if>
                    <c:if test="${sessionScope.userRole.getName().equals('admin')}">
                        <th scope="col"></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="topic" items="${topics}">
                        <tr>
                            <ex:OutPutRow message = "${topic}" />
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="<c:out value="${'frontController?action=publications'
                    += '&' += 'page=' += ps.currentPage - 1
                    += '&'+= 'sort=' += ps.sortType
                    += '&'+= 'topic_filter=' += ps.topicFilter
                    += '&'+= 'title_filter=' += ps.titleFilter
                    }"/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
                <li class="page-item"><a class="page-link" > ${ps.currentPage}</a></li>
                <li class="page-item"><a class="page-link" ><fmt:message key="pagination.name"/> ${ps.numberOfPages}</a></li>
            <li class="page-item">
                <a class="page-link" href="<c:out value="${'frontController?action=publications'
                    += '&'+= 'page=' += ps.currentPage + 1
                    += '&'+= 'sort=' += ps.sortType
                    += '&'+= 'topic_filter=' += ps.topicFilter
                    += '&'+= 'title_filter=' += ps.titleFilter
                    }"/>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

</div>

<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
