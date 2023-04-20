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
            <a class="dropdown-item" href="<c:out value="${'frontController?action=reports' += '&'+= 'sort=' += 'ws'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter
                += '&'+= 'date_begin=' += ps.dateBegin += '&'+= 'date_end=' += ps.dateEnd}"/>">
                <fmt:message key="account.sort.ws"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=reports' += '&'+= 'sort=' += 'nms'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter
                += '&'+= 'date_begin=' += ps.dateBegin += '&'+= 'date_end=' += ps.dateEnd}"/>">
                <fmt:message key="account.sort.nms"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=reports' += '&'+= 'sort=' += 'nmf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter
                += '&'+= 'date_begin=' += ps.dateBegin += '&'+= 'date_end=' += ps.dateEnd}"/>">
                <fmt:message key="account.sort.nmf"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=reports' += '&'+= 'sort=' += 'bs'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter
                += '&'+= 'date_begin=' += ps.dateBegin += '&'+= 'date_end=' += ps.dateEnd}"/>">
                <fmt:message key="publication.sort.prices"/> </a>
            <a class="dropdown-item" href="<c:out value="${'frontController?action=reports' += '&'+= 'sort=' += 'bf'
                += '&'+= 'topic_filter=' += ps.topicFilter += '&'+= 'title_filter=' += ps.titleFilter
                += '&'+= 'date_begin=' += ps.dateBegin += '&'+= 'date_end=' += ps.dateEnd}"/>">
                <fmt:message key="publication.sort.pricef"/> </a>
        </div>
    </div>

    <p style="margin-left: 40px"> </p>

    <form class="form-inline" method="post" action="frontController?action=export_to_pdf">
        <button type="submit" margin-left: 40px> <fmt:message key="report.export_to_pdf"/> </button>
    </form>


</div>
<div class="row">

<form class="form-inline" method="post" action="frontController?action=reports">
    <div class="form-group">


        <label for="date_begin"> <fmt:message key="report.date_begin"/> </label>
        <p style="margin-left: 5px"> </p>
        <input type="date" id="date_begin" name="date_begin"
           min="2020-01-01T00:00" value = ${ps.dateBegin} required />

        <p style="margin-left: 5px"> </p>
        <label for="date_end"> <fmt:message key="report.date_end"/> </label>
        <p style="margin-left: 5px"> </p>
        <input type="date" id="date_end" name="date_end"
            min="2020-01-01T00:00" value = "" required />

        <script type="text/javascript">
            const dateControlEnd = document.querySelector('input[id="date_end"]');
            dateControlEnd.value = new Date().toJSON().slice(0,10);
        </script>

        <p style="margin-left: 5px"> </p>
        <label for="publication_topic"> <fmt:message key="publication.th.topic"/> </label>
        <p style="margin-left: 5px"> </p>

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

        <p style="margin-left: 5px"> </p>
        <label for="publication_title"> <fmt:message key="publication.th.name"/> </label>
        <p style="margin-left: 5px"> </p>
        <input type="text" class="form-control" id="title_filter" name="title_filter" value="${ps.titleFilter}"
                minlength="1" maxlength="255">

        <p style="margin-left: 5px"> </p>
        <button type="submit" class="btn btn-dark"> <fmt:message key="publication.filter"/> </button>

    </div>
</form>


</div>

    <c:if test="${not empty errorMessage}">
        <h5 style="color:#ff0000"> ${errorMessage}</h5>
    </c:if>

<%-- Reports--%>

<div class="row"> <p style="margin-left: 5px"> </p> </div>

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
                        <fmt:message key="publication.th.topic"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="publication.th.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="report.publication.th.quantity"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="report.publication.th.amount"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="recordReportPublications" items="${reportPublications}">
                        <tr>
                            <ex:OutPutRow recordReport = "${recordReportPublications}" />
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
                <a class="page-link" href="<c:out value="${'frontController?action=reports'
                    += '&' += 'page=' += ps.currentPage - 1
                    += '&'+= 'sort=' += ps.sortType
                    += '&'+= 'topic_filter=' += ps.topicFilter
                    += '&'+= 'title_filter=' += ps.titleFilter
                    += '&'+= 'date_begin=' += ps.dateBegin
                    += '&'+= 'date_end=' += ps.dateEnd
                    }"/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
                <li class="page-item"><a class="page-link" > ${ps.currentPage}</a></li>
                <li class="page-item"><a class="page-link" ><fmt:message key="pagination.name"/> ${ps.numberOfPages}</a></li>
            <li class="page-item">
                <a class="page-link" href="<c:out value="${'frontController?action=reports'
                    += '&'+= 'page=' += ps.currentPage + 1
                    += '&'+= 'sort=' += ps.sortType
                    += '&'+= 'topic_filter=' += ps.topicFilter
                    += '&'+= 'title_filter=' += ps.titleFilter
                    += '&'+= 'date_begin=' += ps.dateBegin
                    += '&'+= 'date_end=' += ps.dateEnd
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
