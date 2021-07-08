#pragma once
#include "LocatarRepo.h"


class ActiuneUndo 
{
public:
	virtual ~ActiuneUndo() = default;

	virtual void doUndo() =0;
};

class UndoAdauga : public ActiuneUndo
{
public:
	void doUndo() override
	{
		repo_.deleteLocatar(locatar_);
	}

	UndoAdauga( LocatarRepo& repo, const Locatar& locatar): repo_{repo}
	{
		locatar_ = locatar;
	}
private:
	LocatarRepo& repo_;
	Locatar locatar_;


	
};

class UndoSterge : public ActiuneUndo
{
public:
	void doUndo() override
	{
		repo_.addLocatar(locatar_);
	}

	UndoSterge(LocatarRepo& repo, const Locatar& locatar):repo_ { repo }
	{
		locatar_ = locatar;
	}
private:
	LocatarRepo& repo_;
	Locatar locatar_;

	
};

class UndoModifica : public ActiuneUndo
{
public:
	void doUndo() override
	{
		repo_.deleteLocatar(locatarVechi_);
		repo_.addLocatar(locatarNou_);
	}

	UndoModifica(LocatarRepo& repo, const Locatar& locatarVechi,const Locatar& locatarNou) :repo_{ repo }
	{
		locatarVechi_ = locatarVechi;
		locatarNou_ = locatarNou;
	}
private:
	LocatarRepo& repo_;
	Locatar locatarVechi_;
	Locatar locatarNou_;


};

