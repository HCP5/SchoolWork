#include "validatorLocatar.h"

bool validatorLocatar::valideazaLocatar(const Locatar& locatar)
{
	if (locatar.Apartament < 1)
		throw myError("Apartament invalid!");
	if (locatar.Suprafata < 1)
		throw myError("Suprafata Invalida");
	if (locatar.NumeProprietar._Equal(""))
		throw myError("Proprietar Invalid");
	if (locatar.TipApartament._Equal("garsoniera") || locatar.TipApartament._Equal("pentHouse") || locatar.TipApartament._Equal("apartament"))
		return  true;
	throw myError("tip invalid !");
}

