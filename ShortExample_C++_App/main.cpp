#include <QApplication>
#include <QPushButton>
#include "teste.h"
#include "myGUI.h"

int main(int argc, char *argv[]) {
    testAll();
    QApplication a(argc, argv);
    Repository repository("date.txt");
    Service service(repository);
    myGUI gui(service);
    gui.show();
    return QApplication::exec();
}
