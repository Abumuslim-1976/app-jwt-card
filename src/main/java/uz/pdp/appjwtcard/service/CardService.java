package uz.pdp.appjwtcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.payload.ApiResponse;
import uz.pdp.appjwtcard.payload.CardDto;
import uz.pdp.appjwtcard.repository.CardRepository;
import uz.pdp.appjwtcard.security.JwtFilter;
import uz.pdp.appjwtcard.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    CardRepository cardRepository;

    public ApiResponse create(CardDto cardDto, HttpServletRequest request) {

        //-----Cardga userni tokendan kelgan user bilan set qilamiz-----//
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String usernameFromToken = jwtProvider.getUserAndValidateToken(token);

        //-------Yangi karta create qilyapmiz------//
        Card card = new Card();
        card.setCardNumber(cardDto.getCardNumber());
        card.setBalance(cardDto.getBalance());
        card.setUsername(usernameFromToken);
        cardRepository.save(card);
        return new ApiResponse("Karta saqlandi", true);
    }

    public Card getOne(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElseGet(Card::new);
    }

    public List<Card> getMany() {
        return cardRepository.findAll();
    }

}
