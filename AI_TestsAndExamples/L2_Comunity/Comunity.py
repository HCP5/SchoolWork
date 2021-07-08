from random import randint

class Comunity:
    def __init__(self, problParam=None):
        self.__problParam = problParam

        self.__repres=[randint(problParam["min"], problParam["max"]) for _ in range(problParam["noNodes"])]
        # for _ in range(problParam["noNodes"]):
        #     gene = [randint(problParam["min"], problParam["max"]) for _ in range(problParam["noNodes"])]
        #     self.__repres.append(gene)
        self.__fitness = 0.0

    @property
    def repres(self):
        realRepres = []
        for gene in self.__repres:
            realRepres.append(gene)
        # for i in range(len(self.__repres)):
        #     realRepres[self.__repres[i]].append(i)
        return realRepres

    @property
    def fitness(self):
        return self.__fitness

    @repres.setter
    def repres(self, l=[]):
        self.__repres = l

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def crossover(self, c):
        noNodes = self.__problParam["noNodes"]
        cuttingPoint = randint(0, noNodes - 1)
        geneM=self.__repres
        geneF=c.__repres
        offspring=Comunity(self.__problParam)
        newrepres=[geneM[i] if i < cuttingPoint else geneF[i] for i in range(noNodes)]
        offspring.repres=newrepres
        return offspring

    def mutation(self):
        gene = randint(0, len(self.__repres) - 1)
        self.__repres[gene]=randint(0,len(self.__repres)-1)

    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness