<?php
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form name="mainForm" method="post" action="loginThis.php">
    <label>
        email:
        <input type="text" name="email">
    </label>

    <label>
        passwd:
        <input type="password" name="passwd">
    </label>
    <br>
    <button type="submit" name="login" value="1">Login</button>

    <button type="submit" name="login" value="2">CreateAccount</button>
</form>

</body>
</html>

