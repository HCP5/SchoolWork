#pragma once
#include <QtWidgets/QLabel>
#include <QtWidgets/qwidget.h>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QListWidgetItem>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QMessageBox>

#include <QtWidgets/QTableWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QLineEdit>
#include <qdebug.h>

#include "COSGui.h"
#include "ServiceLocatar.h"
#include "validatorLocatar.h"

class ServiceLocatar;

class myGUI : public QWidget
{
private:
	ServiceLocatar& service_;

	//Buttons
	QPushButton* ListeazaButton; 
	
	QPushButton* SorteazaButton;
	QPushButton* searchButton;
	QPushButton* adaugaButton;
	QPushButton* stergeButton;
	QPushButton* modificaButton;
	QPushButton* Undo;
	QPushButton* COS;
//Lista
	QListWidget* list;
//Tabela
	QTableWidget* table;
//LineEdits
	QLineEdit* apartamentInput;
	QLineEdit* suprafataInput;
	QLineEdit* tipInput;
	QLineEdit* numeInput;
	QCheckBox* check_box1;
	QCheckBox* check_box2;
	QCheckBox* check_box3;

	COSGui *gui;

public:
	

	myGUI(ServiceLocatar& service): service_{ service }
	{
		setWindow();
		setController();
	}

	void setWindow()
	{
		const auto mainLy = new QHBoxLayout;
		const auto td1 = new QVBoxLayout;
		list = new QListWidget;
		
		td1->addWidget(list);
		const auto tr1 = new QHBoxLayout;
		ListeazaButton = new QPushButton("Listeaza");
		SorteazaButton = new QPushButton("Sorteaza");
		Undo = new QPushButton("Undo");
		adaugaButton = new QPushButton("Adauga");
		stergeButton = new QPushButton("Sterge");
		modificaButton = new QPushButton("Update");
		searchButton = new QPushButton("Search");
		adaugaButton->setStyleSheet("background-color: rgb(0, 191,255)");
		stergeButton->setStyleSheet("background-color: rgb(207, 137, 142)");
		searchButton->setStyleSheet("background-color: rgb(104, 137, 142)");
		
		tr1->addWidget(ListeazaButton);
		tr1->addWidget(SorteazaButton);
		tr1->addWidget(searchButton);
		tr1->addWidget(Undo);
		td1->addLayout(tr1);

		
		
		const auto td2 = new QVBoxLayout;
		const auto tr2 =new QHBoxLayout;
		const auto tr3 =new QHBoxLayout;
		tr2->addWidget(adaugaButton);
		tr2->addWidget(stergeButton);
		tr2->addWidget(modificaButton);
		COS =new QPushButton("Cos");
		tr2->addWidget(COS);
		table = new QTableWidget{1,4};
		table->setSelectionBehavior(QAbstractItemView::SelectRows);
		table->setSelectionMode(QAbstractItemView::SingleSelection);
		td2->addWidget(table);
		auto form = new QFormLayout;
		apartamentInput = new QLineEdit;
		numeInput = new QLineEdit;
		suprafataInput= new QLineEdit;
		tipInput = new QLineEdit;
		form->addRow("Apartament", apartamentInput);
		form->addRow("Nume proprietar", numeInput);
		form->addRow("Suprafata", suprafataInput);
		form->addRow("Tip apartament",tipInput);
		td2->addLayout(form);
		td2->addLayout(tr2);

		check_box1 = new QCheckBox("PentHouse");
		check_box2 = new QCheckBox("Min 200 mp");
		check_box3 = new QCheckBox("garsoniera");
		tr3->addWidget(check_box1);
		tr3->addWidget(check_box2);
		tr3->addWidget(check_box3);
		td1->addLayout(tr3);
		
		mainLy->addLayout(td1);
		mainLy->addLayout(td2);
		setLayout(mainLy);
	}
	
	void list_this(std::vector<Locatar> vector)
	{
		list->clear();
		for (const auto & locatar : vector)
		{
			list->addItem(new QListWidgetItem(QString::fromStdString(locatar.to_string())));
		}
	}

	void setController()
	{
		QObject::connect(COS, &QPushButton::clicked, [=]()
			{
				gui=new COSGui{service_};
				gui->show();
			});
		
		QObject::connect(ListeazaButton, &QPushButton::clicked, [=]()
			{
				list->clear();
				std::vector<Locatar> locatari = service_.getAll();
				int i = 0;
				table->setColumnCount(4);
				table->setItem(0, 0, new QTableWidgetItem("Ap."));
				table->setItem(0, 1, new QTableWidgetItem("Loc."));
				table->setItem(0, 2, new QTableWidgetItem("Sup."));
				table->setItem(0, 3, new QTableWidgetItem("Tip."));
				table->setRowCount(locatari.size() + 1);
				for (const auto& locatar : locatari)
				{
					QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(locatar.to_string()), list);
					item->setData(Qt::UserRole, QString::fromStdString(std::to_string(i)));
					table->setItem(i + 1, 0, new QTableWidgetItem(QString::fromStdString(std::to_string(locatar.Apartament))));
					table->setItem(i + 1, 1, new QTableWidgetItem(QString::fromStdString(locatar.NumeProprietar)));
					table->setItem(i + 1, 2, new QTableWidgetItem(QString::fromStdString(std::to_string(locatar.Suprafata))));
					table->setItem(i + 1, 3, new QTableWidgetItem(QString::fromStdString(locatar.TipApartament)));
					i++;
				}
			});
		QObject::connect(SorteazaButton, &QPushButton::clicked, [=]()
			{
				qDebug() << "Sort";
				const std::vector<Locatar> aux = service_.sort();
				list_this(aux);
			});
		
