package org.example;

/**
 * @author buyu_6911
 * @version 2024/10/8 15:01
 * note:
 */
public class StringSplitTest {
    public static void main(String[] args) {
        String s = "  s   fhg    s    s";

        for (String a : s.split(" ")) {
            if (a.equals(" ")) continue;
            System.out.println("[" + a + "]");
        }
    }
}

