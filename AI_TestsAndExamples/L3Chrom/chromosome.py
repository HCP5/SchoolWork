from random import randint
import random


class Chromosome:
    def __init__(self, problParam=None):
        self.__problParam = problParam
        self.__repres = random.sample(range(1,problParam["noNodes"]+1),problParam["noNodes"])
        self.__repres.append(random.randint(1,problParam["noNodes"]))
        self.__fitness = 0.0
        self.__roadLenght=0

    @property
    def repres(self):
        return self.__repres

    @property
    def fitness(self):
        return self.__fitness

    @repres.setter
    def repres(self, l=[]):
        self.__repres = l

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    @property
    def roadLenght(self):
        return self.__roadLenght

    @roadLenght.setter
    def roadLenght(self,len):
        self.__roadLenght=len

    def crossover(self, c):
        cuttingPoint = randint(0, len(self.__repres) - 1)
        newrepres=[]
        for gene in c.__repres:
            newrepres.append(self.__repres[gene-1])
        offspring = Chromosome(c.__problParam)
        offspring.repres = newrepres
        return offspring

    def mutation(self):
        pos1 = randint(0, len(self.__repres) - 1)
        pos2 = randint(0, len(self.__repres) - 1)
        while not pos2==pos1:
            pos2 = randint(0, len(self.__repres) - 1)
        aux=self.__repres[pos1]
        self.__repres[pos1]=self.__repres[pos2]
        self.__repres[pos2]=aux


    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness