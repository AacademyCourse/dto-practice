package com.example.demo.util;
import java.math.BigDecimal;

public class trnUtil {
    public static boolean compareAmounts(BigDecimal balance, Double trnAmount){
        double clientBalance = balance.doubleValue();
        return(clientBalance >= trnAmount);
    }


}
