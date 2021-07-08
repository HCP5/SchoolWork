# %% md

# %%

dataFile = open("iris.data")
data = dataFile.read()
irisData = []
for line in data.split("\n"):
    irisData.append(line.split(","))
for line in irisData:
    print(line)

# %% md


# %%

label = []
trainData = [x for x in irisData[:len(irisData) * 80 // 100]]
testData = [x for x in irisData[len(irisData) * 80 // 100:]]
print("For train: ", len(trainData))
print("For test: ", len(testData))
for line in trainData + testData:
    if not label.__contains__(line[4]):
        label.append(line[4])
print("Labels are: \n" + label.__str__())
threshold = 1 / len(label)

# %%

inputData = []
outputData = []
outputData0 = []
outputData1 = []
outputData2 = []

inputDataTest = []
outputDataTest = []
outputDataTest0 = []
outputDataTest1 = []
outputDataTest2 = []

for data in testData:
    x = []
    for i in range(len(data) - 1):
        x.append(float(data[i]))
    inputDataTest.append(x)
    outputDataTest.append(label.index(data[4]))
for data in trainData:
    x = []
    for i in range(len(data) - 1):
        x.append(float(data[i]))
    inputData.append(x)
    outputData.append(label.index(data[4]))
print(inputData)
print(outputData)
outputData0 = [1 if x == 0 else 0 for x in outputData]
outputData1 = [1 if x == 1 else 0 for x in outputData]
outputData2 = [1 if x == 2 else 0 for x in outputData]

"""
    0 -> 1 si in rest 0 
"""

outputDataTest0 = [1 if x == 0 else 0 for x in outputDataTest]
outputDataTest1 = [1 if x == 1 else 0 for x in outputDataTest]
outputDataTest2 = [1 if x == 2 else 0 for x in outputDataTest]

# %%
from math import exp


def fit(x, y, w, learningRate=0.001, noEpochs=1000):
    for epoch in range(noEpochs):
        for i in range(len(x)):
            computed = w[0]
            for j in range(len(w) - 1):
                computed += w[j + 1] * x[i][j]
            computed = 1 / (1 + exp(-computed))
            error = computed - y[i]
            for j in range(len(w) - 1):
                w[j + 1] = w[j + 1] - learningRate * error * x[i][j]
            w[0] = w[0] - learningRate * error * 1
    return w


beta = [[0] * len(trainData[0]) for i in range(len(label))]

"""
beta=[
[coeficienti pentru a clasa tipul 0]
[coeficienti pentru a clasa tipul 1]
[coeficienti pentru a clasa tipul 2]
]

"""

beta[0]=fit(inputData,outputData0,beta[0])
beta[1]=fit(inputData,outputData1,beta[1])
beta[2]=fit(inputData,outputData2,beta[2])
print(beta)

# %%


def predict(x, w):
    rez = w[0]
    for pair in zip(x, w[1:]):
        rez += pair[0] * pair[1]
    rez = 1 / (1 + exp(-rez))  # sigmoid
    if rez < threshold:
        rez = 0
    elif rez < threshold * 2:
        rez = 1
    else:
        rez = 2
    return rez


computed0 = []
computed1 = []
computed2 = []

for i in range(len(inputDataTest)):
    computed0.append(predict(inputDataTest[i], beta[0]))
for i in range(len(inputDataTest)):
    computed1.append(predict(inputDataTest[i], beta[1]))
for i in range(len(inputDataTest)):
    computed2.append(predict(inputDataTest[i], beta[2]))

finalCompute=[]
import random
for i in range(len(testData)):
    ok=0
    if computed0[i] > computed1[i]:
        if computed0[i] > computed2[i]:
            finalCompute.append(0)
            ok=1
    if computed1[i] > computed0[i]:
        if computed1[i] > computed2[i]:
            finalCompute.append(1)
            ok=1
    if computed2[i] > computed1[i]:
        if computed2[i] > computed0[i]:
            finalCompute.append(2)
            ok=1
    if ok==0:
        # finalCompute.append(-1)
        if computed0[i]==computed1[i]==computed2[i]:
            finalCompute.append(random.randint(0,2))
        elif computed1[i]==computed2[i]:
            finalCompute.append(random.randint(1,2))
        elif computed0[i]==computed1[i]:
            finalCompute.append(random.randint(0,1))
        else:
            x=random.randint(0,2)
            while x==1:
                x = random.randint(0, 2)
            finalCompute.append(x)

print(finalCompute)
print(outputDataTest)

x=sum([0 if finalCompute[i]==outputDataTest[i] else 1 for i in range(len(outputDataTest))])
print("Error is: " ,1-x/len(outputDataTest))

print("BATCH:")

def fitBATCH(x, y, w, learningRate=0.001, noEpochs=1000):
    for epoch in range(noEpochs):
        for i in range(len(x)):
            computed = w[0]
            for j in range(len(w) - 1):
                computed += w[j + 1] * x[i][j]
            computed = 1 / (1 + exp(-computed))
            error += computed - y[i]
        error=error/len(x)
        for j in range(len(w) - 1):
            w[j + 1] = w[j + 1] - learningRate * error * sum([xi[j] for xi in x])/len(x)
        w[0] = w[0] - learningRate * error * 1
    return w
beta = [[0] * len(trainData[0]) for i in range(len(label))]

beta[0]=fit(inputData,outputData0,beta[0])
beta[1]=fit(inputData,outputData1,beta[1])
beta[2]=fit(inputData,outputData2,beta[2])

computed0 = []
computed1 = []
computed2 = []
for i in range(len(inputDataTest)):
    computed0.append(predict(inputDataTest[i], beta[0]))
for i in range(len(inputDataTest)):
    computed1.append(predict(inputDataTest[i], beta[1]))
for i in range(len(inputDataTest)):
    computed2.append(predict(inputDataTest[i], beta[2]))

finalCompute=[]
import random
for i in range(len(testData)):
    ok=0
    if computed0[i] > computed1[i]:
        if computed0[i] > computed2[i]:
            finalCompute.append(0)
            ok=1
    if computed1[i] > computed0[i]:
        if computed1[i] > computed2[i]:
            finalCompute.append(1)
            ok=1
    if computed2[i] > computed1[i]:
        if computed2[i] > computed0[i]:
            finalCompute.append(2)
            ok=1
    if ok==0:
        # finalCompute.append(-1)
        if computed0[i]==computed1[i]==computed2[i]:
            finalCompute.append(random.randint(0,2))
        elif computed1[i]==computed2[i]:
            finalCompute.append(random.randint(1,2))
        elif computed0[i]==computed1[i]:
            finalCompute.append(random.randint(0,1))
        else:
            x=random.randint(0,2)
            while x==1:
                x = random.randint(0, 2)
            finalCompute.append(x)

print(finalCompute)
print(outputDataTest)

x=sum([0 if finalCompute[i]==outputDataTest[i] else 1 for i in range(len(outputDataTest))])
print("Error is: " ,1-x/len(outputDataTest))

print("Cross:")
def fitCross(x, y, w, learningRate=0.001, noEpochs=1000):
    noEpochs=500
    error=0
    for epoch in range(noEpochs):
        for i in range(len(x)):
            computed = w[0]
            for j in range(len(w) - 1):
                computed += w[j + 1] * x[i][j]
            computed = 1 / (1 + exp(-computed))
            error += computed - y[i]
        error=error/len(x)
        for j in range(len(w) - 1):
            w[j + 1] = w[j + 1] - learningRate * error * sum([xi[j] for xi in x])/len(x)
        w[0] = w[0] - learningRate * error * 1
    for epoch in range(noEpochs):
        for i in range(len(x)):
            computed = w[0]
            for j in range(len(w) - 1):
                computed += w[j + 1] * x[i][j]
            computed = 1 / (1 + exp(-computed))
            error = computed - y[i]
            for j in range(len(w) - 1):
                w[j + 1] = w[j + 1] - learningRate * error * x[i][j]
            w[0] = w[0] - learningRate * error * 1
    return w
beta = [[0] * len(trainData[0]) for i in range(len(label))]

beta[0]=fitCross(inputData,outputData0,beta[0])
beta[1]=fitCross(inputData,outputData1,beta[1])
beta[2]=fitCross(inputData,outputData2,beta[2])

computed0 = []
computed1 = []
computed2 = []
for i in range(len(inputDataTest)):
    computed0.append(predict(inputDataTest[i], beta[0]))
for i in range(len(inputDataTest)):
    computed1.append(predict(inputDataTest[i], beta[1]))
for i in range(len(inputDataTest)):
    computed2.append(predict(inputDataTest[i], beta[2]))

finalCompute=[]
import random
for i in range(len(testData)):
    ok=0
    if computed0[i] > computed1[i]:
        if computed0[i] > computed2[i]:
            finalCompute.append(0)
            ok=1
    if computed1[i] > computed0[i]:
        if computed1[i] > computed2[i]:
            finalCompute.append(1)
            ok=1
    if computed2[i] > computed1[i]:
        if computed2[i] > computed0[i]:
            finalCompute.append(2)
            ok=1
    if ok==0:
        # finalCompute.append(-1)
        if computed0[i]==computed1[i]==computed2[i]:
            finalCompute.append(random.randint(0,2))
        elif computed1[i]==computed2[i]:
            finalCompute.append(random.randint(1,2))
        elif computed0[i]==computed1[i]:
            finalCompute.append(random.randint(0,1))
        else:
            x=random.randint(0,2)
            while x==1:
                x = random.randint(0, 2)
            finalCompute.append(x)

print(finalCompute)
print(outputDataTest)

x=sum([0 if finalCompute[i]==outputDataTest[i] else 1 for i in range(len(outputDataTest))])
print("Error is: " ,1-x/len(outputDataTest))


# print(beta)
# error = 10
# learningRate = 0.001
# epoch = 100
# w = beta[0]
# print(w)
# while epoch > 0:
#     i = 0
#     for data1 in inputData:
#         computed = w[0]
#         for pair in zip(w[1:], data1):
#             computed += pair[0] * pair[1]
#         computed = 1 / (1 + exp(-computed))  # sigmoid
#         error = computed - outputData0[i]
#         for j in range(1, len(w)):
#             w[j] = w[j] - learningRate * error * w[j - 1]
#         w[0] = w[0] - learningRate * error
#         i += 1
#     epoch -= 1
# beta[0] = w
#
# # %%
#
# print(beta)
# error = 10
# learningRate = 0.001
# epoch = 100
# w = beta[1]
# print(w)
# while epoch > 0:
#     i = 0
#     for data1 in inputData:
#         computed = w[0]
#         for pair in zip(w[1:], data1):
#             computed += pair[0] * pair[1]
#         computed = 1 / (1 + exp(-computed))  # sigmoid
#         error = computed - outputData1[i]
#         for j in range(1, len(w)):
#             w[j] = w[j] - learningRate * error * w[j - 1]
#         w[0] = w[0] - learningRate * error
#         i += 1
#     epoch -= 1
# beta[1] = w
#
# # %%
#
# print(beta)
# error = 10
# learningRate = 0.001
# epoch = 100
# w = beta[2]
# print(w)
# while epoch > 0:
#     i = 0
#     for data1 in inputData:
#         computed = w[0]
#         for pair in zip(w[1:], data1):
#             computed += pair[0] * pair[1]
#             computed = 1 / (1 + exp(-computed))  # sigmoid
#             error = computed - outputData2[i]
#             for j in range(1, len(w)):
#                 w[j] = w[j] - learningRate * error * w[j - 1]
#             w[0] = w[0] - learningRate * error
#         i += 1
#     epoch -= 1
# beta[2] = w
#
# # %%
# print(beta)

# 5.1,3.5,1.4,0.2,Iris-setosa
# 4.9,3.0,1.4,0.2,Iris-setosa
# 4.7,3.2,1.3,0.2,Iris-setosa
# 4.6,3.1,1.5,0.2,Iris-setosa
# 5.0,3.6,1.4,0.2,Iris-setosa
# 5.4,3.9,1.7,0.4,Iris-setosa
# 4.6,3.4,1.4,0.3,Iris-setosa
# 5.0,3.4,1.5,0.2,Iris-setosa
# 4.4,2.9,1.4,0.2,Iris-setosa
# 4.9,3.1,1.5,0.1,Iris-setosa
# 5.4,3.7,1.5,0.2,Iris-setosa
# 6.3,3.3,6.0,2.5,Iris-virginica
# 5.8,2.7,5.1,1.9,Iris-virginica
# 7.1,3.0,5.9,2.1,Iris-virginica
# 6.3,2.9,5.6,1.8,Iris-virginica
# 6.5,3.0,5.8,2.2,Iris-virginica
# 7.6,3.0,6.6,2.1,Iris-virginica
# 4.9,2.5,4.5,1.7,Iris-virginica
# 7.3,2.9,6.3,1.8,Iris-virginica
# 6.7,2.5,5.8,1.8,Iris-virginica
# 7.2,3.6,6.1,2.5,Iris-virginica
# 6.5,3.2,5.1,2.0,Iris-virginica
# 6.4,2.7,5.3,1.9,Iris-virginica
# 6.8,3.0,5.5,2.1,Iris-virginica
# 5.7,2.5,5.0,2.0,Iris-virginica
# 5.8,2.8,5.1,2.4,Iris-virginica
# 6.4,3.2,5.3,2.3,Iris-virginica
# 6.5,3.0,5.5,1.8,Iris-virginica
# 7.7,3.8,6.7,2.2,Iris-virginica
# 7.7,2.6,6.9,2.3,Iris-virginica
# 6.0,2.2,5.0,1.5,Iris-virginica
# 6.9,3.2,5.7,2.3,Iris-virginica
# 5.6,2.8,4.9,2.0,Iris-virginica
# 7.7,2.8,6.7,2.0,Iris-virginica
# 6.3,2.7,4.9,1.8,Iris-virginica
# 6.7,3.3,5.7,2.1,Iris-virginica
# 7.2,3.2,6.0,1.8,Iris-virginica
# 7.0,3.2,4.7,1.4,Iris-versicolor
# 6.4,3.2,4.5,1.5,Iris-versicolor
# 6.9,3.1,4.9,1.5,Iris-versicolor
# 5.5,2.3,4.0,1.3,Iris-versicolor
# 6.5,2.8,4.6,1.5,Iris-versicolor
# 5.7,2.8,4.5,1.3,Iris-versicolor
# 6.3,3.3,4.7,1.6,Iris-versicolor
# 4.9,2.4,3.3,1.0,Iris-versicolor
# 6.6,2.9,4.6,1.3,Iris-versicolor
# 5.2,2.7,3.9,1.4,Iris-versicolor
# 5.0,2.0,3.5,1.0,Iris-versicolor
# 5.9,3.0,4.2,1.5,Iris-versicolor
# 6.0,2.2,4.0,1.0,Iris-versicolor
# 6.1,2.9,4.7,1.4,Iris-versicolor
# 5.6,2.9,3.6,1.3,Iris-versicolor
# 6.7,3.1,4.4,1.4,Iris-versicolor
# 5.6,3.0,4.5,1.5,Iris-versicolor
# 5.8,2.7,4.1,1.0,Iris-versicolor
# 6.2,2.2,4.5,1.5,Iris-versicolor
# 5.6,2.5,3.9,1.1,Iris-versicolor
# 5.9,3.2,4.8,1.8,Iris-versicolor
# 6.1,2.8,4.0,1.3,Iris-versicolor
# 4.8,3.4,1.6,0.2,Iris-setosa
# 4.8,3.0,1.4,0.1,Iris-setosa
# 4.3,3.0,1.1,0.1,Iris-setosa
# 5.8,4.0,1.2,0.2,Iris-setosa
# 4.6,3.6,1.0,0.2,Iris-setosa
# 5.1,3.3,1.7,0.5,Iris-setosa
# 4.8,3.4,1.9,0.2,Iris-setosa
# 5.0,3.0,1.6,0.2,Iris-setosa
# 5.0,3.4,1.6,0.4,Iris-setosa
# 5.2,3.5,1.5,0.2,Iris-setosa
# 5.2,3.4,1.4,0.2,Iris-setosa
# 4.7,3.2,1.6,0.2,Iris-setosa
# 4.8,3.1,1.6,0.2,Iris-setosa
# 5.4,3.4,1.5,0.4,Iris-setosa
# 5.2,4.1,1.5,0.1,Iris-setosa
# 5.5,4.2,1.4,0.2,Iris-setosa
# 4.9,3.1,1.5,0.1,Iris-setosa
# 5.0,3.2,1.2,0.2,Iris-setosa
# 5.5,3.5,1.3,0.2,Iris-setosa
# 4.9,3.1,1.5,0.1,Iris-setosa
# 4.4,3.0,1.3,0.2,Iris-setosa
# 5.1,3.4,1.5,0.2,Iris-setosa
# 5.0,3.5,1.3,0.3,Iris-setosa
# 4.5,2.3,1.3,0.3,Iris-setosa
# 4.4,3.2,1.3,0.2,Iris-setosa
# 5.0,3.5,1.6,0.6,Iris-setosa
# 5.1,3.8,1.9,0.4,Iris-setosa
# 4.8,3.0,1.4,0.3,Iris-setosa
# 5.1,3.8,1.6,0.2,Iris-setosa
# 4.6,3.2,1.4,0.2,Iris-setosa
# 5.3,3.7,1.5,0.2,Iris-setosa
# 5.0,3.3,1.4,0.2,Iris-setosa
# 6.3,2.5,4.9,1.5,Iris-versicolor
# 6.1,2.8,4.7,1.2,Iris-versicolor
# 6.4,2.9,4.3,1.3,Iris-versicolor
# 6.6,3.0,4.4,1.4,Iris-versicolor
# 6.4,2.8,5.6,2.2,Iris-virginica
# 6.3,2.8,5.1,1.5,Iris-virginica
# 6.1,2.6,5.6,1.4,Iris-virginica
# 7.7,3.0,6.1,2.3,Iris-virginica
# 6.3,3.4,5.6,2.4,Iris-virginica
# 6.4,3.1,5.5,1.8,Iris-virginica
# 6.0,3.0,4.8,1.8,Iris-virginica
# 6.9,3.1,5.4,2.1,Iris-virginica
# 6.7,3.1,5.6,2.4,Iris-virginica
# 6.9,3.1,5.1,2.3,Iris-virginica
# 5.8,2.7,5.1,1.9,Iris-virginica
# 6.8,3.2,5.9,2.3,Iris-virginica
# 6.7,3.3,5.7,2.5,Iris-virginica
# 6.8,2.8,4.8,1.4,Iris-versicolor
# 6.7,3.0,5.0,1.7,Iris-versicolor
# 6.0,2.9,4.5,1.5,Iris-versicolor
# 5.7,2.6,3.5,1.0,Iris-versicolor
# 5.5,2.4,3.8,1.1,Iris-versicolor
# 5.5,2.4,3.7,1.0,Iris-versicolor
# 5.8,2.7,3.9,1.2,Iris-versicolor
# 6.0,2.7,5.1,1.6,Iris-versicolor
# 5.4,3.0,4.5,1.5,Iris-versicolor
# 6.0,3.4,4.5,1.6,Iris-versicolor
# 6.7,3.1,4.7,1.5,Iris-versicolor
# 6.3,2.3,4.4,1.3,Iris-versicolor
# 5.6,3.0,4.1,1.3,Iris-versicolor
# 5.5,2.5,4.0,1.3,Iris-versicolor
# 5.5,2.6,4.4,1.2,Iris-versicolor
# 6.1,3.0,4.6,1.4,Iris-versicolor
# 5.8,2.6,4.0,1.2,Iris-versicolor
# 5.0,2.3,3.3,1.0,Iris-versicolor
# 5.6,2.7,4.2,1.3,Iris-versicolor
# 5.7,3.0,4.2,1.2,Iris-versicolor
# 5.7,2.9,4.2,1.3,Iris-versicolor
# 6.2,2.9,4.3,1.3,Iris-versicolor
# 5.1,2.5,3.0,1.1,Iris-versicolor
# 5.7,2.8,4.1,1.3,Iris-versicolor
# 6.2,2.8,4.8,1.8,Iris-virginica
# 6.1,3.0,4.9,1.8,Iris-virginica
# 6.4,2.8,5.6,2.1,Iris-virginica
# 7.2,3.0,5.8,1.6,Iris-virginica
# 7.4,2.8,6.1,1.9,Iris-virginica
# 7.9,3.8,6.4,2.0,Iris-virginica
# 6.7,3.0,5.2,2.3,Iris-virginica
# 6.3,2.5,5.0,1.9,Iris-virginica
# 6.5,3.0,5.2,2.0,Iris-virginica
# 6.2,3.4,5.4,2.3,Iris-virginica
# 5.9,3.0,5.1,1.8,Iris-virginica
# 5.7,4.4,1.5,0.4,Iris-setosa
# 5.4,3.9,1.3,0.4,Iris-setosa
# 5.1,3.5,1.4,0.3,Iris-setosa
# 5.7,3.8,1.7,0.3,Iris-setosa
# 5.1,3.8,1.5,0.3,Iris-setosa
# 5.4,3.4,1.7,0.2,Iris-setosa
# 5.1,3.7,1.5,0.4,Iris-setosa