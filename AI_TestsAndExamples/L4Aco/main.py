import random


def changeGraph(costs, pheros):
    lenPlus=len(costs)+1
    costs[lenPlus]=[]
    costs[lenPlus].append([random.randint(1,50)for i in range(lenPlus-1)])
    pheros[lenPlus]=[]
    pheros[lenPlus].append([0.5 for i in range(lenPlus)])
    for i in range(lenPlus-1):
        costs[i+1].append(random.randint(1,50))
        pheros[i+1].append(0.5)
    costs[lenPlus]=costs[lenPlus][0]
    pheros[lenPlus]=pheros[lenPlus][0]
    costs[lenPlus].append(0)
    return costs,pheros


class ACO:
    def __init__(self,params):
        self.__params=params
        self.__alpha = params["alpha"]
        self.__beta=params["beta"]
        self.__intensity=params["intensity"]
        self.__noAnts=params["noAnts"]
        self.__graphCosts=params["costs"]
        self.__graphPheros=params["pheros"]
        self.__noRepeats=params["repeatTest"]
        self.__evap=params["evap"]
        self.__changeRoads=10
        self.__foodTimeBaby=False

    def solve(self):
        """
        Function that throws for a fixed number of iterations every ant
            in a random position in graph

        :param graph : graph of costs:
        :return: best road based on pheromones
        """

        for time in range(self.__noRepeats):
            myAntsList=[ANT(self.__params) for i in range(self.__noAnts)]
            for ant in myAntsList:
                if (self.__foodTimeBaby.__eq__(True)):
                    self.__graphCosts,self.__graphPheros=changeGraph(self.__graphCosts,self.__graphPheros)
                    self.__params["costs"]=self.__graphCosts
                    self.__params["pheros"]=self.__graphPheros
                    ant.updatePhero(self.__params)
                    self.__foodTimeBaby=False
                for i in range(len(self.__graphCosts)-1):

                    ant._goNext()
            self._updateAllPheros(myAntsList)
            if time==self.__changeRoads:
                self.__foodTimeBaby=True


        return self.__graphPheros

    def _updateAllPheros(self, myAntsList):
        generalPhero={}
        for ant in myAntsList:
            for edge in ant.getEdges():
                generalPhero[edge]=0
        for ant in myAntsList:
            for edge in ant.getEdges():
                generalPhero[edge]+=ant.getPhero()
        for node in self.__graphPheros:
            for toNode in range(len(self.__graphPheros)):
                self.__graphPheros[node][toNode]*=(1-self.__evap)

        for edge in generalPhero:
            self.__graphPheros[edge[0]][edge[1]-1]+=generalPhero[edge]


class ANT:
    def __init__(self,params):
        self.__road=[]
        self.__phero=0
        #initialize the starting point
        self.__road.append(random.randint(1,len(params["costs"])))
        self.__acoParams=params
        self.__edgeVisited=[]

    def getPhero(self):
        return self.__phero

    def getEdges(self):
        return self.__edgeVisited

    def _goNext(self):
        graph=self.__acoParams["costs"]
        pheroGraph=self.__acoParams["pheros"]
        alhpa=self.__acoParams["alpha"]
        beta=self.__acoParams["beta"]
        probabilities={}
        fromNode=self.__road[-1:][0]
        sumOfPOS=0
        for i in range(len(graph)):
            toNode=i+1
            if not self.__road.__contains__(toNode):
                sumOfPOS+=(pow(pheroGraph[fromNode][toNode-1],alhpa)*pow(1/graph[fromNode][toNode-1],beta))

        for i in range(len(graph)):
            toNode=i+1
            if not self.__road.__contains__(toNode):
                probabilities[toNode]=(pow(pheroGraph[fromNode][toNode-1],alhpa)*pow(1/graph[fromNode][toNode-1],beta))/sumOfPOS
        selected=0
        while selected==0:
            roulete = random.uniform(0, 1)
            for toNode in probabilities:
                if len(probabilities) == 1:
                    selected=toNode
                    break
                if probabilities[toNode]<roulete:
                    selected=toNode
                    break
        self.__road.append(selected)

    def updatePhero(self,params):
        totalLength=self.getLenght()
        self.__acoParams=params



    def getLenght(self):
        graph=self.__acoParams["costs"]
        length=0

        for nod in range(len(self.__road)-1):
            length+=graph[self.__road[nod]][self.__road[nod+1]-1]
            self.__edgeVisited.append((self.__road[nod],self.__road[nod+1]))
        return length

    def workHarder(self):
        self.__acoParams["intensity"]+=0.5

def readData(data):
    dataFile=open(data,"r")
    tree={}
    phero={}
    n = int(dataFile.readline())
    for i in range(n):
        phero[i+1]=[]
    for i in range(n):
        [phero[i+1].append(0.5) for i in range(n)]
    for i in range(n):
        b = []
        line = dataFile.readline()
        line = line.split(",")
        for e in line:
            b.append(int(e))
        tree.__setitem__(i + 1, b)
    return tree,phero


def getBest(pheroTree):
    pass


if __name__ == '__main__':
    tree, pheroTree = readData("data/date.txt")

    props = {'alpha': 1.0, 'beta': 10.0, 'intensity': 1, 'noAnts': 100, 'costs': tree, 'pheros': pheroTree,
             'repeatTest': 20, 'evap': 0.5}
    aco = ACO(props)
    pheroTree = aco.solve()
    for edge in pheroTree:
        print( pheroTree[edge])

