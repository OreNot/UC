<#import "parts/common.ftl" as c>

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

<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" placeholder="Файл"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">В архив</button>

    <div>Список заявлений</div>
    <table border="1">
        <tr><td>Id</td><td>Date</td><td>Org</td><td>Fio</td><td>User</td><td>comment</td><td>Executor</td><td>Status</td><td>FIle</td><td>LS</td><td>Radio</td></tr>
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


</@c.page>