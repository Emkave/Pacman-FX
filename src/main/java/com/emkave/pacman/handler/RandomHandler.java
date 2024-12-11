package com.emkave.pacman.handler;

import java.security.SecureRandom;

public class RandomHandler {
    private static final SecureRandom secureRandom = new SecureRandom();


    public static int generateEntropyBasedRandomLong() {
        int entropy = 0;

        for (int i=0; i<64; i++) {
            long t1 = System.nanoTime();
            while (System.nanoTime() == t1);
            long t2 = System.nanoTime();
            entropy ^= (int)((t2 - t1) * secureRandom.nextInt());
        }

        return Math.abs(entropy);
    }


    public static int getRandomInt() {
        return RandomHandler.generateEntropyBasedRandomLong() % 100001;
    }
}
