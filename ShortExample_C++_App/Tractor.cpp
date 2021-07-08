//
// Created by PsyHo on 7/5/2021.
//

#include "Tractor.h"

Tractor::Tractor(int id, const std::string &denumire, const std::string &tip, int nrRoti) : id(id), denumire(denumire),
                                                                                            tip(tip), nrRoti(nrRoti) {}

int Tractor::getId() const {
    return id;
}

void Tractor::setId(int id) {
    Tractor::id = id;
}

const std::string &Tractor::getDenumire() const {
    return denumire;
}

void Tractor::setDenumire(const std::string &denumire) {
    Tractor::denumire = denumire;
}

const std::string &Tractor::getTip() const {
    return tip;
}

void Tractor::setTip(const std::string &tip) {
    Tractor::tip = tip;
}

int Tractor::getNrRoti() const {
    return nrRoti;
}

void Tractor::setNrRoti(int nrRoti) {
    Tractor::nrRoti = nrRoti;
}

bool Tractor::operator==(const Tractor &rhs) const {
    return id == rhs.id;
};

