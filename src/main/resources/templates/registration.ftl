<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Login page
Addd user
    ${message?ifExists}
<@l.login "/registration" />
</@c.page>