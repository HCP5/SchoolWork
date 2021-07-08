#include "LocatarRepo.h"
std::vector<Locatar>& LocatarRepo::get_lista_locatari()
{
	return this->listaLocatari;
}

int LocatarRepo::getLocatar(const Locatar& locatarDeGasit) 
{
	int i = 0;
	auto current = listaLocatari.begin();
	auto last = listaLocatari.end();
	while (current != last)
	{
		if (*current == locatarDeGasit)
		{
			return i;
		}
		++current;
		++i;
	}
	return -1;
}

void LocatarRepo::addLocatar(const Locatar& locatar)
{
	if (validator.valideazaLocatar(locatar))
		if (!exists(locatar))
			this->listaLocatari.push_back(locatar);
}

void LocatarRepo::deleteLocatar(const Locatar& locatardeSters)
{
	auto current = listaLocatari.begin();
	auto last = listaLocatari.end();
	while (current!=last)
	{
		if (*current == locatardeSters)
		{
			listaLocatari.erase(current);
			return;
		}
		
		++current;
	}
}

int LocatarRepo::getRepoDim() const
{
	return listaLocatari.size();
}

void LocatarRepo::updateLocatar(const int locatarDeModif, const Locatar& locatar)
{
	int dim = listaLocatari.size();
	if (locatarDeModif > -1 && locatarDeModif < dim)
		if (validator.valideazaLocatar(locatar))
			listaLocatari[locatarDeModif] = locatar;
		else;
	else
		throw myError("Locatar Inexistent!");
}

bool LocatarRepo::exists(const Locatar& locatarDeGasit) 
{
	if(!listaLocatari.empty())
		for (auto& i : listaLocatari)
		{
			if(i ==locatarDeGasit)
			{
				return true;
			}
		}
	return false;
}



// void LocatarRepo::saveList()
// {
// 	for (const auto & locatar : listaLocatari)
// 	{
// 		writeToFile(locatar);
// 	}
// }
