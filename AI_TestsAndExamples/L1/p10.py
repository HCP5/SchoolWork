n = int(input("N: "))  # linii
m = int(input("M: "))  # coloane

matrix = \
    [[0, 0, 0, 1, 1],
     [0, 1, 1, 1, 1],
     [0, 0, 1, 1, 1]]

indexMax=-1
sumMax=0
for i in range(n):
    sum=0
    for j in range(m):
        sum+=matrix[i][j]
    if sum>sumMax :
        sumMax=sum
        indexMax=i
print("Linia cu numarul: "+str(indexMax)+" are cele mai multe elemente 1")
