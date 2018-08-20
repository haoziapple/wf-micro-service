<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2>${.now?string["yyyy-MM-dd hh:mm:ss"]}</h2>
<h3>使用fm特殊变量，参考：http://freemarker.foofun.cn/ref_specvar.html</h3>
<p>Page generated: ${.now}</p>
<p>Freemarker version: ${.version}</p>
<p>Current template name: ${.current_template_name}</p>
</body>
</html>