//
// Created by PsyHo on 7/5/2021.
//

#ifndef UNTITLED_REPOSITORY_H
#define UNTITLED_REPOSITORY_H
#include "vector"
#include "Tractor.h"
#include "fstream"
class Repository {
private:
    std::vector<Tractor> listaTractor;
    std::string fisier;

public:
    //constructor implicit
    Repository(const std::string &fisier);

    //functie ce incarca in memorie citind din fisier
    //i:-
    //o:-
    void readFromFile();

    //functie ce adauga un tractor in lista de tractoare;
    //i: tractor-Tractor
    //o:-
    void addTractor(const Tractor& tractor);

    //functie ce salveaza intreaga lista in fisier
    //i:-
    //o:-
    void writeToFile();

    const std::vector<Tractor> &getListaTractor() const;


    //functie ce verifica valabilitatea unui tractor
    //i:tractor-Tractor
    //o:true daca tractor este valid, false altfel
    bool valideazaTractor(const Tractor& tractor);
};


#endif //UNTITLED_REPOSITORY_H
