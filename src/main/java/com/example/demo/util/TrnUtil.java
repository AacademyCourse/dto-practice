package com.example.demo.util;

import java.math.BigDecimal;

public class TrnUtil {
    public static boolean compareAmounts(BigDecimal balance,Double trnAmount){
        boolean result = false;
        Double clientBalance = balance.doubleValue();
        if(clientBalance>= trnAmount) {
            result = true;
        }
        return result;
    }
}
