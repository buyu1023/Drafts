package org.example;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author buyu_6911
 * @version 2024/10/7 16:44
 * note:
 */
public class Draft {
    public static void main(String[] args) {
        int[] test = new int[]{1,3,5};

        int j = 0;
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[j++]);
        }
    }

    static class finalTest {
        public static final int i;
        static {
            i = new Random().nextInt(100);
        }
    }

}

