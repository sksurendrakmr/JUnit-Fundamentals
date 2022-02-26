package com.surendra.parameterized;


import com.surendra.calculator.CalculatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorImplTest {

    //1.Creating fields - Identify inputs and results
    private int num1;
    private int num2;
    private int expectedResult;

    //2. Create constructor
    public CalculatorImplTest(int num1, int num2, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.expectedResult = result;
    }

    /**3. Create a static method which will feed in data required for our test and will mark it @Parameter
     * This method should return collection of dataset
     */
    @Parameters
    public static Collection<Integer[]> data(){
        return Arrays.asList(new Integer[][]{{-1,2,1},{1,2,3},{6,7,13}});
    }

    /**
     * 4. Create and update the test
     */
    @Test
    public void test(){
        CalculatorImpl calculator = new CalculatorImpl();
        int result = calculator.add(num1, num2);
        assertEquals(expectedResult,result);
    }


}
