<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
    <@c.page>

    <div>
        <@l.logout/>
    </div>


    <br>
    <br>
    <form method="get" action="allorders">
        <input type="text" list="listOrg" name="filter"  placeholder="User" value="${filter}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <datalist id="listOrg">

            <option value="2"></option>
            <option value="user"></option>


        </datalist>

        <button type="submit">Найти</button>

        <div>Список заявлений</div>
        <table border="1">
            <tr><td>Id</td><td>Date</td><td>Org</td><td>Fio</td><td>User</td><td>comment</td><td>Executor</td><td>Status</td></tr>
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