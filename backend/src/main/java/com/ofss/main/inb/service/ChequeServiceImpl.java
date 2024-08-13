// package com.ofss.main.inb.service;

// import java.util.List;

// import com.ofss.main.inb.domain.Cheque;
// import com.ofss.main.inb.repo.ChequeRepo;
// import com.ofss.main.inb.repo.ChequeRepoImpl;

// public class ChequeServiceImpl  implements ChequeService{

//     ChequeRepo chequeRepo = new ChequeRepoImpl();

//     @Override
//     public boolean issueNewCheck(double amount, int from, int to) {
//         return chequeRepo.issueNewCheck(amount, from, to);
//     }

//     @Override
//     public boolean updateCheckByID(Cheque cheque) {
//        return chequeRepo.updateCheckByID(cheque);
//     }

//     @Override
//     public List<Cheque> getAllPendingChecks() {
//         return chequeRepo.getAllPendingChecks();
//     }
    
// }
