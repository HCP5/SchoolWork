//
// Created by PsyHo on 7/5/2021.
//

#ifndef UNTITLED_MYMODEL_H
#define UNTITLED_MYMODEL_H

#include <QBrush>
#include "vector"
#include "Tractor.h"
#include "QAbstractTableModel"
class myModel : public QAbstractTableModel {
private:
    std::vector<Tractor> listaTractoare;
    std::vector<int> nrOfTrucks;
    int checkbox=0;
public:
    myModel(const std::vector<Tractor> &listaTractor,const std::vector<int> &nrTractoare) : listaTractoare(listaTractor) , nrOfTrucks(nrTractoare) {};

    int rowCount(const QModelIndex &parent = QModelIndex()) const override {
        return listaTractoare.size();
    }

    int columnCount(const QModelIndex &parent = QModelIndex()) const override {
        return 5;
    }

    QVariant data(const QModelIndex& index,int role=Qt::DisplayRole)const override{
        if(role==Qt::DisplayRole){
            Tractor t=listaTractoare[index.row()];
            int nrTracotr=nrOfTrucks[index.row()];
            if(index.column()==0){
                return QString::fromStdString(std::to_string(t.getId()));
            }
            if(index.column()==1){
                return QString::fromStdString(t.getDenumire());
            }
            if(index.column()==2){
                return QString::fromStdString(t.getTip());
            }
            if(index.column()==3){
                return QString::fromStdString(std::to_string(t.getNrRoti()));
            }
            if(index.column()==4){
                return QString::fromStdString(std::to_string(nrTracotr));
            }
        }
        if(role==Qt::BackgroundRole){
            Tractor t=listaTractoare[index.row()];
            if(checkbox==1){
                if(t.getTip()=="industrial")
                {
                    QBrush bg(Qt::red);
                    return bg;
                }
            }
            else
                if(checkbox==2){
                    if(t.getTip()=="servici")
                    {
                        QBrush bg(Qt::red);
                        return bg;
                    }
                }
                else{
                    if(t.getTip()=="transport")
                    {
                        QBrush bg(Qt::red);
                        return bg;
                    }
                }

        }
        return QVariant();
    }

    void setListe(const std::vector<int> &nrTractoare, const std::vector<Tractor>& listaTractoare){
        this->listaTractoare=listaTractoare;
        this->nrOfTrucks=nrTractoare;
        auto TLeft= createIndex(0,0);
        auto BRight=createIndex(rowCount(), columnCount());
        emit dataChanged(TLeft,BRight);
    }

    void setCheckBox(int x){
        this->checkbox=x;
        auto TLeft= createIndex(0,0);
        auto BRight=createIndex(rowCount(), columnCount());
        emit dataChanged(TLeft,BRight);
    }
};


#endif //UNTITLED_MYMODEL_H
