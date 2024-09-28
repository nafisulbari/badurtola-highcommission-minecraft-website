<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
</head>
<body>
<header>
    <h1>${header}</h1>
</header>
<nav>
    <ul>
        <#list menuItems as item>
            <li><a href="${item.link}">${item.name}</a></li>
        </#list>
    </ul>
</nav>
<main>
    <section>
        <h2>${sectionTitle}</h2>
        <p>${content}</p>
    </section>
</main>
<footer>
    <p>&copy; ${year} Your Company</p>
</footer>
</body>
</html>