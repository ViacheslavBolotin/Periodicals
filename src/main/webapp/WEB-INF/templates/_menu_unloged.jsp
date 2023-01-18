<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <fmt:message key="main.menu.application"/>
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

        </ul>
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

        <form class="form-inline my-2 my-lg-0" method="post" action="frontController?action=publications">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="main.menu.button.publications"/></button>
        </form>
        <form class="form-inline my-2 my-lg-0" method="post" action="frontController?action=login&main_menu=1">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="mainmenu.button.login"/></button>
        </form>
        <form class="form-inline my-2 my-lg-0" method="post" action="frontController?action=new_user">
            <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><fmt:message key="mainmenu.button.register"/></button>
        </form>
    </div>
</nav>
