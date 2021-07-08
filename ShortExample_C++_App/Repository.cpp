//
// Created by PsyHo on 7/5/2021.
//

#include "Repository.h"

Repository::Repository(const std::string &fisier) : fisier(fisier) {
    readFromFile();
}

//(id -int, denumire-string, tip -string,  numarRoti-intreg).
void Repository::readFromFile() {
    std::ifstream fin(fisier);
    int id;
    std::string denumire;
    std::string tip;
    int nrRoti;
    while (fin>>id>>denumire>>tip>>nrRoti){
        Tractor t(id,denumire,tip,nrRoti);
        addTractor(t);
    }
    fin.close();
}

void Repository::addTractor(const Tractor &tractor) {
    if(valideazaTractor(tractor)){
        listaTractor.push_back(tractor);
    }
}

// Denumire si tipnu poate fi vid, numarRotitrebuie sa fie numărpar intre 2 si 16; nu putem avea doua tractoarecu același id
bool Repository::valideazaTractor(const Tractor &tractor) {
    if(tractor.getDenumire().empty()||tractor.getTip().empty()){
        return false;
    }
    if(tractor.getNrRoti()<2||tractor.getNrRoti()>16||tractor.getNrRoti()%2==1)
        return false;
    for (const auto &item : listaTractor)
        if(item==tractor)
            return false;
    return true;

}

void Repository::writeToFile() {
    std::ofstream fout(fisier);
    for (const auto &item : listaTractor){
        fout<<item.getId()<<" "<<item.getDenumire()<<" "<<item.getTip()<<" "<<item.getNrRoti()<<"\n";
    }
}

const std::vector<Tractor> &Repository::getListaTractor() const {
    return listaTractor;
};
