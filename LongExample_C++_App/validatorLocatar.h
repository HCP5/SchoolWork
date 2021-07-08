#pragma once
#include "Locatar.h"

class validatorLocatar
{
public:

	validatorLocatar() = default;

	validatorLocatar(const validatorLocatar& ot) = delete;
	
	/*
	 *valideaza un locatar
	 *i:Locatar
	 *o:true daca e valid, fals altfel
	 */
	bool valideazaLocatar(const Locatar& locatar);
};

class myError
{
	std::string message;

public:
	explicit myError(const std::string& message)
		: message(message)
	{
	}


	std::string& get_message()
	{
		return message;
	}

	__declspec(property(get = get_message)) std::string Message;
};