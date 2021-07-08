let rec;

function sort(x){
    if (x==="Pr"){
        var a = document.getElementById("Pr1").innerText
        var b = document.getElementById("Pr2").innerText
        if(a>b){
            var parent11=document.getElementById("Ca2").parentNode
            parent11.insertBefore(document.getElementById("Ca2"),document.getElementById("Ca1"))

            var parent22=document.getElementById("Pr1").parentNode
            parent22.insertBefore(document.getElementById("Pr2"),document.getElementById("Pr1"))

            var parent33=document.getElementById("Fr2").parentNode
            parent33.insertBefore(document.getElementById("Fr2"),document.getElementById("Fr1"))
        }
        else{

            var parent11=document.getElementById("Ca2").parentNode
            parent11.insertBefore(document.getElementById("Ca1"),document.getElementById("Ca2"))

            var parent22=document.getElementById("Pr1").parentNode
            parent22.insertBefore(document.getElementById("Pr1"),document.getElementById("Pr2"))

            var parent33=document.getElementById("Fr2").parentNode
            parent33.insertBefore(document.getElementById("Fr1"),document.getElementById("Fr2"))
        }
    }
    else {
        if (x==="Ca"){
            var a1 = document.getElementById("Ca1").innerText
            var b1 = document.getElementById("Ca2").innerText
            if(a1>b1){
                var parent1=document.getElementById("Ca2").parentNode
                parent1.insertBefore(document.getElementById("Ca2"),document.getElementById("Ca1"))

                var parent2=document.getElementById("Pr1").parentNode
                parent2.insertBefore(document.getElementById("Pr2"),document.getElementById("Pr1"))

                var parent3=document.getElementById("Fr2").parentNode
                parent3.insertBefore(document.getElementById("Fr2"),document.getElementById("Fr1"))
            }
        }else{
            var parent11=document.getElementById("Ca2").parentNode
            parent11.insertBefore(document.getElementById("Ca1"),document.getElementById("Ca2"))

            var parent22=document.getElementById("Pr1").parentNode
            parent22.insertBefore(document.getElementById("Pr1"),document.getElementById("Pr2"))

            var parent33=document.getElementById("Fr2").parentNode
            parent33.insertBefore(document.getElementById("Fr1"),document.getElementById("Fr2"))
        }
    }
}

function sortB(x){
    if (x==="Pr"){
        if(document.getElementById("Pr1").innerText>document.getElementById("Pr2").innerText)
        {
            document.getElementById("table").childNodes[1].insertBefore(document.getElementById("Tr1"),document.getElementById("Tr2"))
        }
        else {
            document.getElementById("table").childNodes[1].insertBefore(document.getElementById("Tr2"),document.getElementById("Tr1"))
        }
    }
    else {
        if(document.getElementById("Ca1").innerText>document.getElementById("Ca2").innerText){
            document.getElementById("table").childNodes[1].insertBefore(document.getElementById("Tr1"),document.getElementById("Tr2"))
        }
        else {
            document.getElementById("table").childNodes[1].insertBefore(document.getElementById("Tr2"),document.getElementById("Tr1"))
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