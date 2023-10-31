package com.jennifer.sbank.utils;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class AccountUtils {
    private Set<String> existingAccountNumbers;
    private Set<String> existingReferenceNumbers;

    public String generateAccountNumber() {
        String accountNumber;
        do {
            int randomNumbers = new Random().nextInt(10000000);
            accountNumber = String.format("00%08d", randomNumbers);
        } while (existingAccountNumbers.contains(accountNumber));
        existingAccountNumbers.add(accountNumber);
        return accountNumber;
    }

    private String generateReferenceNumber() {
        String referenceNumber;
        do {
            UUID uuid = UUID.randomUUID();
            referenceNumber = "REF" + uuid.toString().substring(0, 8);
        } while (existingReferenceNumbers.contains(referenceNumber));

        existingReferenceNumbers.add(referenceNumber);
        return referenceNumber;
    }
}







