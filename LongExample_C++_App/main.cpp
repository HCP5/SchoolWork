#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>

#include "LocatarRepo.h"
#include "myGUI.h"
#include "ServiceLocatar.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    validatorLocatar validator=validatorLocatar();
    LocatarRepo repo=LocatarRepo(validator);
    ServiceLocatar service=ServiceLocatar(repo);
    myGUI gui=myGUI(service);
    gui.show();
	
    return a.exec();
}
