n = int(input("N: "))
kDat = int(input("K: "))
x = []

for i in range(n):
    x.append(int(input()))

x.sort()

print(x)
print(x[n - kDat])
