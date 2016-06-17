import static org.junit.Assert.*;

import org.junit.Test;


public class TestsCases {

	@Test
	public void test() {
	testLex();
		
		//преобразование 
		// разбор строки вручную 
		
		//fail("Not yet implemented");
	}
	@Test
	public void testLex(){
		boolean f = false;
		 String[] masslex = new String[100];
		 masslex[0] = "a";
		 masslex[1] = "|";
		 masslex[3] = "c";
		 masslex[4] = "&";
		 masslex[5] = "!";
		 masslex[6] = "b";
		 masslex[7] = "&";
		 masslex[8] = "(";
		 masslex[9] = "c";
		 masslex[10] = "&";
		 masslex[11] = "b";
		 masslex[12] = ")";
		 System.out.println("a|c&!b&(c&b)");
		 String[] masslex1 = MainClass.Lex();
		 assertTrue(masslex1.equals(masslex));
		
		//return f;
		
	}
	
	public boolean testExpr(){
		return false;
		
	}
}
