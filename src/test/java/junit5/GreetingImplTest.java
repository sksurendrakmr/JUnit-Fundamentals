package junit5;

import com.surendra.junit5.Greeting;
import com.surendra.junit5.GreetingImpl;
import com.surendra.junit5.service.GreetingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //take care of mocking and running our test
public class GreetingImplTest {
    @Mock
    private GreetingService service; //we need to inject this service in GreetingImpl class.

    /**
     * InjectMocks will create an object/instance of GreetingImpl.
     * When it does that it will look at that implementation, and it knows that it has this dependency (GreetingService)
     */
    @InjectMocks
    private GreetingImpl greeting;



    @Test
    public void shouldGiveValidGreet(){
        when(service.greet("Junit")).thenReturn("Hello Junit");
        String result = greeting.greet("Junit");
        Assertions.assertEquals("Hello Junit", result);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsBlank(){
        doThrow(IllegalArgumentException.class).when(service).greet("");
        Assertions.assertThrows(IllegalArgumentException.class,()->{
        greeting.greet("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull(){
        doThrow(IllegalArgumentException.class).when(service).greet(null);
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            greeting.greet(null);
        });
    }
}