		QObject::connect(adaugaButton, &QPushButton::clicked, [=]()
			{
				qDebug() << "AdaugaPressed";
				try
				{
					service_.adauga(apartamentInput->text().toInt(), numeInput->text().toStdString(), suprafataInput->text().toDouble(), tipInput->text().toStdString());
					qDebug() << "Locatar added";
					ListeazaButton->click();
				}
				catch (myError e)
				{
					const QString message = QString::fromStdString(e.Message);
					QMessageBox box;
					box.setText(QString::fromStdString(e.Message));
					box.exec();
					qDebug() << message;
				}
			});
		QObject::connect(stergeButton, &QPushButton::clicked, [=]()
			{
				qDebug() << "Sterge Pressed";
				try
				{
					service_.sterge(apartamentInput->text().toInt(), numeInput->text().toStdString(), suprafataInput->text().toDouble(), tipInput->text().toStdString());
					qDebug() << "Locatar sters";
					ListeazaButton->click();
				}
				catch (myError e)
				{
					const QString message = QString::fromStdString(e.Message);
					QMessageBox box;
					box.setText(QString::fromStdString(e.Message));
					box.exec();
					qDebug() << message;
				}
			});
		QObject::connect(modificaButton, &QPushButton::clicked, [=]()
			{
				qDebug() << "Modifica pressed";
				try
				{
					int apartament = table->selectedItems()[0]->text().toInt();
					std::string numeProp = table->selectedItems()[1]->text().toStdString();
					double suprafata = table->selectedItems()[2]->text().toDouble();
					std::string tip = table->selectedItems()[3]->text().toStdString();

					service_.update(apartament, numeProp, suprafata, tip, apartamentInput->text().toInt(), numeInput->text().toStdString(), suprafataInput->text().toDouble(), tipInput->text().toStdString());
					ListeazaButton->click();
				}
				catch (myError e)
				{
					const QString message = QString::fromStdString(e.Message);
					QMessageBox box;
					box.setText(QString::fromStdString(e.Message));
					box.exec();
					qDebug() << message;
				}

			});

		QObject::connect(table, &QTableWidget::itemSelectionChanged, [=]()
			{
				int apartament = table->selectedItems()[0]->text().toInt();
				std::string numeProp = table->selectedItems()[1]->text().toStdString();
				double suprafata = table->selectedItems()[2]->text().toDouble();
				std::string tip = table->selectedItems()[3]->text().toStdString();
				apartamentInput->setText(QString::fromStdString(std::to_string(apartament)));
				numeInput->setText(QString::fromStdString(numeProp));
				suprafataInput->setText(QString::fromStdString(std::to_string(suprafata)));
				tipInput->setText(QString::fromStdString(tip));
			});
		QObject::connect(searchButton, &QPushButton::clicked, [=]()
			{
				qDebug() << "search";
				QListWidget* list = new QListWidget();
				std::vector<Locatar> locatari=service_.getAll();
				for (const auto & locatar : locatari)
				{
					if(apartamentInput->text().toInt()==locatar.Apartament)
						list->addItem(new QListWidgetItem(QString::fromStdString(locatar.to_string())));
				}

				list->show();
			});
		
		QObject::connect(check_box1, &QCheckBox::stateChanged, [=]()
			{
				if (check_box1->checkState() == Qt::Checked)
				{
					check_box2->setCheckState(Qt::Unchecked);
					check_box3->setCheckState(Qt::Unchecked);
					std::vector<Locatar> locatars = service_.getAll();
					std::vector<Locatar> vector = std::vector<Locatar>();
					for (const auto & locatar : locatars)
					{
						if (locatar.TipApartament._Equal("pentHouse"))
							vector.push_back(locatar);
					}
					list_this(vector);
				}
				else
				{
					ListeazaButton->click();
				}
			});
		
		QObject::connect(check_box2, &QCheckBox::stateChanged, [=]()
			{
				if (check_box2->checkState() == Qt::Checked)
				{
					check_box1->setCheckState(Qt::Unchecked);
					check_box3->setCheckState(Qt::Unchecked);
					std::vector<Locatar> locatars = service_.getAll();
					std::vector<Locatar> vector = std::vector<Locatar>();
					for (const auto& locatar : locatars)
					{
						if (locatar.Suprafata > 200.0)
							vector.push_back(locatar);
					}
					list_this(vector);
				}
			else
			{
					ListeazaButton->click();
			}
			});
		QObject::connect(check_box3, &QCheckBox::stateChanged, [=]()
			{
				if (check_box3->checkState() == Qt::Checked)
				{
					check_box1->setCheckState(Qt::Unchecked);
					check_box2->setCheckState(Qt::Unchecked);
					std::vector<Locatar> locatars = service_.getAll();
					std::vector<Locatar> vector = std::vector<Locatar>();
					for (const auto& locatar : locatars)
					{
						if (locatar.TipApartament._Equal("garsoniera"))
							vector.push_back(locatar);
					}
					list_this(vector);
				}
			else
			{
					ListeazaButton->click();
			}
			});
		QObject::connect(Undo, &QPushButton::clicked, [=]()
			{
				service_.doUndo();
				ListeazaButton->click();
			});
	}
};
