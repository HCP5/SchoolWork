
let a=[
    [0,0,0],
    [0,0,0],
    [0,0,0]
]


a.exists = function (x) {
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if(a[i][j]===x)
                return true
        }
    }
    return false
}

function startGame() {
    tabela=$("#tabela")[0]
    for (let i = 0; i < 3; i++) {
        for (let j=0;j<3;j++){
            let x = Math.floor(1+Math.random()*9);
            while (a.exists(x)){
                x=Math.floor(1+Math.random()*9)
            }
            a[i][j]=x;
            tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML=x//.childNodes[i*2+1].childNodes[j*2+1]
            if(x===9){
                tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML="";//.childNodes[i*2+1].childNodes[j*2+1]
            }
        }
    }
}

function setStartPozition() {
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if(a[i][j]===9)
                return [i,j]
        }
    }
}

let poz

function verifyWin() {
    let b=[
        [1,2,3],
        [4,5,6],
        [7,8,9]
    ]

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if(a[i][j]!==b[i][j])
                return false
        }
    }
    return true
}



$(document).keydown(function (e){
    e = e || window.event;
    console.log(e.keyCode)
    if(e.keyCode===13 && a[0][0]===0){
        startGame()
        poz=setStartPozition()
    }
    // <-:37 ^:38 ->:39 v:40
    if(e.keyCode===37)
    {
        let i=poz[0]
        let j=poz[1]-1

        if(poz[1]<1)
            return
        tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML=""
        tabela.childNodes[1].childNodes[poz[0]*2].childNodes[poz[1]*2+1].innerHTML=a[i][j]
        let aux=a[i][j]
        a[i][j]=a[poz[0]][poz[1]]
        a[poz[0]][poz[1]]=aux
        poz[0]=i
        poz[1]=j
    }
    else if(e.keyCode===38){
        let i=poz[0]-1
        let j=poz[1]

        if(poz[0]<1)
            return
        tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML=""
        tabela.childNodes[1].childNodes[poz[0]*2].childNodes[poz[1]*2+1].innerHTML=a[i][j]
        let aux=a[i][j]
        a[i][j]=a[poz[0]][poz[1]]
        a[poz[0]][poz[1]]=aux
        poz[0]=i
        poz[1]=j
    }
    else if(e.keyCode===39){
        let i=poz[0]
        let j=poz[1]+1

        if(poz[1]>1)
            return
        tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML=""
        tabela.childNodes[1].childNodes[poz[0]*2].childNodes[poz[1]*2+1].innerHTML=a[i][j]
        let aux=a[i][j]
        a[i][j]=a[poz[0]][poz[1]]
        a[poz[0]][poz[1]]=aux
        poz[0]=i
        poz[1]=j
    }
    else if(e.keyCode===40){
        let i=poz[0]+1
        let j=poz[1]

        if(poz[0]>1)
            return

        tabela.childNodes[1].childNodes[i*2].childNodes[j*2+1].innerHTML=""
        tabela.childNodes[1].childNodes[poz[0]*2].childNodes[poz[1]*2+1].innerHTML=a[i][j]
        let aux=a[i][j]
        a[i][j]=a[poz[0]][poz[1]]
        a[poz[0]][poz[1]]=aux
        poz[0]=i
        poz[1]=j
    }
    else if(e.keyCode===87){
        let k=1;
        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {
                tabela.childNodes[1].childNodes[i * 2].childNodes[j * 2 + 1].innerHTML = k
                k++
            }
        }
        tabela.childNodes[1].childNodes[4].childNodes[5].innerHTML=""
        window.alert("Congrats!")
        removeEventListener('keydown',move)
    }

    if(verifyWin()) {
        window.alert("Bravo...\n eu inca nu am reusit sa il fac")
    }
})