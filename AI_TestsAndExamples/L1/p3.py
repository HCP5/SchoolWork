a = [1, 0, 2, 0, 3]
b = [1, 2, 0, 3, 1]

suma = 0
for i in range(len(a)):
    suma = suma + (a[i] * b[i])

print(suma)
