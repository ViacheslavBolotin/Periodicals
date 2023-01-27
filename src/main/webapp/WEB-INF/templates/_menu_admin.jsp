<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <fmt:message key="main.menu.application"/>
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="frontController?action=users"><fmt:message key="main.menu.button.users"/> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                            <a class="nav-link" href="frontController?action=publications"><fmt:message key="main.menu.button.publications"/></a>
            </li>
            <li class="nav-item">
                            <a class="nav-link" href="frontController?action=topics"><fmt:message key="main.menu.button.publications_topics"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="frontController?action=accounts"><fmt:message key="main.menu.button.accounts"/></a>
            </li>
            <li class="nav-item">
                            <a class="nav-link" href="frontController?action=reports"><fmt:message key="main.menu.button.reports"/></a>
            </li>
            <li class="nav-item">
                            <a class="nav-link" href="frontController?action=user_profile"><fmt:message key="mainmenu.button.profile"/></a>
            </li>
        </ul>

        <a class="navbar-brand" href="#"> <h>${sessionScope.user.userName}</h> </a>

        <div class="dropdown">
            <button class="btn btn-outline-secondary btn-sm" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">
                    language
                </i>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <form class="form-inline" method="post" action="frontController?action=i18n">
                    <button type="submit" name="uk" class="dropdown-item">Ukranian</button>
                    <button type="submit" name="en" class="dropdown-item">English</button>
                </form>
            </div>
        </div>
        <form class="form-inline my-2 my-lg-0" method="post" action="frontController?action=logout">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="mainmenu.button.logout"/></button>
        </form>
    </div>
</nav>
