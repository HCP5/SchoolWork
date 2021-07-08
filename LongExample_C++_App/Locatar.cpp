#include "Locatar.h"

#include <iostream>

Locatar::Locatar(const int& apartament, const std::string& nume_proprietar, const double& suprafata,
                 const std::string& tip_apartament)
{
	this->apartament = apartament;
	this->numeProprietar= nume_proprietar;
	this->suprafata = suprafata;
	this->tipApartament = tip_apartament;
}


int Locatar::get_apartament() const
{
	return apartament;
}

void Locatar::set_apartament(const int& a)
{
	this->apartament = a;
}

std::string Locatar::get_nume_proprietar() const
{
	return numeProprietar;
}

void Locatar::set_nume_proprietar(const std::string& nume_proprietar)
{
	numeProprietar = nume_proprietar;
}

double Locatar::get_suprafata() const
{
	return suprafata;
}

void Locatar::set_suprafata(const double& s)
{
	this->suprafata = s;
}

std::string Locatar::get_tip_apartament() const
{
	return tipApartament;
}

void Locatar::set_tip_apartament(const std::string& tip_apartament)
{
	tipApartament = tip_apartament;
}

bool operator==(const Locatar& lhs, const Locatar& rhs)
{
	return lhs.apartament == rhs.apartament
		&& lhs.numeProprietar == rhs.numeProprietar
		&& lhs.suprafata == rhs.suprafata
		&& lhs.tipApartament == rhs.tipApartament;

}

bool operator!=(const Locatar& lhs, const Locatar& rhs)
{
	
	return !(lhs == rhs);
	
}
