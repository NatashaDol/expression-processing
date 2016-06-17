import java.sql.Struct;
import java.util.*;

import javax.swing.Spring;
import javax.swing.text.html.HTMLDocument;
import genetics.*;

/**
 * 
 */

/**
 * ����� ����������� ��������� ���
 * @author Natalia 
 *
 */
public class Tree {
	
	private TreeSet <TreeList> tree = new TreeSet<TreeList>();
	private TreeMap<String, Boolean> values;
	//private static TreeSet <TreeList> newtree = new TreeSet<TreeList>();
	
	public void add(TreeList l){
		tree.add(l);
	}
	
	public void add(TreeList l, Tree newtree){
		newtree.add(l);
	}
	
	public void CreateList(){

	}
	
	public void printTree() {
		for(TreeList x: tree) {
			System.out.println("id=" + x.id + " pid=" + x.pid + " left=" + x.left + " right=" + x.right + " type=" + x.type + " data=" + x.data + "brac=" + x.brac);
		}
	}

	/**
	 * �����, ��������� �� ��� ������ �������
	 */
	public  void turnTree(){
		
		for(TreeList x: tree){
		
			if (x.type==1 || x.type==2){
				if(x.right==-1 ){
				if(x.pid==-1){
				TreeList tmp2 = new TreeList(x.left, 0);
					tmp2 = tree.floor(tmp2);
					tmp2.pid = -1;
					if(!tmp2.brac){tmp2.brac = x.brac;};
					x.isNull = true;
				}
					
				TreeList tmp1 = new TreeList(x.pid, 0);
				tmp1 = tree.floor(tmp1);
				if(tmp1 != null){
				TreeList tmp2 = new TreeList(x.left, 0);
				tmp2 = tree.floor(tmp2);
				if(tmp1.left==x.id){
					tmp1.left = tmp2.id;
				}
				else{
					tmp1.right = tmp2.id;
				}
				tmp2.pid = tmp1.id;
				if(!tmp2.brac){tmp2.brac = x.brac;};
				x.isNull = true;
				
				
				}
			}
		}
		}
	}
	
	public void GetValue(){
		
		
	}
	
	public void reduce() {
		
		TreeList[] tl = new TreeList[1];
		tl = tree.toArray(tl);
		tree.clear();
		for(TreeList x: tl) {
			if(!x.isNull)
				tree.add(x);
		}
	}
	
	/**
	 * ����� ������������ �������� ��������� 
	 * @param a - ������� ����
	 * @return �������� ������� ������������ � ����� �
	 */
	
	private boolean getValue(TreeList a){
		//���� ������� - �������� "�", "���"
		if (a.type==1 || a.type ==2){
			//��������� ��������� ������ ����� 
			//����� ����� ����������� ������ �����
			TreeList tmp1 = new TreeList(a.right, 0);
			tmp1 = tree.floor(tmp1);
			//����� ����� ����������� ����� �����
			TreeList tmp2 = new TreeList(a.left, 0);
			tmp2 = tree.floor(tmp2);
			//���������� �������� ���������
			return a.type == 1 ? getValue(tmp1) | getValue(tmp2) : getValue(tmp1) & getValue(tmp2);
			
		}
		else
			//���� �������� - ���������
			if (a.type==3){
			
				TreeList tmp1 = new TreeList(a.left, 0);
				tmp1 = tree.floor(tmp1);
				//���������� �������� �������� 
				return !getValue(tmp1);
			}
			else
			//���������� �������� ���������� 
				return values.get(a.data);
	
	}
			
	public boolean getValue(TreeMap<String, Boolean> values) {
		this.values = values;
		return getValue(getFirst());
	}
	
	public void podschtet(){
		
	}
	
	/**
	 * ����� ������������� ��� � ������
	 * @param c - ������� ����
	 * @return tmp - ���������� ������
	 */
	
