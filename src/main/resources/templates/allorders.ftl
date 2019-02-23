<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
    <@c.page>

    <div>
        <@l.logout/>
    </div>
<div>Список заявлений</div>
    <#list statements as statement>
    <div>
    <b>${statement.id}</b>
    <b>${statement.comment}</b>
    <b>${statement.authorName}</b>
    </div>
    <#else>
    No statements
    </#list>


    <br>
    <br>
    <form method="get" action="allorders">
        <input type="text" list="listOrg" name="filter"  placeholder="User" value="${filter}">
        <datalist id="listOrg">

            <option value="2"></option>
            <option value="hello"></option>


        </datalist>

        <button type="submit">Найти</button>
    </form>

    <br>
    <a href="addorder">Добавить</a>
</@c.page>