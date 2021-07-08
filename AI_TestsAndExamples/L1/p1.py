s = input("Propozitia dvs: ")
s = s.split(" ")

for word in s:
    if s[0] < word:
        s[0] = word

print(s[0])
