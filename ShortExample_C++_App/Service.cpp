//
// Created by PsyHo on 7/5/2021.
//

#include "Service.h"

Service::Service(Repository &repository) : repository(repository) {}

std::vector<Tractor> Service::getListaSortata() {
    std::vector<Tractor> rez=repository.getListaTractor();
    for (int i = 0; i < rez.size() - 1; ++i) {
        for (int j = i+1; j < rez.size() ; ++j) {
            if(rez[i].getDenumire()>rez[j].getDenumire())
            {
                Tractor aux=rez[i];
                rez[i]=rez[j];
                rez[j]=aux;
            }
        }
    }
    return rez;
}

const std::vector<int> Service::getNrTrucs() {
    std::vector<int> rez;
    std::vector<Tractor> listaTractoare=getListaSortata();
    int listaAparitii[listaTractoare.size()];
    for(int i=0;i<listaTractoare.size();++i)
    {
        rez.push_back(0);
        listaAparitii[i]=0;
    }
    int lungLista=0;
    std::string listaElemente[listaTractoare.size()];

    for (const auto &item : listaTractoare){
        bool gasit= false;
        for(int i=0;i<lungLista;i++)
            if(item.getTip()==listaElemente[i]) {
                listaAparitii[i]++;
                gasit = true;
            }
        if(!gasit){
            listaElemente[lungLista++]= item.getTip();
            listaAparitii[lungLista-1]=1;
        }
    }
    for(int i=0;i<listaTractoare.size();i++){
        for(int j=0;j<lungLista;j++)
            if(listaTractoare[i].getTip()==listaElemente[j])
                rez[i]=listaAparitii[j];
    }
    return rez;
}

std::string Service::addTractor(int id, std::string denumire, std::string tip, int nrRoti) {
    if(denumire.empty()||tip.empty()){
        return std::string("Denumirea si tipul nu pot fi goale");
    }
    if(nrRoti<2||nrRoti>16||nrRoti%2==1)
        return std::string("nrRoti trebuie sa fie [2,16] si sa fie numar par");
    for (const auto &item : repository.getListaTractor())
        if(item.getId()==id)
            return std::string("id invalid!");
    Tractor t(id,denumire,tip,nrRoti);
    repository.addTractor(t);
    repository.writeToFile();
    return std::string("succes");
}
