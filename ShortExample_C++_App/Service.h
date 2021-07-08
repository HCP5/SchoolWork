//
// Created by PsyHo on 7/5/2021.
//

#ifndef UNTITLED_SERVICE_H
#define UNTITLED_SERVICE_H
#include "Repository.h"

class Service {
private:
    Repository& repository;
public:
    //constructor implicit
    Service(Repository &repository);

    //functie ce returneaza lista sortata
    //i:-
    //o:std::vector<Tractor>
    std::vector<Tractor> getListaSortata();

    //functie ce returneaza lista de nr de unitati;
    //i:-
    //o:std::vector<int>
    const std::vector<int> getNrTrucs();

    std::string addTractor(int id, std::string denumire, std::string tip, int nrRoti);
};


#endif //UNTITLED_SERVICE_H
