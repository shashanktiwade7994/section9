package com.easybytes.card.service.impl;

import com.easybytes.card.constants.CardsConstants;
import com.easybytes.card.dto.CardsDto;
import com.easybytes.card.entity.Cards;
import com.easybytes.card.exceptions.CardAlreadyExistsException;
import com.easybytes.card.exceptions.CardNotFoundException;
import com.easybytes.card.mapper.CardsMapper;
import com.easybytes.card.repository.CardsRespository;
import com.easybytes.card.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRespository cardRepository;

    @Override
    public CardsDto findByMobileNumber(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new CardNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public void create(String mobileNumber) {
        Optional<Cards> cards = cardRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()){
            throw new CardAlreadyExistsException("Card is already present in DB" + mobileNumber);
        }

        cardRepository.save(createNewCard(mobileNumber));

    }

    @Override
    public boolean updateCard(CardsDto cardDto) {
        Cards cards = cardRepository.findByMobileNumber(cardDto.getMobileNumber())
                .orElseThrow(() -> new CardNotFoundException("Cards", "mobileNumber", cardDto.getMobileNumber()));
        CardsMapper.mapToCards(cardDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new CardNotFoundException("Cards", "mobileNumber", mobileNumber)
        );

//        Optional<Cards> cards = cardRepository.findByMobileNumber(mobileNumber);
//        Long id = cards.get().getCardId();


        cardRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
//        newCard.setCreatedAt(LocalDateTime.now());
//        newCard.setCreatedBy("Anonymous");
        return newCard;
    }



}
