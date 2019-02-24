<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<div>
    <@l.logout/>
</div>


<br>
<br>
<form method="get" action="allmyorders">
    <input type="text" list="listOrg" name="status"  placeholder="Status">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <datalist id="listOrg">

        <option value="В работе"></option>
        <option value="Завершено"></option>


    </datalist>

    <button type="submit">Изменить</button>

    <div>Список заявлений</div>
    <table border="1">
        <tr><td>Id</td><td>Date</td><td>Org</td><td>Fio</td><td>User</td><td>comment</td><td>Executor</td><td>Status</td><td>Radio</td></tr>
        <#list statements as statement>
            <!-- <div>-->
            <tr>
                <td>${statement.id}</td>
                <td>${statement.regDate}</td>
                <td>${statement.clientOrg}</td>
                <td>${statement.clientFio}</td>
                <td>${statement.authorName}</td>
                <td>${statement.comment}</td>
                <td>${statement.executorName}</td>
                <td>${statement.status}</td>
                <td><input type="radio" name="radio" value="${statement.id}"></td>
            </tr>
            <!--</div>-->
        <#else>
            No statements
        </#list>
    </table>

</form>

<br>
<a href="addorder">Добавить</a>
<a href="setexec">Назначить</a>
</@c.page>