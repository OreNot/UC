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
    <img src="/img/greenatom.png" class="rounded float-left" width="145" height="159">
    <div>

        <table class="table" title="Статистика">
            <thead>
            <tr>
                <th scope="col">User</th>
                <th scope="col">Col</th>
            </tr>
            </thead>
            <tbody>
                <#list usercol?keys as key>
                <tr><td>${key}</td><td>${usercol[key]}</td></tr>
                </#list>
            </tbody>


        </table>
    </div>


    <br>
    <div class="form-row">
        <div class="form-group col-md-6">
    <form method="get" action="allorders">
        <div class="form-check form-check-inline mb-1">
        <input type="radio" class="form-check-input" name="radiofilter" onclick="radioClick(this)" value="executorfilter" checked="true">
        <!--<input type="text" id="" list="listOrg" name="filter"  placeholder="Исполнитель">-->
        <select id="executor" class="form-control" name="filter"  placeholder="Исполнитель">
            <option value="user">user</option>
            <option value="Лютов">Лютов</option>
            <option value="Все">Все</option>

        </select>
        </div>
        <br>
        <div class="form-check form-check-inline mb-1">
        <input type="radio" class="form-check-input" name="radiofilter" onclick="radioClick(this)" value="orgfilter">
        <input type="text" id="organization" class="form-control"  name="filter"  placeholder="Организация" disabled>
        </div>
        <br>
        <div class="form-check form-check-inline mb-1">
        <input type="radio" class="form-check-input" name="radiofilter" onclick="radioClick(this)" value="fiofilter">
        <input type="text" id="fio"  class="form-control" name="filter"  placeholder="ФИО" disabled>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <datalist id="listOrg">

            <option value="2"></option>
            <option value="user"></option>


        </datalist>
        <br>
        <button type="submit" class="btn btn-primary mb-2">Найти</button>


        <div>Список заявлений</div>
        <table  class="table mt-2">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Date</th>
                <th scope="col">Org</th>
                <th scope="col">Fio</th>
                <th scope="col">User</th>
                <th scope="col">Executor</th>
                <th scope="col">Status</th>
                <th scope="col">comment</th>
                <th scope="col">File</th>
                <th scope="col">FileLS</th>
                <th scope="col">StType</th>
            </tr>
            </thead>
           <tbody>
            <#list statements as statement>
                <!-- <div>-->
                <tr>
                    <th scope="row">${statement.id}</th>
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
                    <td></td>
                </tr>
                <!--</div>-->
            <#else>
                No statements
            </#list>
            </tbody>
        </table>

    </form>
        </div>
    </div>


</@c.page>