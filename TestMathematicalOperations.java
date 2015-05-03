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
	@Test
	public void remainderByZero (){

		MathematicalOperations remainder = new MathematicalOperations();

		//Test cases
		assertEquals("Remainder of -5 with 4 must be -1", -1, remainder.remainder(-5, 4), 0.00000001);
		assertEquals("Remainder of 10 with 0 must be positive infinity" , Double.POSITIVE_INFINITY, remainder.remainder(10, 0), 0.00000001);
		assertEquals("Remainder of 0 with 0 must be undefined", Double.NaN, remainder.remainder(0,0), 0.00000001);
		assertEquals("Remainder of -3 with 0 must be negative infinity", Double.NEGATIVE_INFINITY, remainder.remainder(-3, 0), 0.00000001);
	}
}