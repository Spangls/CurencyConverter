<#macro body>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Converter</title>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="filters.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
    <a href="/configurator">Конфигуратор</a>
    <a href="/items">Товары</a>
    <div>
        <#nested>
    </div>
    </body>
    </html>
</#macro>