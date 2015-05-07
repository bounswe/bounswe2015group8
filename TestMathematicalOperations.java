import org.junit.Test;
import static org.junit.Assert.assertEquals;


/*! The JUnit Testing class for the operations in MathematicalOperations.java */
public class TestMathematicalOperations{
	
	/*! \brief Unit Test function for double division in MathematicalOperations class
	 * 
	 * Tests the edge cases where the divider is zero.
	 * In Java, dividing by zero for doubles does not throw an exception unlike it does for integers.
	 * Instead, it returns positive infinity if the dividend is positive.
	 * If the dividend is negative, then the result is negative infinity.
	 * Dividing zero by zero does not throw an exception either, for the double types. It returns NaN.
	 * Hence, there should not be any exception for those cases.  
	 *  */
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

    /** Unit Test for power operation in MathematicalOperations class
	 * This functions tests, any number to the power of zero,
     * 1 to the power of any number, 0 to the power of any number.
	 * No overflow errors should occur with doubles.
	 */
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

	/** Unit Test for bitwise and operation in MathematicalOperations class 
	* Contains two JUnit test cases for bitwiseAnd function in MathematicalOperations.java
	*/
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
    	
     	/** Unit Test for bitwise xor operation in MathematicalOperations class
     	* Contains two JUnit test cases for bitwiseXor function in MathematicalOperations.java
	*/
    	@Test
    	public void bitwiseXorTest() {
            	MathematicalOperations bitwiseXorOperation = new MathematicalOperations();
		assertEquals("Bitwise or of 20 with 15 is 27.", 27, bitwiseXorOperation.bitwiseXor(20, 15), 0.00000001);      
		assertEquals("Bitwise or of 64 with 49 is 113.", 113, bitwiseXorOperation.bitwiseXor(64, 49), 0.00000001);      
	}
    
	/*! \brief Unit Test function for double subtraction in MathematicalOperations class
	 * 
	 * Tests the edge case where the difference is out of double boundaries.
	 * Tests the edge case where the difference is less than tolerance.
	 * Tests some regular cases with positive and negative results.
	 *  */
	@Test
	public void subtractTest(){
		MathematicalOperations mo = new MathematicalOperations();
		assertEquals("Double.MIN_VALUE minus Double.MAX_VALUE must be negative Double.MAX_VALUE", -Double.MAX_VALUE, mo.subtract(Double.MIN_VALUE, Double.MAX_VALUE), 0.00000001);
		assertEquals("5.000000001 minus 5.000000000 must be zero", 0.0, mo.subtract(5.000000001, 5.000000000), 0.00000001);
		assertEquals("12 minus 7 must be 5.0", 5.0, mo.subtract(12, 7), 0.00000001);
		assertEquals("24 minus 36 must be -12.0", -12.0, mo.subtract(24, 36), 0.00000001);
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
