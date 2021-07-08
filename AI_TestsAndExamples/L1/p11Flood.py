ijVizitat = []

coadaVizitare = []

matrix = \
    [[1, 1, 1, 1, 0, 0, 1, 1, 0, 1],
     [1, 0, 0, 1, 1, 0, 1, 1, 1, 1],
     [1, 0, 0, 1, 1, 1, 1, 1, 1, 1],
     [1, 1, 1, 1, 0, 0, 1, 1, 0, 1],
     [1, 0, 0, 1, 1, 0, 1, 1, 0, 0],
     [1, 1, 0, 1, 1, 0, 0, 1, 0, 1],
     [1, 1, 1, 0, 1, 0, 1, 0, 0, 1],
     [1, 1, 1, 0, 1, 1, 1, 1, 1, 1]]


def ifInBond(param, param1):
    return -1 < param < len(matrix) and -1 < param1 < len(matrix[0])


for i in range(8):
    for j in range(10):
        if matrix[i][j] == 0 and not ijVizitat.__contains__((i, j)):
            coadaVizitare.append((i, j))
            ok = 1
            listaSol=[]
            while len(coadaVizitare) != 0:
                currentNeighbour = coadaVizitare.pop(0)
                if currentNeighbour[0] == 0 or currentNeighbour[1] == 0 or currentNeighbour[0] == len(matrix) - 1 or \
                        currentNeighbour[1] == len(matrix[0]) - 1:
                    ok = 0
                if (currentNeighbour[0] < len(matrix) and currentNeighbour[1] < len(matrix[0])) and (
                        currentNeighbour[0] > -1 and currentNeighbour[1] > -1 and not ijVizitat.__contains__(
                    currentNeighbour)):
                    if ifInBond(currentNeighbour[0], currentNeighbour[1] + 1) and not ijVizitat.__contains__(
                            (currentNeighbour[0], currentNeighbour[1] + 1)):
                        if matrix[currentNeighbour[0]][currentNeighbour[1]+1] == 0:
                            coadaVizitare.append((currentNeighbour[0], currentNeighbour[1] + 1))
                    if ifInBond(currentNeighbour[0] + 1, currentNeighbour[1]) and not ijVizitat.__contains__(
                            (currentNeighbour[0] + 1, currentNeighbour[1])):
                        if matrix[currentNeighbour[0]+1][currentNeighbour[1]] == 0:
                            coadaVizitare.append((currentNeighbour[0] + 1, currentNeighbour[1]))
                    if ifInBond(currentNeighbour[0], currentNeighbour[1] - 1) and not ijVizitat.__contains__(
                            (currentNeighbour[0], currentNeighbour[1] - 1)):
                        if matrix[currentNeighbour[0]][currentNeighbour[1]-1] == 0:
                            coadaVizitare.append((currentNeighbour[0], currentNeighbour[1] - 1))
                    if ifInBond(currentNeighbour[0] - 1, currentNeighbour[1]) and not ijVizitat.__contains__(
                            (currentNeighbour[0] - 1, currentNeighbour[1])):
                        if matrix[currentNeighbour[0] - 1][currentNeighbour[1]] == 0:
                            coadaVizitare.append((currentNeighbour[0] - 1, currentNeighbour[1]))
                    ijVizitat.append(currentNeighbour)
                    listaSol.append(currentNeighbour)

            if ok == 1:
                for vecin in listaSol:
                    matrix[vecin[0]][vecin[1]]=1
for i in range(len(matrix)):
    print(matrix[i])
