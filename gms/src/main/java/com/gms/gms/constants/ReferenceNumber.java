package com.gms.gms.constants;

import java.util.Random;

public class ReferenceNumber {

    public static String generateUniqueReference(int length) {
        Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(random.nextInt(10)); // Generate a random digit (0-9)
            }
        return sb.toString();
    }
}
