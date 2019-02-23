<#import "parts/common.ftl" as c>
<@c.page>
<div>
    <form method="post">
        <input type="text" name="comment" placeholder="Комментарий"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Зарегистрировать</button>
    </form>


</div>
<a href="allorders">Вернуться ко всем</a>
</@c.page>