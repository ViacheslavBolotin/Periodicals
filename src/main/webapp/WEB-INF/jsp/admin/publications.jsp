<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель администратора" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>
<c:if test="${sessionScope.userRole.getName().equals('admin')}">
    <jsp:include page="/WEB-INF/templates/_menu_admin.jsp"></jsp:include>
</c:if>
<c:if test="${!sessionScope.userRole.getName().equals('admin') && sessionScope.user != null}">
    <jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>
</c:if>
<c:if test="${sessionScope.user == null}">
    <jsp:include page="/WEB-INF/templates/_menu_unloged.jsp"></jsp:include>
</c:if>

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

<div class="col">

<form class="form-inline" method="post" action="frontController?action=publications">
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

    <c:if test="${sessionScope.userRole.getName().equals('admin')}">

    <div class="d-flex justify-content-end">
        <button type="button" class="btn btn-outline-secondary btn-sm mr-2"
                data-toggle="modal"
                data-target="#publicationAddModal">
            <i class="material-icons">create</i>
            <fmt:message key="publication.menu.private_office.modal.title"/>
        </button>
    </div>
    <!-- Modal publication add -->
    <div class="modal fade" id="publicationAddModal" tabindex="-1" role="dialog"
         aria-labelledby="publicationAddModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="frontController?action=add_publication" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            <fmt:message key="publication.menu.private_office.modal.title"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 mb-3">

                            <label for="publication_topic"> <fmt:message key="publication.th.topic"/> </label>

                            <select name="publication_topic" id="publication_topic" class="form-control"
                                width = "150" >
                                <option value=""> <fmt:message key="publication.choose_topic"/> </option>

                                <c:forEach var="topic" items="${topics}">

                                    <option value="${topic.id}">${topic.name}</option>

                                </c:forEach>

                            </select>

                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="publication_title">
                                    <fmt:message key="publication.th.name"/>
                                    </label>
                                <input type="text" class="form-control" id="publication_title"
                                       name="publication_title"
                                       size = "50"
                                       maxlength="255"
                                       placeholder=""
                                       required>
                                <div class="invalid-feedback">
                                    <fmt:message key="publication.placeholder.title"/>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label for="publication_price" class="col-form-label">
                                    <fmt:message key="publication.th.price"/>
                                </label>
                                <input type="number" min="0" max="999999" minlength="1" maxlength="10" step="0.01"
                                       class="form-control"
                                       name="publication_price"
                                       id="publication_price" required>
                            </div>

                        </div>
                        <div class="row">

                            <div class="col-md-3 mb-3">
                                <label for="publication_price" class="col-form-label">
                                    <fmt:message key="publication.th.text"/>
                                </label>
                                <textarea
                                       name="publication_text"
                                       id="publication_text" required
                                       rows="7" cols="60">
                                </textarea>
                            </div>

                        </div>

                        <hr class="mb-4">
                        <input type="hidden" name="publication_id" value="0">
                        <button class="btn btn-dark btn-lg btn-block" name="btnAddCard" type="submit">
                            <fmt:message key="publication.menu.private_office.modal.title"/>
                        </button>

                    </div>

                </form>
            </div>
        </div>
    </div>

 </div>





</div>

</c:if>


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
                    <c:if test="${sessionScope.user != null}">
                        <th scope="col"></th>
                    </c:if>
                    <c:if test="${sessionScope.userRole.getName().equals('admin')}">
                        <th scope="col"></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="publication" items="${publications}">
                    <tr>
                        <td>${publication.title}</td>
                        <td>${publication.topicName}</td>
                        <td>${publication.price}</td>

                        <c:if test="${sessionScope.userRole.getName().equals('admin')}">
                            <td> <a href="frontController?action=edit_publication&publicationId=${publication.id}"> <fmt:message key="publication.th.edit"/> </a> </td>
                            <td> <a href="frontController?action=delete_publication&publicationId=${publication.id}" style="color:#ff0000"> <fmt:message key="publication.th.delete"/> </a> </td>
                        </c:if>

                        <c:if test="${!sessionScope.userRole.getName().equals('admin') && sessionScope.user != null}">
                            <td> <a href="frontController?action=subscribe_publication&publication_id=${publication.id}">
                                <fmt:message key="publication.subscribe"/> </a>
                            </td>
                        </c:if>
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
