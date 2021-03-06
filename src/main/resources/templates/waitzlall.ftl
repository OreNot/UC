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
        <form method="post" enctype="multipart/form-data">
            <!--
            <div class="custom-file">
                <input type="file" name="file" id="customFile"/>
                <label class="custom-file-label" for="customFile">Выберите файл</label>
            </div>
            -->
            <div class="file-upload-wrapper mb-2">
                <input type="file"  name="file" id="input-file-now" class="file-upload" />
                <label class="input-file-label" for="input-file-now">Зелёный лист</label>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary mb-2">Добавить ЗЛ</button>

            <div><h4>Список заявлений без ЗЛ</h4></div>
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