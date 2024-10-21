package com.book.bookmanagement.service.impl;

import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.entity.Fine;
import com.book.bookmanagement.entity.User;
import com.book.bookmanagement.exception.ResourceNotFoundException;
import com.book.bookmanagement.repository.BorrowTransactionRepository;
import com.book.bookmanagement.repository.FineRepository;
import com.book.bookmanagement.repository.UserRepository;
import com.book.bookmanagement.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final BorrowTransactionRepository borrowTransactionRepository;
    private final FineRepository fineRepository;
    private final UserRepository userRepository;
    @Override
    public List<BorrowTransaction> getBorrowHistory(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return borrowTransactionRepository.findByUser(userOpt.get());
    }

    @Override
    public List<Fine> getFines(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return fineRepository.findByUser(userOpt.get());
    }

    @Override
    public Fine payFine(Long fineId) {
        Optional<Fine> fineOpt = fineRepository.findById(fineId);
        if (fineOpt.isEmpty()) {
            throw new RuntimeException("Fine not found");
        }

        if(fineOpt.get().getStatus().equals("paid")) {
            throw new RuntimeException("Fine already paid");
        }

        Fine fine = fineOpt.get();
        fine.setStatus("paid");
        fine.setPaidAt(LocalDateTime.now());

        return fineRepository.save(fine);
    }
}
