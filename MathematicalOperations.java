public class MathematicalOperations {

	/* The division function for numbers with double type */
	public double div(double x, double y) {
		return x / y;
	}

	/* The multiplication function for numbers with double type */
	public double multiply(double x, double y) {
		return x * y;
	}
	
	/* The square function for numbers with double type */
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

    //The power function for numbers with duoble type
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

    // The bitwise and operation
    public int bitwiseAnd(int x, int y){
        return x & y;
    }

	/* The bitwise or operation */
	public int bitwiseOr(int x, int y) {
		return x | y;
	}
     
	// This function performs the subtract operation. It returns x minus y.
    public double subtract(double x, double y){
    	return x - y;
    }
}
