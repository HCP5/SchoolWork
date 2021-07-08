//
// Created by PsyHo on 7/5/2021.
//

#ifndef UNTITLED_MYGUI_H
#define UNTITLED_MYGUI_H
#include "QWidget"
#include "Service.h"
#include "myModel.h"
#include "QTableView"
#include "QHBoxLayout"
#include "QVBoxLayout"
#include "QPushButton"
#include "QLineEdit"
#include "QFormLayout"
#include "QCheckBox"
class myGUI : public QWidget{
private:
    Service & service;
    QTableView* tableView;
    myModel* model;
    QLineEdit* idTractorLine;
    QLineEdit* denumireLine;
    QLineEdit* tipLine;
    QLineEdit* nrRotiLine;
    QPushButton* addTractorButton;

    QCheckBox* box1;
    QCheckBox* box2;
    QCheckBox* box3;
public:
    myGUI(Service &service): service(service){
        setWindow();
        setController();
    };

    void setWindow();
    void setController();
};


#endif //UNTITLED_MYGUI_H
