#pragma once
#include <fstream>
#include <iostream>
#include <vector>

#include "Locatar.h"

class FileRepo
{
protected:
	std::vector<Locatar> listaLocatari;
public:
	FileRepo(){
		std::ofstream file;
		file.open("dateCSV.csv");
		file.clear();
	}

	void writeToFile(Locatar& x)
	{
		std::fstream file;
		std::string numeFisier = "dateCSV.csv";
		std::vector<Locatar> locatariExistenti = readFromFile();
		
		file.open(numeFisier);
		for(const auto& l : locatariExistenti)
		{
			file << l.Apartament<<", "<<l.NumeProprietar<<", "<<l.Suprafata<<", "<<l.TipApartament<<"\n";
		}
		file << x.Apartament << ", " << x.NumeProprietar << ", " << x.Suprafata << ", " << x.TipApartament << "\n";
		file.close();
	}

	std::vector<Locatar>&readFromFile() const
	{//apartament : 2; numeProprietar: eu; suprafata : 100; tipApartament: apartament;
		std::fstream file;
		std::string numeFisier = "dateCSV.csv";
		file.open(numeFisier);
		std::vector<Locatar> rez;
		Locatar x;
		std::string data;
		std::string delimiter = ";";
		while (std::getline(file,data))
		{
			data.erase(0, data.find(": ") + 2);
			auto apartament=data.substr(0,data.find(delimiter));
			data.erase(0, data.find(": ") + 2);
			auto numeProprietar=data.substr(0,data.find(delimiter));
			data.erase(0, data.find(": ") + 2);
			auto suprafata=data.substr(0,data.find(delimiter));
			data.erase(0, data.find(": ") + 2);
			auto tip = data.substr(0, data.find('\n'));
			rez.emplace_back(stoi(apartament), numeProprietar, std::stod(suprafata), tip);
		}
		
		file.close();
		return rez;
	}
	void golesteLista()
	{
		while (!listaLocatari.empty())
		{
			listaLocatari.pop_back();
		}
	}

	void addInList(const Locatar& locatar)
	{
		listaLocatari.push_back(locatar);
	}

	void generateList(const int noAp,const std::vector<Locatar>&vectorRepo)
	{
		const int size = vectorRepo.size();
		if (noAp >= size)
		{
			for (const auto& locatar : vectorRepo)
				listaLocatari.push_back(locatar);
			return;
		}
		
		{//Altfel ia random
			const int size1 = vectorRepo.size();
			std::vector<int>solution=std::vector<int>();
			int noGenerated=0;
			while (noGenerated!=noAp)
			{
				int generated = std::rand() % size1;

				while (std::count(solution.begin(),solution.end(),generated))
				{
					generated = std::rand() % size1;
				}
				solution.push_back(generated);
				noGenerated++;
			}

			for (auto value : solution)
				listaLocatari.push_back(vectorRepo[value]);
		}

		
		// for(int i=0;i<noAp;++i)
		// {
		// 	Locatar loc = Locatar(i, "UnNume", i * 100, "apartament");
		// 	listaLocatari.push_back(loc);
		// }
	}

	void exportToCSV()
	{
		std::fstream file;
		file.open("dateCSV.csv");
		file << "Apartament," << "NumePropiretar," << "Suprafata," << "TipApartament"<<"\n";
		for (const auto& locatar : listaLocatari)
		{
			file << locatar.Apartament << ", " << locatar.NumeProprietar << ", " << locatar.Suprafata << ", " << locatar.TipApartament << "\n";
		}
		file.close();
	}

	int getDIM()
	{
		return listaLocatari.size();
	}
};

