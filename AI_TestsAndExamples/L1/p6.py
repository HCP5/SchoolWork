import collections

n = int(input("Cate nr:"))

dictionar = collections.Counter()

for i in range(n):
    dictionar[int(input())] += 1
if dictionar.most_common()[0][1] > n / 2:
    print(dictionar.most_common()[0][0])
