# n = int(input("N:"))
# m = int(input("M:"))
# matrix = [[0 for _ in range(m)] for _ in range(n)]
# for i in range(n):
#     for j in range(m):
#         matrix[i][j]=int(input())
matrix = \
    [[0, 2, 5, 4, 1],
     [4, 8, 2, 3, 7],
     [6, 3, 4, 6, 2],
     [7, 3, 1, 8, 3],
     [1, 5, 7, 9, 4]]

a = (int(input()), int(input()))
b = (int(input()), int(input()))

sum = 0

for i in range(a[0], b[0] + 1):
    for j in range(a[1], b[1] + 1):
        sum += matrix[i][j]

print(sum)
