function verifyData(){
    let ok = 0;
    document.getElementById('numeInput').style.border="solid  black";
    document.getElementById('varstaInput').style.border="solid black";
    document.getElementById('emailInput').style.border="solid black";
    let nume= document.getElementById('numeInput').value;
    let email=document.getElementById("emailInput").value;
    let varsta= document.getElementById('varstaInput').value;
    let erori = [];
    if(nume===""){
        document.getElementById('numeInput').style.border = "solid red";
        ok=-1;
        erori.push("NumeInvalid");
    }
    if(varsta<18){
        document.getElementById('varstaInput').style.border="solid red";
        ok=-2;
        erori.push("Varsta prea mica");
    }
    var pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!pattern.test(email)){
        document.getElementById('emailInput').style.border="solid red";
        ok=-3;
        erori.push("Email incorect");
    }
    if(ok===0){
        window.alert("Datele completate corect!");
    }
    else {
        erori.forEach(eroare=>window.alert(eroare))
    }

    //
    // if(ok===-1){
    //     window.alert("introduceti nume");
    // }
    // if(ok===-2){
    //     window.alert("Trebuie sa aveti minim 18 ani!");
    // }
    // if(ok===-3){
    //     window.alert("adresa e-mail invalida!");
    // }
}