sum = 0
n = int(input("Cate nr: "))
for i in range(n):
    sum += int(input())

print(int(sum - ((n * (n - 1)) / 2)))