	public String treeToStr(TreeList c) {
		String tmp = "";
		TreeList tmp1 = null, tmp2 = null;
		System.out.println(c.id);
		if(c.type == 1 || c.type == 2) {
			tmp1 = new TreeList(c.left, 0);
			tmp1 = tree.floor(tmp1);
			tmp2 = new TreeList(c.right, 0);
			tmp2 = tree.floor(tmp2);
			tmp = c.type == 1 ? treeToStr(tmp1) + " | " + treeToStr(tmp2) : treeToStr(tmp1) + " & " + treeToStr(tmp2);
		}
		if(c.type == 3) {
			tmp1 = new TreeList(c.left, 0);
			tmp1 = tree.floor(tmp1);
			tmp = "!" + treeToStr(tmp1);
		}
		if(c.type == 4)
			tmp = c.data;
		if(c.brac)
			tmp = "(" + tmp + ")";
		return tmp;
		
	}
	
	
	
		
	public  String[] getN(){
		int n=0;
		TreeSet<String> ts = new TreeSet<String>();
		for(TreeList x: tree){
		
			if (x.type==4){
				ts.add(x.data);
				
			}
		}
		
		String[] massp = ts.toArray(new String[ts.size()]);

	
		return massp;
	}
	
	public TreeList getFirst(){
		return (tree.first());
	}
	
	//�������� ���������
	public void delTree(TreeList k){
		
		if( k.right!=-1){
			TreeList tmp1 = new TreeList(k.right, 0);
			tmp1 = tree.floor(tmp1);
			delTree(tmp1); // ���������� � ��������� ��������
		}
		
		if( k.left!=-1){
			TreeList tmp1 = new TreeList(k.left, 0);
			tmp1 = tree.floor(tmp1);
			delTree(tmp1); // ���������� � ��������� ��������
		}
		
		TreeList tmp2 = new TreeList(k.pid, 0);
		if(tmp2.left==k.id){
			tmp2.left=-1;
		}
		else{tmp2.right=-1;}
		tree.remove(k);
		
		
	}
	
	public Tree clone(TreeList a, Tree newtree){
		//�� �������� ���������� � ������
		newtree.add(a, newtree);
		if( a.right!=-1){
			TreeList tmp1 = new TreeList(a.right, 0);
			tmp1 = tree.floor(tmp1);
			//�������� ������ ������� ������� � newtree
			clone(tmp1,newtree); // ���������� � ���������� ��-�� � ������
		}
		
			if( a.left!=-1){
				TreeList tmp1 = new TreeList(a.left, 0);
				tmp1 = tree.floor(tmp1);
				clone(tmp1,newtree); // ���������� � ��������� �������� � ���������� ��-�� � ������
			}		
		
		return newtree;
		
	}
	
	static Tree tree1 = new Tree();
	private static Random rnd = new Random();
	
	public Tree changeTree(Tree treea, Tree treeb, int lengtha, int lengthb){
		//treea.getFirst()=treeb.getFirst();
		int i = rnd.nextInt(lengtha);
		int j = rnd.nextInt(lengthb);
		for(TreeList x: tree){
			if (x.id==i){
				for(TreeList y: tree){
					if (y.id==j){
						Tree tree1 = new Tree();
						tree1=clone(x,tree1);//����������� ���������
						treea.delTree(x);
						treea=clone(y,treeb);
						//������������  ������ ���� � ����������� treea � treeb.  
						treeb.delTree(y);
						treeb=clone(x,tree1);
						
						//���������� tree1
						
						// ������� �������
						//������������ 
						//�������� �������
					}
				}
			}
			
		}
		//����� ������ �������
		//����� � ������ ������ �� �� ����� 
		return tree1;
	}
	
	
	public void mutation(int lengtha, String[] massp){
		//String[] letters = {"&","|"};
		int i = rnd.nextInt(lengtha);
		for(TreeList x: tree){
			if (x.id==i){
				if (x.type==4){
						x.data = massp[i];//(int)(Math.random()*2)] ;
				}
				if (x.type==1| x.type==2){
					int j = rnd.nextInt(1);
					if (j==1){
						x.type= 1; x.data="&";
					}
					else {
						x.type= 2; x.data="|";
					}
			}
		}
		}
		
	}
	
	
	
	
}
