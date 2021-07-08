#pragma once
#include <QMessageBox>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QLabel>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QHBoxLayout>


#include "ServiceLocatar.h"

class COSGui : public QWidget
{
private:
	ServiceLocatar& service_;
	QPushButton* emptyCos;
	QPushButton* add;
	QPushButton* generate;
	QPushButton* exportCos;
	QLineEdit* input;
	QListWidget* listaCos;
	QLabel* label;
	

public:
	void setWindow()
	{
		emptyCos = new QPushButton("Empty");
		add = new QPushButton("Add");
		generate= new QPushButton("Generate");
		exportCos = new QPushButton("Export");
		label = new QLabel();

		auto mainly = new QVBoxLayout;
		mainly->addWidget(label);
		auto hor = new QHBoxLayout;
		mainly->addLayout(hor);
		input = new QLineEdit();
		mainly->addWidget(input);
		hor->addWidget(emptyCos);
		hor->addWidget(add);
		hor->addWidget(generate);
		hor->addWidget(exportCos);

		setLayout(mainly);
		
		
	}
	
	
	void connection()
	{
		QObject::connect(emptyCos, &QPushButton::clicked, [=]()
			{
				int a=service_.emtyCos();
				label->setText( QString::fromStdString(std::to_string(a)));
			});
		QObject::connect(add,&QPushButton::clicked,[=]()
		{
				int ap = input->text().toInt();
				int a=service_.adaugaInCos(ap);
				label->setText( QString::fromStdString(std::to_string(a)));
		});
		QObject::connect(generate, & QPushButton::clicked, [=]()
			{
				// int size = service_.getAll().size();
				if(input->text().toInt()<=0)
				{
					QMessageBox box;
					box.setText("Dati nr de generari!");
					box.exec();
				}
				else {
					int a = service_.genereaza(input->text().toInt());
					label->setText(QString::fromStdString(std::to_string(a)));
				}

			});
		QObject::connect(exportCos, &QPushButton::clicked, [=]()
			{
				service_.exporta();
				QMessageBox box;
				box.setText("Exportat cu succes!");
				box.exec();
			});
	}

	COSGui(ServiceLocatar& service)
		: service_{service}
	{
		setWindow();
		connection();
	}
};

