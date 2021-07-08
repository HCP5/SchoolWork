#pragma once
#include <map>
#include <stack>

#include "ActiuneUndo.h"
#include "LocatarRepo.h"

class ServiceLocatar
{
private:
	LocatarRepo& repo;
	std::vector<std::unique_ptr<ActiuneUndo>> undos_;
	// repoNou& repo;
public:
	//constructori
	ServiceLocatar(LocatarRepo& repo) :repo{ repo } {}
	ServiceLocatar() = default;
	ServiceLocatar(const ServiceLocatar& ot) = delete;

	// explicit ServiceLocatar(repoNou& repo) :repo{ repo }{}

	/*
	 *adauga un locatar in repo
	 *i:parametrii unui locatar
	 *o:-
	 */
	void adauga(const int& apartament,const std::string& nume, const double& suprafata, const std::string& tip);
	
	/*
	 *sterge un locatar din repo
	 *i:parametrii unui locatar
	 *o:-
	 */
	void sterge(const int& apartament,const std::string& nume, const double& suprafata, const std::string& tip);

	/*
	 *face update la un locatar din repo
	 *i: parametrii locatarului cautat, parametrii noi
	 *o: -
	 */
	void update(const int& apartament,const std::string& nume, const double& suprafata, const std::string& tip, const int& apartament2, const std::string& nume2, const double& suprafata2, const std::string& tip2);

	/*
	 * aduce vectorul de locatari din repo
	 * i:-
	 * o:std::vector<Locatari>
	 */
	std::vector<Locatar>& getAll() ;
	void saveToFile();


	int emtyCos()
	{
		repo.freeList();
		return repo.getDimCos();
	}

	int adaugaInCos(int no)
	{
		repo.addLocatar(no);
		return repo.getDimCos();
	}

	int genereaza(int no)
	{
		repo.generate(no, repo.ListaLocatari);
		return repo.getDimCos();
	}

	void exporta()
	{
		repo.exportList();
	}

	std::map<std::string,int> getReport()
	{
		std::vector<Locatar> lista = repo.ListaLocatari;
		std::map<std::string, int> myMap;
		myMap.insert(std::pair<std::string,int>("apartament",0));
		myMap.insert(std::pair<std::string,int>("garsoniera",0));
		myMap.insert(std::pair<std::string,int>("pentHouse",0));
		
		for (auto& locatar : lista)
		{
			myMap.at(locatar.TipApartament)++;
		}

		return myMap;
	}


	void doUndo()
	{
		if (!undos_.empty()) {
			undos_.back()->doUndo();
			undos_.pop_back();
		}
	}

	std::vector<Locatar> sort()
	{
		std::vector<Locatar> locatari=getAll();
		std::vector<Locatar> aux = locatari;
		for (int i = 0; i < locatari.size()-1; ++i)
		{
			for(int j=i+1;j<locatari.size();++j)
				if (aux[i].NumeProprietar > aux[j].NumeProprietar)
				{
					Locatar loc(aux[i].Apartament, aux[i].NumeProprietar, aux[i].Suprafata, aux[i].TipApartament);
					aux[i] = aux[j];
					aux[j] = loc;
				}
		}
		return aux;
	}
};

