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
        <h4>Статистика</h4>
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
    <div class="form-row align-items-center">
        <div class="col-auto">
    <select class="form-control" name="executor"  placeholder="Исполнитель">
        <#list usercol?keys as key>
            <option value="${key}">${key}</option>
        </#list>

    </select>
        </div>
        <div class="col-auto">
        <button type="submit" class="btn btn-primary ml-2">Назначить</button>
        </div>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" name="redact" value="redact" class="custom-control-input" id="defaultChecked2">
            <label class="custom-control-label" for="defaultChecked2">С редактированием</label>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">




    <h4>Список заявлений</h4>
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
                <th scope="col">Файл заявления</th>
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
                    <td><#if statement.filename??>
                        <!--     <a href="file:////orders/${statement.filename}" target="_blank">zl</a> -->
                        <input type="button" value="Файл заявления" onclick="openNewWin('${prefix}/orders/${statement.filename}')"></#if></td>

                    <td><input type="checkbox" name="radio" value="${statement.id}"></td>
                </tr>
                <!--</div>-->
                <#else>
                Пусто
                </#list>
            </tbody>
        </table>
    </div>


</form>
    </div>
</div>
<br>

</@c.page>