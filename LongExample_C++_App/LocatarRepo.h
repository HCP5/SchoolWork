#pragma once
#include <vector>

#include "FileRepo.h"
#include "Locatar.h"
#include "validatorLocatar.h"

class LocatarRepo : public FileRepo 
{
private:
	std::vector<Locatar> listaLocatari;
	validatorLocatar validator;
	
public:
	//Constructori;
	 LocatarRepo(const validatorLocatar& validator)
		:FileRepo::FileRepo() {
		this->validator = validator;
	}

	 LocatarRepo(const LocatarRepo& ot) = delete;
	
	 LocatarRepo() = default;

	/*
	 * returneaza lista din repo
	 * o: un vector de Locatari
	 */
	std::vector<Locatar>& get_lista_locatari() ;

	/*
	 * returneaza indicele unui locatar cautat
	 * i: locatarul cautat
	 * o: indicele la care se afla in vector
	 */
	int getLocatar(const Locatar& locatar)  ;

	__declspec(property(get = get_lista_locatari)) std::vector<Locatar>  ListaLocatari;

	/*
	 *adauga Locatar in vector
	 *i:locatar
	 *o:-
	 */
	void addLocatar(const Locatar& locatar) ;

	/*
	 *sterge locatar din vector
	 *i:locatar
	 */
	void deleteLocatar(const Locatar& locatar) ;



	/*
	 *returneaza dimensiunea vectorului(cati locatari am)
	 *o: dim vector int
	 */
	int getRepoDim() const ;

	/*
	 * face update la un locatar
	 * i:locatarDeModif: Locatar vechi locatar: locatar nou
	 */
	void updateLocatar(const int locatarDeModif, const Locatar& locatar);

	/*
	 *cauta daca exista un locatar in vector
	 *o: true daca da, fals altfel
	 */
	bool exists(const Locatar& locatar);

	/*
	 * creeaza un locatar
	 * i:parametrii unui locatar
	 * o:un locatar
	 */
	

	void freeList() 
	{
		golesteLista();
	}

	void addLocatar(int nrAp) 
	{
		for (const auto& locatar : listaLocatari)
		{
			if(locatar.Apartament==nrAp)
			{
				addInList(locatar);
			}
		}
	}

	void generate(int no, const std::vector<Locatar>& vector)  
	{
		generateList(no,vector);
	}

	void exportList() 
	{
		exportToCSV();
	}

	int getDimCos() 
	{
		return getDIM();
	}
};

