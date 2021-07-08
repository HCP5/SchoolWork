
function moveA_B(parent) {
    // console.log(parent[parent.selectedIndex].text)
    const listA = $("#firstList");
    const listB = $("#secondList");
    if(listA[0].id===parent){
        listB.append("<option>"+$(":selected").text()+"</option>")
        $(":selected").remove()
    }else {
        listA.append("<option>"+$(":selected").text()+"</option>")
        $(":selected").remove()
    }
}