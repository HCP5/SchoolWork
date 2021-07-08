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
    let tabela=$("#table")[0]
    if(pas===0){
        tabela.rows[x].cells[y].innerHTML=table[x][y];
        pas++;
    }
    else
    if(pas===1){
        if(lastValue!==table[x][y])
        {
            tabela.rows[x].cells[y].innerHTML=table[x][y];
            tabela.rows[x].cells[y].style.backgroundColor="lightcoral";
            tabela.rows[lastX].cells[lastY].style.backgroundColor="lightcoral";
            setTimeout(function (x,y,lastX,lastY){
                tabela.rows[x].cells[y].innerHTML="";
                tabela.rows[lastX].cells[lastY].innerHTML="";

                tabela.rows[x].cells[y].style.backgroundColor="white";
                tabela.rows[lastX].cells[lastY].style.backgroundColor="white";
            },500,x,y,lastX,lastY);

            pas++;
        }
        else{
            tabela.rows[x].cells[y].style.backgroundColor="lightgreen";
            tabela.rows[lastX].cells[lastY].style.backgroundColor="lightgreen";
            tabela.rows[x].cells[y].innerHTML=table[x][y];
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