package com.emkave.pacman.handler;

import java.security.SecureRandom;

public class RandomHandler {
    private static final SecureRandom secureRandom = new SecureRandom();


    public static double getRandom() {
        long entropy = 0;

        for (int i=0; i<64; i++) {
            long t1 = System.nanoTime();
            while (System.nanoTime() == t1) {
                long t2 = System.nanoTime();
                entropy ^= t2 - t1;
            }
        }

        return 1.00 + Math.abs(entropy % 100000) / 100.0;
    }
}
