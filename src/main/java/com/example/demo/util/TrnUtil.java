package com.example.demo.util;

import java.math.BigDecimal;

public class TrnUtil {
    public static boolean compareAmounts(BigDecimal balance, Double trnAmount) {
        Double clientBalance = balance.doubleValue();
        boolean result = false;
        return clientBalance >= trnAmount;
    }
}
