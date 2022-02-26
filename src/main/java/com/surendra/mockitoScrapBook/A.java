package com.surendra.mockitoScrapBook;


/**
 * We have class B which has a void method.
 * And Class A is dependent on Class B and uses the void method of class B.
 * We will learn how we stub void method using Mockito.
 */
public class A {

    private B b;

    public A(B b) {
        this.b = b;
    }

    public int usesVoidMethod() {
        try {
            b.voidMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
