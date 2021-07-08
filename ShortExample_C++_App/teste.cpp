//
// Created by PsyHo on 7/5/2021.
//

#include "teste.h"
#include "iostream"

void testDomain(){
    Tractor tractor(1,"tractor1", "industrial", 6);
    assert(tractor.getNrRoti()==6);
    assert(tractor.getId()==1);
    assert(tractor.getDenumire()=="tractor1");
    assert(tractor.getTip()=="industrial");
    tractor.setId(3);
    tractor.setDenumire("test");
    tractor.setNrRoti(10);
    tractor.setTip("test");
    assert(tractor.getNrRoti()==10);
    assert(tractor.getId()==3);
    assert(tractor.getDenumire()=="test");
    assert(tractor.getTip()=="test");
    Tractor tractor1(3,"tractor1", "industrial", 6);
    assert(tractor==tractor1);
}

void testService(){
    Repository r("test.txt");
    Service s(r);
    std::vector<Tractor> lista=s.getListaSortata();
    for(int i=0;i<lista.size()-1;++i){
        assert(lista[i].getDenumire()<lista[i+1].getDenumire());
    }
    std::vector<int> listaNr=s.getNrTrucs();
    for (const auto &item : listaNr)
        assert(item==3);
    s.addTractor(11,"","industrial",6);
    s.addTractor(1,"tractor","industrial",6);
    s.addTractor(11,"tractor","industrial",200);
    s.addTractor(11,"tractor","industrial",6);
    assert(s.getListaSortata().size()==10);
}

void testRepo(){
    Repository r("test.txt");
    assert(r.getListaTractor().size()==9);
    r.writeToFile();
    Tractor t(10,"tractor1", "industrial", 6);
    r.addTractor(t);
    assert(r.getListaTractor().size()==10);
    r.addTractor(t);
    assert(r.getListaTractor().size()==10);
    t.setDenumire("");
    r.addTractor(t);
    assert(r.getListaTractor().size()==10);
    t.setDenumire("denumire");
    t.setNrRoti(20);
    r.addTractor(t);
    assert(r.getListaTractor().size()==10);
}

void testAll(){
    testDomain();
    testRepo();
    testService();
}