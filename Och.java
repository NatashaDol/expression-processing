
public class Och {
	int id;
	int child;
	


	//type=-1-нет типа
	//type=1 - дизъюнкция 
	//type=2 - конъюнкция
	//type=3 - отрицание
	//type=4 - переменная
	
	public Och(int id, int ch) {
		this.id = id;
		this.child = ch;
		
	}
	
}
