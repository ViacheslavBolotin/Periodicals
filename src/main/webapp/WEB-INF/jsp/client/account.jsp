<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Панель користувача" scope="page"/>
<jsp:include page="/WEB-INF/templates/_head.jsp"></jsp:include>
<body>

<jsp:include page="/WEB-INF/templates/_menu_customer.jsp"></jsp:include>

<div class="container">

        <div class="tab-pane fade show active" id="new-user" role="tabpanel" aria-labelledby="new-user-tab">
            <form class="mt-2" method="post" action="frontController?action=update_account">

                <input type="hidden" name="account_id" value=${account.id}>

                <div class="form-group">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="lastName"> <fmt:message key="account.th.sum"/> </label>
                            <input type="number" class="form-control" id="amount" name="amount" value=${account.amount}
                                   minlength="2" maxlength="50" readonly>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="replenish_sum" class="col-form-label"> <fmt:message key="account.replenish"/> </label>
                            <input type="number" name="replenish_sum" id="replenish_sum"
                                min="0" max="999999" minlength="1" maxlength="10" step="0.01"
                                class="form-control" value="0" required>
                        </div>
                    </div>

                    <c:if test="${not empty errorMessage}">
                        <h5 style="color:#ff0000"> ${errorMessage}</h5>
                    </c:if>
                    <button type="submit" class="btn btn-dark">
                        <fmt:message key="account.replenish"/>
                    </button>
                </div>

            </form>
    </div>

</div>

<jsp:include page="/WEB-INF/templates/_scripts.jsp"></jsp:include>

</body>
</html>
