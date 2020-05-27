<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isEmployee = user.isEmployee()
    >
    <#else>
    <#assign
        isAdmin = false
        isEmployee = false
        >
</#if>