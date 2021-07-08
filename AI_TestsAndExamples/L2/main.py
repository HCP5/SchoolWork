def getData(tree):
    n = int(dataFile.readline())
    for i in range(n):
        b = []
        line = dataFile.readline()
        line = line.split(",")
        for e in line:
            b.append(int(e))
        tree.__setitem__(i + 1, b)
    source = int(dataFile.readline())
    target = int(dataFile.readline())
    return n, source, target


def equal(a, b):
    return a == b


def getROAD_AB(A, B, treeSource, n=None):
    if n is None:
        verif = False
    else:
        verif = equal

    coada = []
    listaParcurgere = []
    dimensiuneDrum = 0
    vizitati = []
    if (not verif):
        coada.append(A)
    else:
        coada.append(B)
    while len(coada):
        min = None
        vecinMinim = 0
        nodCurent = coada.pop()
        vizitati.append(nodCurent)
        listaParcurgere.append(nodCurent)
        for i in range(len(tree.__getitem__(nodCurent))):
            if not vizitati.__contains__(i + 1):
                if min is None:
                    min = tree.__getitem__(nodCurent)[i]
                    vecinMinim = i + 1
                else:
                    if tree.__getitem__(nodCurent)[i] < min:
                        min = tree.__getitem__(nodCurent)[i]
                        vecinMinim = i + 1
        if not len(vizitati) == len(tree.__getitem__(nodCurent)):
            coada.append(vecinMinim)
            dimensiuneDrum += tree.__getitem__(nodCurent)[vecinMinim - 1]
        if verif is not False:
            if verif(vecinMinim, A):
                listaParcurgere.append(vecinMinim)
                listaParcurgere.reverse()
                return listaParcurgere, dimensiuneDrum
    return listaParcurgere, dimensiuneDrum


def writeSolution(outputFile, n, optimalTrans, valueTrans, lSD, pSD, vSD):
    outputFile.write(str(n) + "\n")
    outputFile.write(str(optimalTrans) + "\n")
    outputFile.write(str(valueTrans) + "\n")
    outputFile.write(str(lSD) + "\n")
    outputFile.write(str(pSD) + "\n")
    outputFile.write(str(vSD) + "\n")


if __name__ == '__main__':
    dataFile = open("hard.txt", "r")
    outputFile = open("hardOut.txt", "w")
    tree = {}
    n, source, target = getData(tree)
    # print(tree.__getitem__(3)[3])
    optimalTrans, valueTrans = getROAD_AB(1, n, tree)
    valueTrans += tree.__getitem__(n)[0]
    pSD, vSD = getROAD_AB(source, target, tree, n)
    writeSolution(outputFile, n, optimalTrans, valueTrans, len(pSD), pSD, vSD)
