<?php
    $checked1=0;
    $checked2=1;

    function printSourceToDestination(){
        echo "merge";
    }

    function checkBoxChange($check){
        global $checked1;
        global $checked2;
        if($check==1){
            $set=($checked1-1)*(-1);
            $checked1=$set;
        }else{
            $set=($checked2-1)*(-1);
            $checked2=$set;
        }
}
    function getSource(){
        $con = new mysqli('127.0.0.1', 'root', '', 'trains');
        $con->set_charset("utf8");

        $sql = "SELECT * FROM myTrains";
        $result = $con->query($sql);

        $arrSource =array();

        while ($row = $result->fetch_assoc()) {
            array_push($arrSource,$row["localitatePlecare"]);
        }
        $arrSource=array_unique($arrSource);
        foreach($arrSource as &$value){
            print "<option> $value </option>";
        }

    }
    function getDestination(){
        $con = new mysqli('127.0.0.1', 'root', '', 'trains');
        $con->set_charset("utf8");

        $sql = "SELECT * FROM myTrains";
        $result = $con->query($sql);

        $arrSource =array();

        while ($row = $result->fetch_assoc()) {
            array_push($arrSource,$row["localitateSosire"]);
        }
        $arrSource=array_unique($arrSource);
        $iter=1;
        foreach($arrSource as &$value){
            print "<option> $value </option>";
            $iter++;
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Problem1</title>
    <script>
    </script>
</head>
<body>
<span style="font-size: 20px">
    C:<br>
O baza de date contine trenuri caracterizate de: nr. tren, tip tren,<br>
localitate plecare, localitate sosire, ora plecare, ora sosire. Un calator va putea cauta<br>
        trenuri intre doua localitati, specificand prin intermediul unui check box daca doreste numai<br>
        curse directe sau si curse cu legatura (schimbarea trenului intr-o localitate intermediara).<br>
</span>
<div style="border-bottom: solid black"></div>
<div style="padding: 20px;">
    <label style="font-size: 20px; color: darkgreen;">
    Source:
        <select name="Source" form="form">
        <?php getSource();?>
        </select>
    </label>
</div>
<div style="padding: 20px;">
    <label style="font-size: 20px; color: yellowgreen">
    Destination:
        <select name="Destination" form="form">
            <?php getDestination();?>
        </select>
    </label>
</div>
<div>
    <label style="display: inline-block">
        <input id="check1" type="checkbox" name="check1" form="form" value="1">
    Direct route
</label>
    <label style="display: inline-block">
        <input id="check2" type="checkbox" name="check1" form="form" value="2">
    Indirect route
</label>
</div>
<form id="form" action="index.php" method="post">
    <button type="submit">Search</button>
</form>
<?php
if($_POST)
{//doar daca is selectate checkboxurile le ia!
    $option=$_POST["check1"]."<br>";
    $con = new mysqli('127.0.0.1', 'root', '', 'trains');
    $con->set_charset("utf8");

    $sql = "SELECT * FROM myTrains";
    $result = $con->query($sql);
    $arrSource =array();
    if($_POST["check1"]==="1")
    {while ($row = $result->fetch_assoc()) {
        if ($row["localitatePlecare"] === $_POST["Source"] and $row["localitateSosire"] === $_POST["Destination"]) {
            echo $row["localitatePlecare"] . " " . $row["localitateSosire"] . " " . $row["oraPlecare"] . ";<br>";
        }
    }
    echo "<br>";}
    else{
    $sql = "SELECT * FROM myTrains";
    $result = $con->query($sql);
    $source=$_POST["Source"];
    $dest=$_POST["Destination"];
    while ($row = $result->fetch_assoc()){
        if($row["localitatePlecare"]===$source){
            echo $row["localitatePlecare"]." -> ";
            $source=$row["localitateSosire"];
        }
        if($row["localitateSosire"]===$dest){
            echo $dest;
            break;
        }
    }
    }

}?>
</body>
</html>
