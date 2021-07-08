//
// Created by PsyHo on 7/5/2021.
//

#ifndef UNTITLED_TRACTOR_H
#define UNTITLED_TRACTOR_H
#include "string"
//(id -int, denumire-string, tip -string,  numarRoti-intreg)
class Tractor {
private:
    int id;
    std::string denumire;
    std::string tip;
    int nrRoti;
public:
    //constructor implicit
    Tractor(int id, const std::string &denumire, const std::string &tip, int nrRoti);

    // functie ce returneaza id-ul unui tractor
    //i: -
    //o: id-int
    int getId() const;

    // functie ce seteaza id-ul unui tractor
    //i: id-int
    //o: -
    void setId(int id);

    // functie ce returneaza denumirea unui tractor
    //i: -
    //o: denumire-std::string
    const std::string &getDenumire() const;

    // functie ce seteaza denumirea unui tractor
    //i: denumire-std::string
    //o: -
    void setDenumire(const std::string &denumire);

    // functie ce returneaza tipul unui tractor
    //i: -
    //o: tip-std::string
    const std::string &getTip() const;

    // functie ce seteaza tipul unui tractor
    //i: tip-std::string
    //o: -
    void setTip(const std::string &tip);

    // functie ce returneaza nr. roti unui tractor
    //i: -
    //o: nrRoti-int
    int getNrRoti() const;

    // functie ce seteaza nr. roti unui tractor
    //i: nrRoti-int
    //o: -
    void setNrRoti(int nrRoti);

    bool operator==(const Tractor &rhs) const;
};


#endif //UNTITLED_TRACTOR_H
