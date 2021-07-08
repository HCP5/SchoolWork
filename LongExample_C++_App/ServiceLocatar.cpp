#include "ServiceLocatar.h"

void ServiceLocatar::adauga(const int& apartament, const std::string& nume, const double& suprafata,
	const std::string& tip)
{
	Locatar locatar(apartament, nume, suprafata, tip);
	try
	{
		repo.addLocatar(locatar);
		undos_.push_back(std::make_unique<UndoAdauga>(repo, locatar));
	}
	catch (myError& e)
	{
		throw e;}
}

void ServiceLocatar::sterge(const int& apartament, const std::string& nume, const double& suprafata,
	const std::string& tip)
{
	Locatar locatar(apartament, nume, suprafata, tip);
	repo.deleteLocatar(locatar);
	undos_.push_back(std::make_unique<UndoSterge>(repo,locatar));
}

void ServiceLocatar::update(const int& apartament, const std::string& nume, const double& suprafata,
	const std::string& tip, const int& apartament2, const std::string& nume2, const double& suprafata2,
	const std::string& tip2)
{
	Locatar locatarDeModif(apartament, nume, suprafata, tip);
	Locatar locatarNou(apartament2, nume2, suprafata2, tip2);
	
	if(repo.exists(locatarDeModif))
	{
		int locatarDeSchimbat = repo.getLocatar(locatarDeModif);
		repo.updateLocatar(locatarDeSchimbat, locatarNou);
		undos_.push_back(std::make_unique<UndoModifica>(repo, locatarNou, locatarDeModif));
	}
}

std::vector<Locatar>& ServiceLocatar::getAll()
{
	return repo.ListaLocatari;
}


// void ServiceLocatar::saveToFile()
// {
// 	repo.saveList();
// }
