package org.example;

/**
 * @author buyu_6911
 * @version V1.0
 * @package org.example
 * @date 2024/5/12 12:27
 */
public class CreateStringTest {
    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        stringTest.test4();
    }
}

class StringTest {
    void test1() {
        String s = new String("1");
        String s2 = "1";
        String intern = s.intern();
        System.out.println(intern == s);// f
        System.out.println(s == s2);// f
        System.out.println(s2 == intern);// t
    }

    void test2() {
        String s3 = new String("1") + new String("2");
        s3.intern();
        String s4 = "12";
        System.out.println(s3 == s4);// f
    }

    void test3() {
        String s1 = new StringBuilder("北京").toString();
        String internS1 = s1.intern();
        System.out.println(internS1 == s1);// f
    }

    void test4() {
        final String s1 = "ioduaosihjd";
        final String s2 = "adjiashdajdo";
        String s3 = s1 + s2;// 编译器优化 直接变成结果，放到常量池里
        String s4 = "ioduaosihjd" + "adjiashdajdo";
        s4 = s3.intern();
        System.out.println(s3 == s4);// t
    }
}




