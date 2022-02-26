package mockitoScrapBook;

import com.surendra.mockitoScrapBook.A;
import com.surendra.mockitoScrapBook.B;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ATest {
    @Mock
    private B b;
    private A a;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }
    @Test
    public void usesVoidMethodShouldCalledTheVoidMethod() throws Exception {
        /**
         *
         * Stubbing out the class will stub out all the void methods and there is nothing
         * we need to do.
         */
        assertSame(1,a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test
    public void explicitlyStubbedVoidMethod() throws Exception {
        /**
         *
         * Explicitly stubbed the void method if we need to use it in some cases.
         * For explicitly mock out, we will use doNothing() available in mockito API
         */
        doNothing().when(b).voidMethod(); //doNothing when b.voidMethod() is called.
        assertSame(1,a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void usesVoidMethodShouldThrownRuntimeException() throws Exception {
        doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void testConsecutiveCalls() throws Exception {
        /**
         * Mockito supports consecutive calls i.e. we can stub out a chain of method.
         * If b.voidMethod() called more than one time, we can tell mockito what should be
         * done the first time and what should be done the second time and so on.
         *
         * This is where doNothing() is very useful.
         *
         * we will call usesVoidMethod multiple times and, we will see different output each times.
         */
        //When 'when' method on b called for first time, it should do nothing and when it called
        //the second time, it will throw an exception.
        doNothing().doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
        verify(b).voidMethod();
        a.usesVoidMethod();
    }
}
