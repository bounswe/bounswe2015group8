public class MathematicalOperations {

	/*! \brief The division operation for double-typed numbers
	 * 
	 * 
	 * Takes two doubles and returns the division of the first number by the second number.
	 * If the second number is between -1 and 1, it can also be used as multiplication.
	 * @param x: the first number (dividend)
	 * @param y: the second number (divider)
	 * @return the division of the given inputs
	 */
	public double div(double x, double y) {
		return x / y;
	}

	/*! \brief The multiplication operation for double-typed numbers
	 * 
	 * 
	 * Takes two doubles and returns the multiplication of the first number by the second number.
	 * If the second number is between -1 and 1, it can also be used as division.
	 * @param x: the first number (multiplicand)
	 * @param y: the second number (multiplier)
	 * @return the multiplication of the given inputs
	 */
	public double multiply(double x, double y) {
		return x * y;
	}
	
	/** The square function for numbers with double type
	 * Will be used for quickly generating the square of any given double.
	 * Is faster than the generalized power function.
	 * @param num the double to be squared
	 * @return square of the given input
	 */
	public double square(double num) {
		return num * num;
	}

	//The remainder function for number withh int type
	public int remainder (int x, int y){
		return x % y;
	}

	/* The add function for numbers with double type */
	public double add(double x, double y){
		return x+y;		
	}
    
	/** The power function for numbers with double type
	 * It is used for generating x to the power of n.
	 * @param x the base number with double type.
     * @param n the exponent number with integer type.
	 * @return result is x to the power of n.
	 */
	public double power(double x, int n){
		if(n==0)
			return 1;
		
		if(n<0){
			x = 1.0/x;
			n = -n;
		}
		double result = power(x,n/2);
		result = result * result;
		if(n%2!=0)
			result = result * x;
		return result;
	}

	/** The bitwise and operation 
	 * Generates the result of bitwise and operation on integers.
	 * @param x the first integer for the operation.
	 * @param y the second integer for the operation.
	 * @return the result of bitwise and operation on x and y.
	 */	public int bitwiseAnd(int x, int y){
    		return x & y;
   	}

	/** The bitwise or operation 
	 * Generates the result of bitwise or operation on integers.
	 * @param x the first integer for the operation.
	 * @param y the second integer for the operation.
	 * @return the result of bitwise or operation on x and y.
	 */
	public int bitwiseOr(int x, int y) {
		return x | y;
	}
	/** The bitwise xor operation 
	 * Generates the result of bitwise xor operation on integers.
	 * @param x the first integer for the operation.
	 * @param y the second integer for the operation.
	 * @return the result of bitwise xor operation on x and y.
	 */
	public int bitwiseXor(int x, int y){
		return x ^ y;
	}
     
	/*! \brief The subtraction operation for double-typed numbers
	 * 
	 * 
	 * Takes two doubles and returns the subtraction of the first number by the second number.
	 * @param x: the first number
	 * @param y: the second number
	 * @return the difference of the given inputs
	 */
	public double subtract(double x, double y){
    		return x - y;
    	}
    
	/** The inverse division function for doubles 
	 * Takes two doubles and returns the division of the second number by the first number.
	 * If the first number is between -1 and 1, it can also be used as multiplication.
	 * @param x: the first number (divider)
	 * @param y: the second number (dividend)
	 * @return the division of the given inputs
	 */
	public double invDiv(double x, double y) {
		return y / x;
	}

}
