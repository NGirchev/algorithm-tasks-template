package ru.girchev.tasks2020.binary.result;

/**
 * @author Girchev N.A.
 * Date: 11.03.2020
 */
public class BytesExample {

    public static void main(String[] args) {
        System.out.println(0x1 == 1);
        System.out.println(0x01 == 1);
        System.out.println(0x0A == 10);
        System.out.println(0x10 == 16);
        System.out.println(0x20 == 32);
        System.out.println(0x90 == 144);
        System.out.println(Byte.MIN_VALUE); //-128
        System.out.println(Byte.MAX_VALUE); //127
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90) {
                System.out.print("NEVER PRINT IT");
            }
        }

        byte a = 7;                                         //0000 0111
        short b = 7;                                        //0000 0000 0000 0111

        System.out.println("~7 = " + (~a));                 //1111 1000 = -8

        int a1 = 5;                                         //0101
        int b1 = 11;                                        //1011

        System.out.println("5 & 11   = " + (a1 & b1));      //0001 = 1
        System.out.println("5 | 11   = " + (a1 | b1));      //1111 = 15
        System.out.println("5 ^ 11   = " + (a1 ^ b1));      //1110 = 14  XOR

        int a2 = 5;                                         //0101
        int b2 = 3;                                         //0011

        System.out.println("5 >>  3 = " + (a2 >>  b2));     //0000 = 0
        System.out.println("5 >>> 3 = " + (a2 >>> b2));     //0000 = 0
        System.out.println("5 <<  3 = " + (a2 <<  b2));     //0010 1000 = 40

        int a3 = 5;                                         //0101
        int b3 = 1;                                         //0001

        System.out.println("5 >>  1 = " + (a3 >>  b3));     //0010 = 2
        System.out.println("5 >>> 1 = " + (a3 >>> b3));     //0010 = 2
        System.out.println("5 <<  1 = " + (a3 <<  b3));     //1010 = 10

        byte a4 = -8;                                       //1111 1000

        System.out.println("-8 >>> 1 = " + (a4 >>> 1));     //2147483644

        int a5 = 6;                                         //0000 0111

        // it's like divide or multiple operation
        System.out.println("6 >> 2 = " + (a5 >>  1));       //0000 0011 = 3
        System.out.println("6 << 2 = " + (a5 <<  1));       //0000 1110 = 12
    }
}