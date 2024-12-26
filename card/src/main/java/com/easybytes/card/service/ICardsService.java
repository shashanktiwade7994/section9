package com.easybytes.card.service;

import com.easybytes.card.dto.CardsDto;

public interface ICardsService{

    CardsDto findByMobileNumber(String mobileNumber);

    void create(String mobileNumber);

    boolean updateCard(CardsDto cardDto);

    boolean deleteCard(String mobileNumber);
}
