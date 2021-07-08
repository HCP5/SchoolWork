<?php
$con = new mysqli('127.0.0.1', 'root', '', 'trains');
$con->set_charset("utf8");
if($_GET){
    echo "Merge";
    if(isset($_GET["email"])){
        $email=$_GET["email"];
        if($email==''){
            unset($email);
        }
    }
    if(isset($_GET["token"])){
        $token=$_GET["token"];
        if($token==''){
            unset($token);
        }
    }
    if(!empty($email) && !empty($token)) {
        echo "Merge";
        $select=$con->prepare("select idUser from users where email=? and token=?");
        $select->bind_param("ss",$email,$token);
        $select->execute();
        if($select->fetch()>0){
            echo "Merge";
            echo $email;
            $con = new mysqli('127.0.0.1', 'root', '', 'trains');
            $con->set_charset("utf8");
           $update=$con->prepare("update users set verifyed=1, token='' where email='awdawd@yahoo.com'");
           $update->execute();
        }
    }
}
