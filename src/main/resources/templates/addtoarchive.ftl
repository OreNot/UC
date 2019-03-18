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
    <form method="post" enctype="multipart/form-data" id="js-upload-form">


        <div class="file-upload-wrapper mb-2">
            <input type="file"  name="file" id="input-file-now" class="file-upload"/>
            <label class="input-file-label" for="input-file-now">Лицевой счет</label>
        </div>

        <div class="custom-control custom-checkbox">
            <input type="checkbox" name="includezl" value="includezl" class="custom-control-input" id="defaultChecked2">
            <label class="custom-control-label" for="defaultChecked2">Включая ЗЛ</label>
        </div>

    <input type="text" name="catNum" class="form-control col-sm-5 mt-2 mb-2" placeholder="Номер папки"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary mb-2">В архив</button>

        <div><h4>Список заявлений</h4></div>
        <table class="table" title="">
            <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Организация</th>
                <th scope="col">Ф.И.О</th>
                <th scope="col">Инициатор</th>
                <th scope="col">Комментарий</th>
                <th scope="col">Исполнитель</th>
                <th scope="col">Статус</th>
                <th scope="col">Заявление</th>
                <th scope="col">Лицевой счет</th>
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
                  <input type="button" value="Файл заявления" onclick="openNewWin('${prefix}/orders/${statement.filename}')"></#if></td>
                <td><#if statement.packfilename??>
                   <input type="button" value="ЛС" onclick="openNewWin('${prefix}/orders/${statement.packfilename}')"></#if></td>
                <td><input type="radio" name="radio" value="${statement.id}"></td>
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