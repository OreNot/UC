<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<script language="JavaScript">
    <!-- hide
    function openNewWin(url) {
        myWin= open(url);
    }
    // -->
</script>

<br>
<br>
<div class="form-row">
    <div class="form-group col-md-6">
<form method="get" action="setexec">

    <div>

        <table class="table" title="Статистика">
            <thead>
            <tr>
                <th scope="col">Оператор</th>
                <th scope="col">Количество заявлений</th>
            </tr>
            </thead>
            <tbody>
                <#list usercol?keys as key>
                <tr><td>${key}</td><td>${usercol[key]}</td></tr>
                </#list>
            </tbody>


        </table>
    </div>

    <select class="form-control mb-2" name="executor"  placeholder="Исполнитель">
        <#list usercol?keys as key>
            <option value="${key}">${key}</option>
        </#list>

    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit" class="btn btn-primary mb-2">Назначить</button>

    <div>Список заявлений</div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Организация</th>
            <th scope="col">Ф.И.О</th>
            <th scope="col">Инициатор</th>
            <th scope="col">Комментарий</th>
            <th scope="col">Исполнитель</th>
            <th scope="col">Статус</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list statements as statement>
            <!-- <div>-->
            <tr>
                <!--<td>${statement.id}</td>-->
                <td>${statement.regDate}</td>
                <td>${statement.clientOrg}</td>
                <td>${statement.clientFio}</td>
                <td>${statement.authorName}</td>
                <td>${statement.comment}</td>
                <td>${statement.executorName}</td>
                <td>${statement.status}</td>
                <td><input type="checkbox" name="radio" value="${statement.id}"></td>
            </tr>
            <!--</div>-->
        <#else>
            Пусто
        </#list>
        </tbody>
    </table>

</form>
    </div>
</div>
<br>

</@c.page>