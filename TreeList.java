
/**
 * Класс описывающий структуру листа ДГР
 * @author Natalia
 * 
 *
 */

public class TreeList implements Comparable<TreeList> {
	int id, pid;
	int left,right;
	String data;
	int type;
	boolean isNull;
	boolean brac;
	boolean value;


	//type=-1-нет типа
	//type=1 - дизъюнкция 
	//type=2 - конъюнкция
	//type=3 - отрицание
	//type=4 - переменная
	
	public TreeList(int id, int pid) {
		this.id = id;
		this.pid = pid;
		left = -1;
		right = -1;
		type = -1;
		data = " ";
		isNull = false;
		brac = false;
		value = false;
	}
	
	public  void GetData(TreeList p){
		
		if (p.type == 1){
			p.data="&";
		}
		else{
			if(p.type == 2){
				p.data="|";
			}
			else{
				if(p.type==3){
					if((p.right!=-1)&(p.left!=-1)){
						p.data="!";
					}
				}
			}
		}
	}
	
	public void SetData(){
		
	}
	
	public int GetID(){
		return id;
		
	}

	@Override
	public int compareTo(TreeList o) {
		// TODO Auto-generated method stub
	    if(this.id > o.id)
	    	return 1;
	    else 
	    	if(this.id < o.id)
	    		return -1;
	    	else
	    		return 0;
	 }
}
