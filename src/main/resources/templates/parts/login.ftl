<#macro login path isRegisterForm>
    <#include "security.ftl">
<form action="${path}" method="post">
    <div class="form-group row mt-3">
        <label class="col-sm-2 col-form-label"> User Name : </label>
        <div class="col-sm-5">
        <input type="text" name="username" class="form-control" placeholder="username"/>
        </div>
    </div>
    <div class="form-group row">
        <label  class="col-sm-2 col-form-label"> Password:  </label>
        <div class="col-sm-5">
        <input type="password" name="password" class="form-control" placeholder="password"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <#if isRegisterForm>${urlprefixPath} </#if>
    <#if !isRegisterForm><a href="${prefix}/registration">Регистрация</a> </#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <button  class="btn btn-primary" type="submit">Выход</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</#macro>