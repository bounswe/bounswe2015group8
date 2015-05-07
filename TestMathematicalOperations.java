import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMathematicalOperations{
	
	/* Unit Test for double division in MathematicalOperations class */
	@Test
	public void divideByZeroShouldBeHandled(){
		MathematicalOperations mOp = new MathematicalOperations();
		
		// Test cases
		assertEquals("5.0 divided by 0.0 must be positively infinite", Double.POSITIVE_INFINITY, mOp.div(5.0, 0.0), 0.00000001);
		assertEquals("-3.0 divided by 0.0 must be negatively infinite", Double.NEGATIVE_INFINITY, mOp.div(-3.0, 0.0), 0.00000001);
		assertEquals("0.0 divided by 0.0 must be undefined (NAN)", Double.NaN, mOp.div(0.0, 0.0), 0.00000001);
	}
	
	/* Unit Test for double multiplication in MathematicalOperations class */
	@Test
	public void multiplyByZero() {
		MathematicalOperations multiplier = new MathematicalOperations();
		
		// Test cases for multiplying by zero
		assertEquals("Multiplication of -5 with 0 is 0", 0, multiplier.multiply(-5, 0), 0.00000001);
		assertEquals("Multiplication of 0 with -0 is 0", 0, multiplier.multiply(0, -0), 0.00000001);
		assertEquals("Multiplication of 2147483647 with 0 is 0", 0, multiplier.multiply(2147483647, 0), 0.00000001);
		assertEquals("Multiplication of positive infinity with 0 is undefined", Double.NaN, multiplier.multiply(Double.POSITIVE_INFINITY, 0), 0.00000001);
		assertEquals("Multiplication of negative infinity with 6541 is undefined", Double.NEGATIVE_INFINITY, multiplier.multiply(Double.NEGATIVE_INFINITY, 6541), 0.00000001);
		assertEquals("Multiplication of positive infinity with negative infinity is negative infinity", Double.NEGATIVE_INFINITY, multiplier.multiply(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY), 0.00000001);
		
		// Test multiplying basic numbers
		assertEquals("Multiplication of 5 with 6 is 30", 30, multiplier.multiply(5, 6), 0.00000001);
		assertEquals("Multiplication of 27 with 16 is 432", 432, multiplier.multiply(27, 16), 0.00000001);
	}
	
	// Unit Test for remainder in MathematicalOperations
	@Test(expected=ArithmeticException.class)
	public void remainderByZero (){

		MathematicalOperations remainder = new MathematicalOperations();

		//Test cases
		assertEquals("Remainder of -5 with 4 must be -1", -1, remainder.remainder(-5, 4), 0.00000001);
		assertEquals("Remainder of 10 with 0 must be positive infinity" , Double.POSITIVE_INFINITY, remainder.remainder(10, 0), 0.00000001);
		assertEquals("Remainder of 0 with 0 must be undefined", Double.NaN, remainder.remainder(0,0), 0.00000001);
		assertEquals("Remainder of -3 with 0 must be negative infinity", Double.NEGATIVE_INFINITY, remainder.remainder(-3, 0), 0.00000001);

	}
		
	/** Unit Test for testing the square function in MathematicalOperations class
	 * This functions tests the edge cases of positive and negative infinities,
	 * the square of zero, and the square of negative numbers (making sure they turn out positive.
	 * No overflow errors should occur with doubles.
	 */
	@Test
	public void squareTest() {
		MathematicalOperations op = new MathematicalOperations();
		// Test for infinities
		assertEquals("Square of negative infinity is positive infinity", Double.POSITIVE_INFINITY, op.square(Double.NEGATIVE_INFINITY), 0.00000001);
		assertEquals("Square of positive infinity is positive infinity", Double.POSITIVE_INFINITY, op.square(Double.POSITIVE_INFINITY), 0.00000001);
		// Test for 0
		assertEquals("Square of 0 is 0", 0, op.square(0), 0.00000001);
		// Test for negative numbers
		assertEquals("Square of -1 is 1", 1, op.square(-1), 0.00000001);

	}

	/* Unit Test for double sum in MathematicalOperations class */
	@Test
	public void addTest(){
		MathematicalOperations adder = new MathematicalOperations();
		
		// Test cases
		assertEquals("Sum of 5.0 with 0.0 is 5.0" , 5.0, adder.add(5.0, 0.0), 0.00000001);
		assertEquals("Sum of -3.0 with 0.0 is -3.0" , -3.0, adder.add(-3.0, 0.0), 0.00000001);
		assertEquals("Sum of 5.0 with 8.0 is 13.0" , 13.0, adder.add(8.0, 5.0), 0.00000001);
	}

    /* Unit Test for power operation in MathematicalOperations class */
    @Test
    public void powerTest() {
            MathematicalOperations op = new MathematicalOperations();
            // Test for 0
            assertEquals("0 to the power of any number is 0", 0, op.power(0,5), 0.00000001);
            // Test for 1
            assertEquals("1 to the power of any number is 1", 1, op.power(1,5), 0.00000001);
            // Test for power of 0
            assertEquals("Any number to the power of 0 is 1", 1, op.power(5,0), 0.00000001);       
    }

    /* Unit Test for bitwise and operation in MathematicalOperations class */
    @Test
    public void bitwiseAndTest() {
            MathematicalOperations bitwiseAndOperation = new MathematicalOperations();
			assertEquals("Bitwise and of 60 with 13 is 12.", 12, bitwiseAndOperation.bitwiseAnd(60,13),0.00000001);      
			assertEquals("Bitwise and of 72 with 184 is 8.", 8, bitwiseAndOperation.bitwiseAnd(72,184),0.00000001);      
		
    }
    
    /** Unit Test for bitwise or operation in MathematicalOperations class 
     * Contains two JUnit test cases for bitwiseOr function in MathematicalOperations.java
     */
    @Test
    public void bitwiseOrTest() {
            MathematicalOperations bitwiseOrOperation = new MathematicalOperations();
			assertEquals("Bitwise or of 10 with 15 is 15.", 15, bitwiseOrOperation.bitwiseOr(10, 15), 0.00000001);      
			assertEquals("Bitwise or of 0 with 100 is 100.", 100, bitwiseOrOperation.bitwiseOr(0, 100), 0.00000001);      
		
    }
     /* Unit Test for bitwise xor operation in MathematicalOperations class */
    @Test
    public void bitwiseXorTest() {
            MathematicalOperations bitwiseXorOperation = new MathematicalOperations();
			assertEquals("Bitwise or of 20 with 15 is 27.", 15, bitwiseXorOperation.bitwiseXor(20, 15), 0.00000001);      
			assertEquals("Bitwise or of 64 with 49 is 113.", 100, bitwiseXorOperation.bitwiseXor(64, 113), 0.00000001);      
		
    }
    /* Unit test for subtract function in MathematicalOperations.java */
    @Test
    public void subtractTest(){
    	MathematicalOperations mo = new MathematicalOperations();
    	assertEquals("", -Double.MAX_VALUE, mo.subtract(Double.MIN_VALUE, Double.MAX_VALUE), 0.00000001);
    	assertEquals("", 0.0, mo.subtract(5.000000001, 5.000000000), 0.00000001);
    	assertEquals("", 5.0, mo.subtract(12, 7), 0.00000001);
    	assertEquals("", -12.0, mo.subtract(24, 36), 0.00000001);
    }
    
    /* Unit Test for inverse division in MathematicalOperations class */
	@Test
	public void divideByInverseZero(){
		MathematicalOperations inv = new MathematicalOperations();
		
		// Test cases
		assertEquals("7.0 divided by 0.0 must be positively infinite", Double.POSITIVE_INFINITY, inv.invDiv(0.0, 7.0), 0.00000001);
		assertEquals("-4.0 divided by 0.0 must be negatively infinite", Double.NEGATIVE_INFINITY, inv.invDiv(0.0, -4.0), 0.00000001);
		assertEquals("0.0 divided by 0.0 must be undefined (NAN)", Double.NaN, inv.invDiv(0.0, 0.0), 0.00000001);
	}
}
