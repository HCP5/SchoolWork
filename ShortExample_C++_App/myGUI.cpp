//
// Created by PsyHo on 7/5/2021.
//

#include "myGUI.h"
#include "QMessageBox"
void myGUI::setController() {
    QObject::connect(addTractorButton,&QPushButton::clicked,[=](){
        std::string text=service.addTractor(idTractorLine->text().toInt(),denumireLine->text().toStdString(),tipLine->text().toStdString(),nrRotiLine->text().toInt());
        if(text!="succes")
        {
            const QString message=QString::fromStdString(text);
            QMessageBox box;
            box.setText(message);
            box.exec();
        } else{
            delete model;
            model=new myModel(service.getListaSortata(),service.getNrTrucs());
            tableView->setModel(model);
        }
    });
    QObject::connect(box1,&QCheckBox::stateChanged,[=](){
        if(box1->checkState()==Qt::Checked){
            model->setCheckBox(1);
            box2->setCheckState(Qt::Unchecked);
            box3->setCheckState(Qt::Unchecked);

        }
    });
    QObject::connect(box2,&QCheckBox::stateChanged,[=](){
        if(box2->checkState()==Qt::Checked){
            model->setCheckBox(2);
            box1->setCheckState(Qt::Unchecked);
            box3->setCheckState(Qt::Unchecked);
        }
    });
    QObject::connect(box3,&QCheckBox::stateChanged,[=](){
        if(box3->checkState()==Qt::Checked){
            model->setCheckBox(3);
            box2->setCheckState(Qt::Unchecked);
            box1->setCheckState(Qt::Unchecked);
        }
    });
}

void myGUI::setWindow() {
    const auto mainLy=new QHBoxLayout;
    const auto firstRow=new QVBoxLayout;
    const auto secondRow=new QVBoxLayout;

    tableView=new QTableView;
    model=new myModel(service.getListaSortata(),service.getNrTrucs());
    tableView->setModel(model);

    auto form=new QFormLayout;
    idTractorLine=new QLineEdit;
    denumireLine=new QLineEdit;
    tipLine=new QLineEdit;
    nrRotiLine=new QLineEdit;

    box1=new QCheckBox("industrial");
    box2=new QCheckBox("servici");
    box3=new QCheckBox("transport");

    form->addRow("ID: ",idTractorLine);
    form->addRow("Denumire: ",denumireLine);
    form->addRow("Tip: ",tipLine);
    form->addRow("nrRoti: ",nrRotiLine);
    addTractorButton=new QPushButton("Adauga");
    secondRow->addLayout(form);
    secondRow->addWidget(addTractorButton);
    secondRow->addWidget(box1);
    secondRow->addWidget(box2);
    secondRow->addWidget(box3);



    firstRow->addWidget(tableView);
    mainLy->addLayout(firstRow);
    mainLy->addLayout(secondRow);
    setLayout(mainLy);
}
