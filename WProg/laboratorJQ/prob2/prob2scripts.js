function verifyData(){
    let ok = 0;
    let numeInput=$("#numeInput")
    let varstaInput=$("#varstaInput")
    let emailInput=$("#emailInput")
    numeInput.css("border","solid  black")
    varstaInput.css("border","solid  black")
    emailInput.css("border","solid  black")
    let nume= numeInput.val();
    let email= emailInput.val();
    let varsta= varstaInput.val();
    let erori = [];
    if(nume===""){
        numeInput.css("border","solid red")
        ok=-1;
        erori.push("NumeInvalid");
    }
    if(varsta<18){
        varstaInput.css("border","solid red")
        ok=-2;
        erori.push("Varsta prea mica");
    }
    var pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!pattern.test(email)){
        emailInput.css("border","solid red")
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