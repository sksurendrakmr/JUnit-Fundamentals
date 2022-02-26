package com.surendra.mokito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class ListTest {
    /**
     * Partially mock this list.
     * All the real methods will be called if we don't stub them out.
     */
    @Spy
    List<String> myList = new ArrayList<>();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){
        //calling the real method
        myList.add("sk");
        myList.add("nk");

        //stub out - with spy we can't use when as it called the actual method. We can use doReturn instead.
        Mockito.doReturn(3).when(myList).size(); //return 3 when myList.size is called.
        assertSame(3,myList.size());

        when(myList.size()).thenCallRealMethod(); //recommended if we need to call real methods.

    }
}
