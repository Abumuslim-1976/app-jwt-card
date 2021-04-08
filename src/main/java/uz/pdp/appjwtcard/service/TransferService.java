package uz.pdp.appjwtcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.entity.Income;
import uz.pdp.appjwtcard.entity.Outcome;
import uz.pdp.appjwtcard.payload.ApiResponse;
import uz.pdp.appjwtcard.payload.TransferDto;
import uz.pdp.appjwtcard.repository.CardRepository;
import uz.pdp.appjwtcard.repository.IncomeRepository;
import uz.pdp.appjwtcard.repository.OutcomeRepository;
import uz.pdp.appjwtcard.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse create(TransferDto transferDto, HttpServletRequest request) {

        //----------Pul o`tkazuvchi karta-----------//
        Optional<Card> optionalFromCard = cardRepository.findById(transferDto.getFromCardId());
        if (!optionalFromCard.isPresent()) {
            return new ApiResponse("Bunday ID li pul o`tkazuvchi card topilmadi", false);
        }
        Card fromCard = optionalFromCard.get();
        Double fromCardBalance = fromCard.getBalance();

        //--------pul o`tkazuvchi kartani userini tekshiramiz-------//
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String userFromToken = jwtProvider.getUserAndValidateToken(token);
        if (!fromCard.getUsername().equals(userFromToken)) {
            return new ApiResponse("Bu karta sizning kartangiz emas , pul o`tkazolmaysiz", false);
        }


        //---------Pul qabul qiluvchi karta-----------//
        Optional<Card> optionalToCard = cardRepository.findById(transferDto.getToCardId());
        if (!optionalToCard.isPresent()) {
            return new ApiResponse("Bunday ID li pul qabul qiluvchi topilmadi", true);
        }
        Card toCard = optionalToCard.get();
        Double toCardBalance = toCard.getBalance();

        //----------pul o`tkazuvchi kartadan pul yechib olindi 1% comissiya bilan-------//
        Double commission = transferDto.getAmount() / 100;
        double amountByCommission = transferDto.getAmount() + commission;
        if (amountByCommission < fromCardBalance) {
            fromCardBalance = fromCardBalance - (amountByCommission);
        } else {
            return new ApiResponse("Karta ichida yetarli pul mablag` mavjud emas", false);
        }

        //----------pul qabul qiluvchiga pul kelib tushdi----------//
        toCardBalance = toCardBalance + transferDto.getAmount();

        //---------malumotlar omboriga o`zgarishlarni saqladik--------//
        fromCard.setBalance(fromCardBalance);
        toCard.setBalance(toCardBalance);
        cardRepository.save(fromCard);
        cardRepository.save(toCard);

        //---------chiqimlar tarixi---------//
        Outcome outcome = new Outcome();
        outcome.setAmount(transferDto.getAmount());
        outcome.setCommissionAmount(commission);
        outcome.setFromCard(fromCard);
        outcome.setToCard(toCard);
        outcomeRepository.save(outcome);

        //-------kirimlar tarixi--------//
        Income income = new Income();
        income.setAmount(transferDto.getAmount());
        income.setFromCard(fromCard);
        income.setToCard(toCard);
        incomeRepository.save(income);

        return new ApiResponse("Pul muvaffaqiyatli o`tkazildi", true);
    }



}






