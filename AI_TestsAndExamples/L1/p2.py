import math

xa = int(input("Dati x primului punct: "))
ya = int(input("Dati y primului punct: "))
xb = int(input("Dati x celui de-al doilea punct: "))
yb = int(input("Dati x celui de-al doilea punct: "))

xPatrat = (xb - xa) * (xb - xa)
yPatrat = (yb - ya) * (yb - ya)

distEucl = math.sqrt(xPatrat + yPatrat)

print("Distanta dintre cele doua puncte este: ", distEucl)
