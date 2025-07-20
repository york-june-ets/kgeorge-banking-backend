package solutions.york.bankingbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.york.bankingbackend.dto.TransferRequest;
import solutions.york.bankingbackend.models.Transfer;
import solutions.york.bankingbackend.services.TransferService;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequest request) {
        Transfer transfer = transferService.create(request);
        return ResponseEntity.ok(transfer);
    }
}
