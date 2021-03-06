package com.kulsin.wallet.model;

import com.kulsin.accounting.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponse {
    List<Transaction> transactions;
}
