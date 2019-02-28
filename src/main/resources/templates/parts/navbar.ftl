<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">UC</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/allorders">Все заявления</a>
            </li>
                <li class="nav-item">
                    <a class="nav-link" href="/setexec">Назначить</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user">SA</a>
                </li>
                </#if>
            <li class="nav-item">
                <a class="nav-link" href="/allmyorders">Заявления на меня</a>
            </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addorder">Добавить</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addtoarchive">В архив</a>
                </li>

        </ul>

        <div class="navbar-text mr-3">${name}</div>
    <@l.logout/>
    </div>
</nav>