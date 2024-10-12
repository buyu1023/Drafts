package org.example;

import java.util.HashSet;

/**
 * @author buyu_6911
 * @version 2024/9/20 17:16
 * note:
 */
public class HashMapExpendTest {
    public static void main(String[] args) {
        HashSet<Homogenous> set = new HashSet<Homogenous>();

        for (int i = 0; i < 25; i++) {
            set.add(new Homogenous(i));
        }
    }
}

class Homogenous {
    int val;

    public Homogenous(int val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return 111;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Homogenous) {
            return this.val == ((Homogenous) obj).val;
        }
        return false;
    }
}
