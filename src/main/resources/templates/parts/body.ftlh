<#macro body>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Converter</title>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    </head>
    <body>
     <a href="/converter">Конвертер</a>
     <a href="/history">История</a>
    <div>
        <#nested>
    </div>
    </body>
    </html>
</#macro>