<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

User editor

<form action="${prefix}/user" method="post">
    <input type="text" value="${user.username}" name="username">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="text" value="${user.id}" name="userId">
    <input type="text" name="fio">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>

</@c.page>