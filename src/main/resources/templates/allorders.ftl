<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
    <@c.page>
    <script language="JavaScript">
        <!-- hide
        function openNewWin(url) {
            myWin= open(url);
        }
        // -->
    </script>
    <script>
        function radioClick(elem) {

            switch(elem.value) {
                case 'executorfilter':
                    document.getElementById("executor").disabled = false;
                    document.getElementById("organization").disabled = true;
                    document.getElementById("fio").disabled = true;
                    break;

                case 'orgfilter':
                    document.getElementById("executor").disabled = true;
                    document.getElementById("organization").disabled = false;
                    document.getElementById("fio").disabled = true;
                    break;

                case 'fiofilter':
                    document.getElementById("executor").disabled = true;
                    document.getElementById("organization").disabled = true;
                    document.getElementById("fio").disabled = false;
                    break;

            }
        }
    </script>
    <div>
        <@l.logout/>
    </div>


    <br>
    <br>
    <form method="get" action="allorders">
        <input type="radio" name="radiofilter" onclick="radioClick(this)" value="executorfilter" checked="true">
        <!--<input type="text" id="" list="listOrg" name="filter"  placeholder="Исполнитель">-->
        <select id="executor" name="filter"  placeholder="Исполнитель">
            <option value="user">user</option>
            <option value="Лютов">Лютов</option>
            <option value="Все">Все</option>

        </select>
        <br>
        <br>
        <input type="radio"  name="radiofilter" onclick="radioClick(this)" value="orgfilter">
        <input type="text" id="organization" name="filter"  placeholder="Организация" disabled>
        <br>
        <br>
        <input type="radio" name="radiofilter" onclick="radioClick(this)" value="fiofilter">
        <input type="text" id="fio" name="filter"  placeholder="ФИО" disabled>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <datalist id="listOrg">

            <option value="2"></option>
            <option value="user"></option>


        </datalist>

        <button type="submit">Найти</button>
        <div>Статистика</div>
        <table border="1">
            <tr><td>User</td><td>Col</td></tr>
            <tr><td>user</td><td>${usercol}</td></tr>
            <tr><td>User</td><td>Col</td></tr>
            <tr><td>User</td><td>Col</td></tr>
            <tr><td>User</td><td>Col</td></tr>
        </table>

        <div>Список заявлений</div>
        <table border="1">
            <tr><td>Id</td><td>Date</td><td>Org</td><td>Fio</td><td>User</td><td>Executor</td><td>Status</td><td>comment</td><td>File</td><td>FileLS</td></tr>
            <#list statements as statement>
                <!-- <div>-->
                <tr>
                    <td>${statement.id}</td>
                    <td>${statement.regDate}</td>
                    <td>${statement.clientOrg}</td>
                    <td>${statement.clientFio}</td>
                    <td>${statement.authorName}</td>
                    <td>${statement.executorName}</td>
                    <td>${statement.status}</td>
                    <td>${statement.comment}</td>
                    <td><#if statement.filename??>
                        <input type="button" value="Файл заявления" onclick="openNewWin('/orders/${statement.filename}')"></#if></td>
                    <td><#if statement.packfilename??>

                        <input type="button" value="ЛС" onclick="openNewWin('/orders/${statement.packfilename}')"></#if></td>
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