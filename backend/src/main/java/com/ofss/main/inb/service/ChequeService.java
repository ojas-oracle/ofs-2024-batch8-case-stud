package com.ofss.main.inb.service;

import java.util.List;

import com.ofss.main.inb.domain.Cheque;

public interface ChequeService {
    boolean issueNewCheck(double amount , int from , int to);
    boolean updateCheckByID(Cheque cheque);
    List<Cheque> getAllPendingChecks();
}
