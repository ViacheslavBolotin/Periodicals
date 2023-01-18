<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
<div class="container">

<div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="payment.sort"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'sort=' += 'ws'}"/>">
                <fmt:message key="account.sort.ws"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'sort=' += 'nms'}"/>">
                <fmt:message key="account.sort.nms"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'sort=' += 'nmf'}"/>">
                <fmt:message key="account.sort.nmf"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'sort=' += 'bs'}"/>">
                <fmt:message key="account.sort.bs"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'sort=' += 'bf'}"/>">
                <fmt:message key="account.sort.bf"/> </a>
        </div>
</div>

<%-- Accounts--%>
<div class="tab-pane fade show active" id="v-pills-users" role="tabpanel"
     aria-labelledby="v-pills-users-tab">
    <div class="tab-content" id="usersTabContent">
        <%-- Список платежів --%>
        <div class="tab-pane fade show active" id="users" role="tabpanel"
             aria-labelledby="internet-tab">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <fmt:message key="account.th.owner"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="login.label.login"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="account.th.sum"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fullAccount" items="${fullAccount}">
                    <tr>
                        <td>${fullAccount.fullName}</td>
                        <td>${fullAccount.userName}</td>
                        <td>${fullAccount.amount}</td>
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
                <a class="page-link" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'page=' += ps.currentPage - 1  += '&'+= 'sort=' += ps.sortType}"/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
                <li class="page-item"><a class="page-link" > ${ps.currentPage}</a></li>
                <li class="page-item"><a class="page-link" ><fmt:message key="pagination.name"/> ${ps.numberOfPages}</a></li>
            <li class="page-item">
                <a class="page-link" href="<c:out value="${'frontController?action=accounts'  += '&'+= 'page=' += ps.currentPage + 1  += '&'+= 'sort=' += ps.sortType}"/>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
