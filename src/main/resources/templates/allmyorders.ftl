<#import "parts/common.ftl" as c>
<@c.page>


<br>
<br>
<form method="get" action="allmyorders">
    <!--<input type="text" list="listOrg" name="status"  placeholder="Status">-->
    <select  name="status"  placeholder="Status">
        <option value="В работе">В работе</option>
        <option value="Завершено">Завершено</option>
        <option value="Ждёт отправки в архив">Ждёт отправки в архив</option>
        <option value="В архиве">В архиве</option>

    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <datalist id="listOrg">

        <option value="В работе"></option>
        <option value="Завершено"></option>
        <option value="Ждёт отправки в архив"></option>
        <option value="В архиве"></option>


    </datalist>

    <button type="submit">Изменить</button>

    <div>Список заявлений</div>
    <table border="1">
        <tr><td>Id</td><td>Date</td><td>Org</td><td>Fio</td><td>User</td><td>comment</td><td>Executor</td><td>Status</td><td>File</td><td>FileLS</td><td>Radio</td></tr>
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
                <td><#if statement.filename??>
                    <a href="/orders/${statement.filename}" target="_blank">Файл заявления</a>
                    <input type="button" value="Файл заявления" onclick="openNewWin('/orders/${statement.filename}')"></#if></td>
                <td><#if statement.packfilename??>
                    <a href="/orders/${statement.packfilename}" target="_blank">ЛС</a>
                    <input type="button" value="ЛС" onclick="openNewWin('/orders/${statement.packfilename}')"></#if></td>
                <td><input type="radio" name="radio" value="${statement.id}"></td>
            </tr>
            <!--</div>-->
        <#else>
            No statements
        </#list>
    </table>

</form>

<br>

<a href="addorder">Зарегистрировать заявление</a>
<a href="addtoarchive">Отправить в архив</a>

</@c.page>