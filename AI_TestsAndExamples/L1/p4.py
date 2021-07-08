import collections

s = input("Propozitia dvs.:")

s = s.split(" ")

dictionar = collections.Counter()

for cuv in s:
    dictionar[cuv] += 1

for el in dictionar:
    if dictionar[el] == 1:
        print(el)
