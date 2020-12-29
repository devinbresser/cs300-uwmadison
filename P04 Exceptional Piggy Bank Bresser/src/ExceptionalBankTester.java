
public class ExceptionalBankTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ExceptionalBank myTestBank = new ExceptionalBank(10);
		System.out.println("testExceptionalBankConstructor: " + testExceptionalBankConstructor());
		//Coin myTestCoin = null;
		myTestBank.removeCoin();
		//System.out.println(myTestCoin.value());
		//myTestBank.addCoin(myTestCoin);
		//myTestBank.addCoin(myTestCoin);
		//myTestBank.addCoin(myTestCoin);
		//myTestBank.addCoin(myTestCoin);
		//System.out.println(myTestBank.getCoins());
		//System.out.println(myTestBank.getBalance());
		//System.out.println(myTestBank.getSummary());
		
		/*
		String time = " 4:17:23";
		String[] parts = time.trim().split(":");
		for(int i=0; i<3; i++) {
			System.out.println(parts[i]);
			
		}*/
	
		
		
	}

	/**
	* This method checks whether the ExceptionalBank constructor throws an
	* IllegalArgumentException with appropriate error message, when it is passed
	* a zero or a negative capacity. This test must fail if another kind of exception
	* is thrown for such test scenario.
	*
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	
	public static boolean testExceptionalBankConstructor() {
	try {
	// create an exceptional bank with a negative capacity
	ExceptionalBank bank = new ExceptionalBank(-10);
	System.out.println(
	"Problem detected. The constructor call of the ExceptionalBank class did not "
	+ "throw an IllegalArgumentException when it is passed a negative capacity.");
	return false; // return false if no exception has been thrown
	} catch (IllegalArgumentException e1) {
	// check that the caught IllegalArgumentException includes
	// an appropriate error message
	if (e1.getMessage() == null // your test method should not throw
	// a NullPointerException,but must return false if e1.getMessage is null
	|| !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
	System.out.println(
	"Problem detected. The IllegalArgumentException thrown by the constructor "
	+ "call of the ExceptionalBank class when it is passed a negative capacity "
	+ "does not contain an appropriate error message.");
	return false;
	}
	} catch (Exception e2) {
	// an exception other than IllegalArgumentException has been thrown
	System.out.println(
	"Problem detected. An unexpected exception has been thrown when calling the "
	+ "constructor of the ExceptionBank class with a negative argument. "
	+ "An IllegalArgumentException was expected to be thrown. "
	+ "But, it was NOT the case.");
	e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
	// constructor code.
	return false;
	}
	return true; // test passed
	}

	
	
	
}
