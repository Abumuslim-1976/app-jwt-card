package uz.pdp.appjwtcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjwtcard.payload.ApiResponse;
import uz.pdp.appjwtcard.payload.TransferDto;
import uz.pdp.appjwtcard.service.TransferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    TransferService transfer;

    @PostMapping
    public HttpEntity<?> createMoney(@RequestBody TransferDto transferDto, HttpServletRequest request) {
        ApiResponse apiResponse = transfer.create(transferDto, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }



}
