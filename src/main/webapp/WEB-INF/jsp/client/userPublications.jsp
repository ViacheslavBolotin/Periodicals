<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>

<div class="container">

<div class="row">

    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="payment.sort"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="<c:out value="${'frontController?action=personal_cabinet' += '&'+= 'sort=' += 'ws'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.ws"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=personal_cabinet' += '&'+= 'sort=' += 'nms'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.nms"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=personal_cabinet' += '&'+= 'sort=' += 'nmf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="account.sort.nmf"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=personal_cabinet' += '&'+= 'sort=' += 'bs'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="publication.sort.prices"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=personal_cabinet' += '&'+= 'sort=' += 'bf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter}"/>">
                <fmt:message key="publication.sort.pricef"/> </a>
        </div>
    </div>

<div class="col">

<form class="form-inline" method="post" action="frontController?action=personal_cabinet">
    <div class="form-group">

        <label for="publication_topic"> <fmt:message key="publication.th.topic"/> </label>

            <select name="topic_filter" id="topic_filter" class="form-control" width = "150">
                <option value=""> <fmt:message key="publication.all_topic"/> </option>
                <c:forEach var="topic" items="${topics}">

                    <option
                        <c:choose>
                            <c:when test="${ps.topicFilter==topic.id}">
                                selected="selected"
                            </c:when>
                        </c:choose>
                        value="${topic.id}">${topic.name}</option>

                </c:forEach>
            </select>

        <label for="publication_title"> <fmt:message key="publication.th.name"/> </label>
        <input type="text" class="form-control" id="title_filter" name="title_filter" value="${ps.titleFilter}"
                minlength="1" maxlength="255">

        <button type="submit" class="btn btn-dark"> <fmt:message key="publication.filter"/> </button>

    </div>
</form>

<div class="col"></div>

    <c:if test="${not empty errorMessage}">
        <h5 style="color:#ff0000"> ${errorMessage}</h5>
    </c:if>

<%-- Publication--%>

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
                        <fmt:message key="publication.th.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="publication.th.topic"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="publication.th.price"/>
                    </th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="publication" items="${publications}">
                    <tr>
                        <td>${publication.title}</td>
                        <td>${publication.topicName}</td>
                        <td>${publication.price}</td>
                        <td> <a href="frontController?action=view_publication&publication_id=${publication.id}">
                                <fmt:message key="publication.view"/> </a>
                        </td>
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
                <a class="page-link" href="<c:out value="${'frontController?action=personal_cabinet'
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
                <a class="page-link" href="<c:out value="${'frontController?action=personal_cabinet'
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
