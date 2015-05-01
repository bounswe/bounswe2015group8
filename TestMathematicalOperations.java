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
}