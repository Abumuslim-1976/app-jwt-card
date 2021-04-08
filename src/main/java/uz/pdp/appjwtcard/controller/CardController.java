package uz.pdp.appjwtcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.payload.ApiResponse;
import uz.pdp.appjwtcard.payload.CardDto;
import uz.pdp.appjwtcard.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> createCard(@RequestBody CardDto cardDto, HttpServletRequest request) {
        ApiResponse apiResponse = cardService.create(cardDto, request);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<Card> getOneCard(@PathVariable Integer id) {
        Card card = cardService.getOne(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping
    public HttpEntity<List<Card>> getManyCard() {
        List<Card> cardList = cardService.getMany();
        return ResponseEntity.ok(cardList);
    }

}
