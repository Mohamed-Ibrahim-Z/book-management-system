package com.book.bookmanagement.service;

import com.book.bookmanagement.entity.BorrowTransaction;
import com.book.bookmanagement.entity.Fine;

import java.util.List;

public interface HistoryService {
    public List<BorrowTransaction> getBorrowHistory(Long userId);

    public List<Fine> getFines(Long userId);

    public Fine payFine(Long fineId);
}
