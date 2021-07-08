class tuple{
    constructor(a, b) {
        this._a = a;
        this._b = b;
    }

    get a() {
        return this._a;
    }

    get b() {
        return this._b;
    }
}

const table = [[1, 2, 3, 4],
    [4, 6, 6, 5],
    [3, 2, 1, 5]];

let x = -1;
let y = -1;

let lastX = -1;
let lastY = -1;
let lastValue=-1;

let pas=0;

pairs=0;

function showCell(){
    if(pas===0){
        document.getElementById('table').rows[x].cells[y].innerHTML=table[x][y];
        pas++;
    }
    else
    if(pas===1){
        if(lastValue!==table[x][y])
        {
            document.getElementById('table').rows[x].cells[y].innerHTML=table[x][y];
            document.getElementById('table').rows[x].cells[y].style.backgroundColor="lightcoral";
            document.getElementById('table').rows[lastX].cells[lastY].style.backgroundColor="lightcoral";
            setTimeout(function (x,y,lastX,lastY){
                document.getElementById('table').rows[x].cells[y].innerHTML="";
                document.getElementById('table').rows[lastX].cells[lastY].innerHTML="";

                document.getElementById('table').rows[x].cells[y].style.backgroundColor="white";
                document.getElementById('table').rows[lastX].cells[lastY].style.backgroundColor="white";
            },1500,x,y,lastX,lastY);

            pas++;
        }
        else{
            document.getElementById('table').rows[x].cells[y].style.backgroundColor="lightgreen";
            document.getElementById('table').rows[lastX].cells[lastY].style.backgroundColor="lightgreen";
            document.getElementById('table').rows[x].cells[y].innerHTML=table[x][y];
            pas++;
            pairs++;
        }
    }
    if(pas===2){
        pas=0;
    }
    if(pairs===6){
        window.alert("FELICITARI!")
        var p=document.createElement("a");
        p.href="Prob3.html";
        p.text="Pentru un nou joc click aici";
        document.body.appendChild(p);
    }


    lastX=x;
    lastY=y;
    lastValue=table[x][y];
}