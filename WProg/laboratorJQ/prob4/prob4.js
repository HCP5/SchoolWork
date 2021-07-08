let rec;

function sort(x){
    let parent33;
    let parent22;
    let parent11;
    if (x==="Pr"){
        const a = $("#Pr1").innerText;
        const b = $("#Pr2").innerText;
        if(a>b){
            parent11 = $("#Ca1");
            parent11.before($("#Ca2"))

            parent11 = $("#Pr1");
            parent11.before($("#Pr2"))

            parent11 = $("#Fr1");
            parent11.before($("#Fr2"))
        }
        else{
            parent11 = $("#Ca2")
            parent11.before($("#Ca1"))


            parent11 = $("#Pr2")
            parent11.before($("#Pr1"))

            parent11 = $("#Fr2")
            parent11.before($("#Fr1"))
        }
    }
    else {
        if (x==="Ca"){
            const a1 = $("Ca1").innerText;
            const b1 = $("Ca2").innerText;
            if(a1>b1){
                let parent1 = $("#Ca2");
                parent1.before($("#Ca1"))

                parent1 = $("#Pr2");
                parent1.before($("#Pr1"))

                parent1 = $("#Fr2");
                parent1.before($("#Fr1"))
            }
        }else{
            parent11 = document.getElementById("Ca2").parentNode;
            parent11.insertBefore(document.getElementById("Ca1"),document.getElementById("Ca2"))

            parent22 = document.getElementById("Pr1").parentNode;
            parent22.insertBefore(document.getElementById("Pr1"),document.getElementById("Pr2"))

            parent33 = document.getElementById("Fr2").parentNode;
            parent33.insertBefore(document.getElementById("Fr1"),document.getElementById("Fr2"))
        }
    }
}

function sortB(x){
    if (x==="Pr"){
        if($("#Pr1").innerText>$("#Pr2").innerText)
        {
            $("#Tr1").before($("#Tr2"))
        }
        else {
            $("#Tr2").before($("#Tr1"))
        }
    }
    else {
        if(document.getElementById("Ca1").innerText>document.getElementById("Ca2").innerText){
            $("#Ca1").before(("#Ca2"))
        }
        else {
            $("#Ca2").before(("#Ca1"))
        }
    }
}

function refactor(){
    rec=!rec
    if(!rec){
        document.getElementById("table").remove()

        document.body.innerHTML="<table id=\"table\">\n" +
            "    <tr id=\"Tr\">\n" +
            "        <th id=\"Fr\" onclick=\"if(rec){sort(this.id)} else{sortB(this.id)}\">Fructe</th>\n" +
            "        <th id=\"Pr\" onclick=\"if(rec){sort(this.id)} else{sortB(this.id)}\">Pret</th>\n" +
            "        <th id=\"Ca\" onclick=\"if(rec){sort(this.id)} else{sortB(this.id)}\">Cantitate</th>\n" +
            "    </tr>\n" +
            "    <tr id=\"Tr1\">\n" +
            "        <td id=\"Fr1\">Mere</td>\n" +
            "        <td id=\"Pr1\">3</td>\n" +
            "        <td id=\"Ca1\">8</td>\n" +
            "    </tr>\n" +
            "    <tr id=\"Tr2\">\n" +
            "        <td id=\"Fr2\">Pere</td>\n" +
            "        <td id=\"Pr2\">4</td>\n" +
            "        <td id=\"Ca2\">6</td>\n" +
            "    </tr>\n" +
            "</table>" +
            "<button onClick=\"refactor()\">resort</button>"
    }
    else {
        document.getElementById("table").remove()
        document.body.innerHTML="<table id=\"table\">\n" +
            "    <tr>\n" +
            "        <th id=\"Fr\" onclick=\"if(rec){sort(this.id)} else{sortB()}\">Fructe</th>\n" +
            "        <td id=\"Fr1\">Mere</td>\n" +
            "        <td id=\"Fr2\">Pere</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <th id=\"Pr\"onclick=\"if(rec){sort(this.id)} else{sortB()}\">Pret</th>\n" +
            "        <td id=\"Pr1\">3</td>\n" +
            "        <td id=\"Pr2\">4</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <th id=\"Ca\" onclick=\"if(rec){sort(this.id)} else{sortB()}\">Cantitate</th>\n" +
            "        <td id=\"Ca1\">8</td>\n" +
            "        <td id=\"Ca2\">6</td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "<button onclick=\"refactor()\">resort</button>"
    }
}