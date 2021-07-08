function moveA_B(parent) {
    // console.log(parent[parent.selectedIndex].text)
    var listA=document.getElementById("firstList");
    var listB=document.getElementById("secondList");
    var option=document.createElement("option");
    if(listA.id===parent.id){
        option.text=parent[parent.selectedIndex].text;
        listB.add(option)
        parent.remove(parent.selectedIndex)
    }else {
        option.text=parent[parent.selectedIndex].text;
        listA.add(option)
        parent.remove(parent.selectedIndex)
    }
}