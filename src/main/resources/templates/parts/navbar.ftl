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
                <a class="nav-link" href="${prefix}/allorders">Все заявления</a>
            </li>
                <li class="nav-item">
                    <a class="nav-link" href="${prefix}/setexec">Назначить</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${prefix}/user">SA</a>
                </li>
                </#if>
            <li class="nav-item">
                <a class="nav-link" href="${prefix}/allmyorders">Заявления на меня</a>
            </li>
                <li class="nav-item">
                    <a class="nav-link" href="${prefix}/addorder">Добавить</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${prefix}/addtoarchive">В архив</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="${prefix}/user/profile">Профиль</a>
                </li>

        </ul>

        <div class="navbar-text mr-3">${name}</div>
    <@l.logout/>
    </div>
</nav>