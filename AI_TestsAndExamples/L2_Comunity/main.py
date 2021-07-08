import networkx as nx
from ga import GA
import collections

def modularity(communities, param):
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if (communities[i] == communities[j]):
               Q += (mat[i][j] - degrees[i] * degrees[j] / M)
    return Q * 1 / M


def daNodDeStart(graphDolph):
    min = len(graphDolph) + 1
    max = -1
    for e in graphDolph:
        if e<min:
            min=e
        if e>max:
            max=e
    return min,max


def getAdiacent(graphDolph):
    matrixA = [[0 for i in range(len(graphDolph))] for j in range(len(graphDolph))]
    G = nx.edges(graphDolph)
    for edge in G:
        matrixA[edge[0]][edge[1]] = 1
        matrixA[edge[1]][edge[0]]=1
    return matrixA


if __name__ == '__main__':
    # file=open("JZ.txt",'w')
    graphDolph=nx.read_gml("data/dolphins/dolphins.gml",label="id")
    # graphDolph=nx.read_gml("data/football/football.gml",label="id")
    # graphDolph=nx.read_gml("data/karate/karate_relabel.gml",label="id")
    # graphDolph=nx.read_gml("data/krebs/krebs.gml",label="id")
    # graphDolph=nx.read_gml("data/lesin.gml",label="id")
    graphDolph=nx.read_gml("data/JZ.gml", label="id")

    min,max=daNodDeStart(graphDolph)
    matrixA=getAdiacent(graphDolph)
    noEdges=2*nx.number_of_edges(graphDolph)
    degres=[]
    for line in matrixA:
        degres.append(sum(line))
    #print matrix
    # for i in range(len(matrixA)):
    #     for j in range(len(matrixA)):
    #         if matrixA[i][j].__eq__(1):
    #             print((i,j))
    gaParam = {'popSize': 100 ,'noGen':1000,'noEdges':noEdges,'mat':matrixA,'degrees': degres,'noNodes': len(graphDolph)}
    problParam = {'min': min, 'max': max, 'function': modularity, 'noNodes': len(graphDolph)}

    ga = GA(gaParam, problParam)
    ga.initialisation()
    ga.evaluation()
    allBestFitnesses = []
    allAvgFitnesses = []
    generations = []
    for g in range(gaParam['noGen']):
        # plotting preparation


        # allPotentialSolutionsX = [c.repres for c in ga.population]
        # allPotentialSolutionsY = [c.fitness for c in ga.population]
        # bestSolX = ga.bestChromosome().repres
        # bestSolY = ga.bestChromosome().fitness
        # allBestFitnesses.append(bestSolY)
        # allAvgFitnesses.append(sum(allPotentialSolutionsY) / len(allPotentialSolutionsY))
        # generations.append(g)


        # logic alg
        # ga.oneGeneration()
        ga.oneGenerationElitism()
        # ga.oneGenerationSteadyState()

        bestChromo = ga.bestChromosome()
        bestChromoMem = bestChromo.repres
        bestChromoDic={}
        for i in range(len(bestChromoMem)):
            bestChromoDic[bestChromoMem[i]]=[]
        for i in range(len(bestChromoMem)):
            bestChromoDic[bestChromoMem[i]].append(i)
        print('Numarul de comunitati:' +str(len(bestChromoDic.keys())))
        print('Best solution in generation ' + str(g) + ' is: x = ' + str(bestChromoDic) + ' f(x) = ' + str(
            bestChromo.fitness))
        # file.write('Numarul de comunitati:' +str(len(bestChromoDic.keys()))+'\n')
        # file.write('Best solution in generation ' + str(g) + ' is: x = ' + str(bestChromoDic) + ' f(x) = ' + str(
        #     bestChromo.fitness)+'\n')
