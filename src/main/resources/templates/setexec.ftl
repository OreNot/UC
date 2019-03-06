<#import "parts/common.ftl" as c>
<@c.page>


<div>${urlprefixPath}</div>
<br>
<br>
<form method="get" action="setexec">
    <input type="text" list="listOrg" name="executor"  placeholder="Исполнитель">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <datalist id="listOrg">

        <option value="2"></option>
        <option value="user"></option>


    </datalist>

    <button type="submit">Назначить</button>

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
<a href="allorders">Ко всем</a>
</@c.page>