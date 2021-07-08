#pragma once
#include <ostream>
#include <string>


/*
 * · gestiunea unei liste de locatari. Locatar: apartament, nume proprietar, suprafata, tip
 *   apartament
 */
class Locatar
{
private:
	int apartament;
	std::string numeProprietar;
	double suprafata;
	std::string tipApartament;

public:
	/*
	 * constructor cu parametrii
	 * i:parametrii
	 * o: un locatar
	 */
	Locatar(const int& apartament, const std::string& nume_proprietar, const double& suprafata,
		const std::string& tip_apartament);
	Locatar()=default;

	Locatar(const Locatar& ot)=default;

	/*
	 * get-ere si sett-ere
	 */
	int get_apartament()const;
	void set_apartament(const int& a);
	__declspec(property(get = get_apartament, put = set_apartament)) int Apartament;

	std::string get_nume_proprietar() const;
	void set_nume_proprietar(const std::string& nume_proprietar);
	__declspec(property(get = get_nume_proprietar, put = set_nume_proprietar)) std::string NumeProprietar;

	double get_suprafata() const;
	void set_suprafata(const double& s);
	__declspec(property(get = get_suprafata, put = set_suprafata)) double Suprafata;

	std::string get_tip_apartament() const;
	void set_tip_apartament(const std::string& tip_apartament);
	__declspec(property(get = get_tip_apartament, put = set_tip_apartament)) std::string TipApartament;

	/*
	 *operator de egalitate
	 *i: lhs: Locatarul din stanga egalitatii rhs: din dreapta
	 *o: true-in caz de egalitate, fals-altfel
	 */
	friend bool operator==(const Locatar& lhs, const Locatar& rhs);
	friend bool operator!=(const Locatar& lhs, const Locatar& rhs);

	/*
	 *pentru afisare ToString();
	 */
	friend std::ostream& operator<<(std::ostream& os, const Locatar& obj)
	{
		return os
			<< "apartament: " << obj.apartament
			<< "; numeProprietar: " << obj.numeProprietar
			<< "; suprafata: " << obj.suprafata
			<< "; tipApartament: " << obj.tipApartament<<";\n";
	}

	std::string to_string() const
	{
		return std::to_string(apartament) + "; " + numeProprietar + "; " + std::to_string(suprafata) + "; " + tipApartament;
	}
};

