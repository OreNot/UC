<#import "parts/common.ftl" as c>
<@c.page>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="fio" placeholder="ФИО"/>
        <input type="text" name="organization" placeholder="Организация"/>
        <input type="text" name="comment" placeholder="Комментарий"/>
        <input type="file" name="file" placeholder="Файл"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Зарегистрировать</button>
    </form>


</div>
<a href="allmyorders">Вернуться ко всем</a>
</@c.page>