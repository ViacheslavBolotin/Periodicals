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



<form class="form-inline" method="post" action="frontController?action=reports">
    <div class="form-group">


        <label for="date_begin">Date begin:</label>
        <input type="date" id="date_begin" name="date_begin"
           min="2020-01-01T00:00" value = ${ps.dateBegin} required />

        <label for="date_end">Date end:</label>
        <input type="date" id="date_end" name="date_end"
            min="2020-01-01T00:00" value = "" required />

        <script type="text/javascript">

            const dateControlEnd = document.querySelector('input[id="date_end"]');
            dateControlEnd.value = new Date().toJSON().slice(0,10);
        </script>


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

<%--
<table border="1" rules="groups" width="500" cellpadding="3" cellspacing="0">
<caption><b>Энергетическая ценность продуктов питания:</b></caption>
<thead bgcolor ="#deb887" title="Шапка">
<tr>
<th>Продукт</th><th>Белки</th><th>Жиры</th><th>Углеводы</th><th>ккал</th>
</tr>
</thead>
<tbody align="left" bgcolor ="#faebd7" title="Энергетическая ценность продуктов питания">
<tr>
<th>Хлеб ржаной</th><td>4,7</td><td>0,7</td><td>49,8</td><td>214</td>
</tr>
<tr>
<th>Молоко</th><td>2,8</td><td>3,2</td><td>4,7</td><td>58</td>
</tr>
<tr>
<td class="table-primary"> Картофель</td><td class="table-primary">2</td><td>0,1</td><td>19,7</td><td>83</td>
</tr>
<tr>
<th>Свинина</th><td>11,4</td><td>49,3</td><td>9</td><td>489</td>
</tr>
</tbody>
<tfoot align="left" bgcolor ="#f5f5dc" title="Итого:">
<tr>
<th>Итого:</th><td>20,9</td><td>53,3</td><td>83,2</td><td>844</td>
</tr>
</tfoot>
</table>

--%>

<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>
</body>
</html>
