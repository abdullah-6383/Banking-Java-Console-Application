package com.project.bank.entity;

import java.time.LocalDate;

public record Transaction(LocalDate transactionDate, String transactionUserId, Double transactionAmount,
                          Double initialBalance, Double finalBalance, String transactionType) {

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate=" + transactionDate +
                ", transactionUserId='" + transactionUserId + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", initialBalance=" + initialBalance +
                ", finalBalance=" + finalBalance +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
