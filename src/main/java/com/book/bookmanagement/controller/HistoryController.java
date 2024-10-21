package com.book.bookmanagement.controller;

import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.entity.Fine;
import com.book.bookmanagement.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class HistoryController {

    private final HistoryService historyService;


    @GetMapping("/history")
    public ResponseEntity<List<BorrowTransaction>> getBorrowHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(historyService.getBorrowHistory(userId));
    }

    @GetMapping("/fines")
    public ResponseEntity<List<Fine>> getFines(@RequestParam Long userId) {
        return ResponseEntity.ok(historyService.getFines(userId));
    }

}
