from chromosome import Chromosome
from ga import GA


def fitnessCalculatorGEN(communities, param):
    noNodes = param['noNodes']
    mat = param['mat']
    M = param['bigRoad']
    Q = 0.0
    for i in range(len(communities) -1):
        Q += mat[communities[i]][communities[i + 1] - 1]
    if (communities[0] == param["source"] and communities[len(communities)-1]==communities[0]):
        return Q, 1.0 - (Q / M)
    else:
        return Q, -1.0


def fitnessCalculatorAB(communities, param):
    noNodes = param['noNodes']
    mat = param['mat']
    M = param['bigRoad']
    Q = 0.0
    if (communities[0] != param["source"]):
        return 0, -1.0
    for i in range(len(communities) - 1):
        Q += mat[communities[i]][communities[i + 1] - 1]
        if (communities[i + 1] == param["target"]):
            return Q, 1.0 - Q / M


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


def calculateAllSum(tree, n):
    sum = 0
    for i in range(len(tree)):
        for j in range(len(tree[i + 1])):
            sum += tree[i + 1][j]
    return sum


def printTotalAB(bestOverall,outputFile,destination):
    print('Best solution overall is: x = ' + str(bestOverall.repres) + ' f(x) = ' + str(
        bestOveral.fitness) + ' len=' + str(bestOveral.roadLenght))
    list=[]
    for e in bestOverall.repres:
        if(e != destination):
            list.append(e)
        else:
            break
    list.append(destination)
    outputFile.write(str(len(list))+"\n")
    outputFile.write(str(list)+"\n")
    outputFile.write(str(bestOverall.roadLenght)+"\n")



def printTotal(bestOverall,outputFile):
    outputFile.write(str(len(bestOverall.repres)-1)+"\n")
    outputFile.write(str(bestOverall.repres[:-1])+"\n")
    outputFile.write(str(bestOverall.roadLenght)+"\n")


def generationsTOT(problParam,gaParam):
    ga = GA(gaParam, problParam)
    ga.initialisation()
    ga.evaluation()

    bestOveral = Chromosome(problParam)
    bestOveral.fitness = -1.0

    for g in range(gaParam['noGen']):

        # logic alg
        # ga.oneGeneration()
        ga.oneGenerationElitism()
        # ga.oneGenerationSteadyState()

        bestChromo = ga.bestChromosome()

        if bestOveral.fitness < bestChromo.fitness:
            bestOveral.repres = bestChromo.repres
            bestOveral.fitness = bestChromo.fitness
            bestOveral.roadLenght = bestChromo.roadLenght

        print('Best solution in generation ' + str(g) + ' is: x = ' + str(bestChromo.repres) + ' f(x) = ' + str(
            bestChromo.fitness) + ' len=' + str(bestChromo.roadLenght))
    return bestOveral



if __name__ == '__main__':
    dataFile = open("data/date.txt", "r")
    outputFile = open("dataOut/dateOut.txt", "w")
    tree = {}
    n, source, target = getData(tree)
    sum = calculateAllSum(tree, n)
    # plotting preparation
    gaParam = {'popSize': 100, 'noGen': 1000}
    # problem parameters


    problParam = {'noNodes': n, 'function': fitnessCalculatorGEN, 'source': 1, 'target': target, 'bigRoad': sum,
                   'mat': tree, 'show': printTotal}
    bestOveral = generationsTOT(problParam,gaParam)
    print('Best solution overall is: x = ' + str(bestOveral.repres) + ' f(x) = ' + str(
        bestOveral.fitness) + ' len=' + str(bestOveral.roadLenght))
    # bestOveral.roadLenght+=tree[bestOveral.repres[n-1]][bestOveral.repres[0]-1]
    problParam["show"](bestOveral,outputFile)

    print("\n######### FROM A->B #############")

    problParam = {'noNodes': n, 'function': fitnessCalculatorAB, 'source': source, 'target': target, 'bigRoad': sum,
                  'mat': tree, 'show': printTotalAB}
    bestOveral= generationsTOT(problParam,gaParam)
    problParam["show"](bestOveral,outputFile,target)
