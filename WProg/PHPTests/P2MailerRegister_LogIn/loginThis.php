<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
    if($_POST["login"]==="1"){

        $con = new mysqli('127.0.0.1', 'root', '', 'trains');
        $con->set_charset("utf8");

        $sql = "SELECT * FROM users";
        $result = $con->query($sql);
        $arrSource =array();

        echo "connecting to ".$_POST["email"]."...<br>";

        $ok=1;

        while ($row = $result->fetch_assoc()) {
            if ($row["email"] === $_POST["email"] and $row["passwd"] === $_POST["passwd"]) {
                echo "ConectatCuSucces!";
                $ok=0;
            }
            else{
                if($row["email"] === $_POST["email"] and $row["passwd"] !== $_POST["passwd"]) {
                    echo "parola Incorecta!";
                    $ok=0;
                }

            }
        }
        if($ok===1)
            echo "Nu aveti cont!";
    }
    else
    {

        if(isset($_POST["email"])){
            $email=$_POST["email"];
            if($email==''){
                unset($email);
            }
        }
        if(isset($_POST["passwd"])){
            $passwd=$_POST["passwd"];
            if($passwd==''){
                unset($passwd);
            }
        }
        if(!empty($email) && !empty($passwd)){
            $passwd=password_hash($passwd,PASSWORD_DEFAULT);
            function getToken($len=32){
                return substr(md5(openssl_random_pseudo_bytes(20)),-$len);
            }
            $token=getToken(10);
            $con = new mysqli('127.0.0.1', 'root', '', 'trains');
            $con->set_charset("utf8");
            $insert=$con->prepare("INSERT into users set 
                email=?,
                passwd=?,
                token=?"
            );
            $insert->bind_param("sss",$email,$passwd,$token);
            $insert->execute();

            require "vendor/autoload.php";
            //MYTRY
            $to_email = "PsyHo.3D@gmail.com";
            $subject = "Simple Email Test via PHP";
            $body = 'Activate email:
                <a href="http://localhost/PHP_Lab/P2/verification.php?email='.$email.'&token='.$token.'">Activate</a>';
            $headers = "From: sender email";

            if (mail($to_email, $subject, $body, $headers)) {
                echo "Email successfully sent to $to_email...";
            } else {
                echo "Email sending failed...";
            }
            $mail = new PHPMailer(true);
            try {
                $mail->setFrom('PsyHo.3D@gmail.com', "User Registration");
                $mail->addAddress('PsyHo.3D@gmail.com');
                $mail->isHTML(true);
                $mail->Subject="Confirm email";
                $mail->Body='Activate email:
                <a href="/localhost/PHP_Lab/P2/verification.php?email='.$email.'&token='.$token.'">Activate</a>';

                $mail->send();
                echo "Sent";
            }catch (\Exception $e)
            {
                echo "Messageunsent",$mail->ErrorInfo;
            }
        }
}
    ?>
<a href="http://localhost/PHP_Lab/P2/verification.php?email="></a>
