def initCoef(nrOfCoef):
    return [0 for _ in range(nrOfCoef + 1)]


def computeThis(w, happy, capital, freedom):
    error = 0
    for i in range(len(happy)):
        computed = w[0] + w[1] * capital[i] + w[2] * freedom[i]
        error += computed - happy[i]
    return error/len(happy)


def cumputeUnivar(w,happy,capital):
    error=0
    for i in range(len(happy)):
        computed=w[0]+w[1]*capital[i]
        error+=computed-happy[i]
    return error/len(happy)
