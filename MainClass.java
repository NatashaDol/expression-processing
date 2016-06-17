import java.lang.System.*;
import java.util.*;
import java.io.*;
import  java.lang.Math;

import javax.swing.JOptionPane;
import javax.swing.Spring;

/**
 * Основной класс программной бибиотеки, из него осуществляется запуск 
 * всех основных функций
 * @author Natalia
 *
 */

public class MainClass {

	private static final int size = 3;

	public static void main(String[] args) {
	
		//generateMap(3);
		//System.out.println(generateMap(3).toString());	
		//int size=4;
		TreeMap<String, Boolean> map = generateMap(size);
		//test1();
		stroka=generator(5, 40,"");
		System.out.println(stroka);
		//System.out.println(stroka);
		
		Lex();
		perem = new String[100];
		currentlex=masslex[0];
		TreeList a = new TreeList(id++, -1);
		tree.add(a);
		expr(a);
		//test1();
		
		tree.printTree();
		tree.turnTree();
		tree.reduce();
		//tree.mutation(massS.length, massS);
		Tree tree2 = new Tree();
		tree2.printTree();
		//TreeList b = new TreeList(0, -1);
		tree2.add(tree.getFirst(),tree2);
		tree2=tree.clone(tree.getFirst(), tree2);
		tree2.printTree();
		System.out.println("++");
		//System.out.println(tree2.treeToStr(tree2.getFirst()));
		//tree.delTree(tree.getFirst());
		//System.out.println(tree2.treeToStr(tree2.getFirst()));
		tree.reduce();
		//tree.printTree();
		Ss2();
		
		//System.out.println(tree.treeToStr(tree.getFirst()));
		System.out.println(generateZn(map).toString());
		System.out.println("Value: " + tree.getValue(map));
		System.out.println(proverka(stroka, 1000, map));
		
		//tree.delTree(tree.getFirst());
		//tree.mutation(massS.length, massS);
		//System.out.println(tree.treeToStr(tree.getFirst()));
		
		
	}
	
	
	static int indexlex=0, id = 0;
	static String[] masslex;
	static String[] perem;
	static Tree tree = new Tree();
	static String currentlex;
	static java.util.Random generator = new java.util.Random();
	static byte[] temp = new byte[40];
	
	
private static Random rnd = new Random();

/**
 * Метод для генерации имен переменных в выражении. 
 * 
 * @param names - структура задющая колличество генерируемых переменных 
 * @return name - имя сгенерированной переменной
 */
public static String generateName(TreeSet<String> names) {
		
		int x = (int) Math.pow(26, Math.floor(Math.log(((names.size() + 1) * 1.2)) / Math.log(26)) + 1);
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String name;
		do {
			int y = rnd.nextInt(x) + 1;
			int cnt = 0;
			while(y > 0) {
				y = y / 26;
				cnt++;
			}
			name = "";
			for(int i = 0; i < cnt; i++) 
				name = name + letters[rnd.nextInt(26)];
		}
		while(names.contains(name));
		return name;
	}
	
	static String stroka="";
	static String[] massS = new String[size];
	
	/**
	 * Метод генерирующий карту - переменная-значение переменной
	 * @param size - количество переменных
	 * @return map
	 */
	public static TreeMap<String, Boolean> generateMap(int size) {
		
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		TreeSet<String> names = new TreeSet<String>();
		for(int i = 0; i < size; i++) {
			String name = generateName(names);
			names.add(name);
			String namesim = generateSimbol();
			if(i < size-1){
			stroka=stroka+name+" "+namesim;
			} else { stroka=stroka+name;}
			map.put(name, rnd.nextBoolean());
			massS[i]=name;			
		}
		return map;
	}
	
	/**
	 * Метод перевода карты в массив
	 * @param map
	 * @return str
	 */
	
	public static String[] mapToMass(TreeMap<String, Boolean> map){
		String[] str = null;
		
		
		return str;
		
	}
	
	
	
	public static String generator(int glubina, int c, String str){

		int n = rnd.nextInt(6);
		String str1="";
		
		for(int i=0;i<=n;i++){
			//отрицание
			int k = rnd.nextInt(2);
			if (k==0){str1=str1+"!";}
			k=rnd.nextInt(2);
			//переменная или выражение
			if (glubina>1){
			if (k==0){str1=str1+massS[rnd.nextInt(size)];}
			else{ 
				
				glubina=glubina-1;
				str1=str1 + "("+ generator(glubina,n-i,"")+ ")";} 
			} 
			else{str1=str1+massS[rnd.nextInt(size)];};
			
			//знак
			if (i!=n){
				str1= str1+generateSimbol();
			}
			
		}
				return str1;
		
	}
	
	/**
	 * Метод генерирует значение переменной
	 * @param a
	 * @return val 
	 */
	
	public static boolean[] getZn(int a){
		boolean[] val = new boolean[10];
		int b;
		for (int i=0; i<size; i++){
				if (a%2==1){
				val[i]=true;
				a=a/2;
			}	else {val[i]=false; a=a/2; }
		}
		
		
		return val;
		
	}
	
	
	/**
	 * метод генерирующий карту значений переменных
	 * @param map
	 * @return map
	 */
	
	public static TreeMap<String, Boolean>  generateZn(TreeMap<String, Boolean> map){
		int x = rnd.nextInt(7);
		boolean[] val = getZn(x);
		for (int i=0; i<size ;i++){
		
			map.put(massS[i],val[i] );
		}
		return map;
		
	}
	
	
	/**
	 * Метод проверяющий количество обращений выражения в истину
	 * @param str - выражение
	 * @param p - число проверок
	 * @param map - карта переменных
	 * @return proc - процент обращения в истину
	 */
	public static int proverka(String str, int p, TreeMap<String, Boolean> map){
		int proc=0;
		
		for(i=0; i<p; i++){
		generateZn(map);
		 if (tree.getValue(map)){proc=proc+1;};
		}
		return proc;
		
	}
	
	
	/**
	 * Метод генерирующий знак операции
	 * @return name
	 */

	public static String generateSimbol() {
		
		String[] letters = {"&","|"};
		String name;
			int y = rnd.nextInt(1);
			int d = rnd.nextInt(1);
			int cnt = 0;
			
			name = letters[(int)(Math.random()*2)] ;
			
		
		return name;
	}
	
	
/**
 * Первый метод лексического разбора выражения
 * @param c - лист дерева
 */
	
		static void expr(TreeList c){
		boolean first = true;
		do{
			if(!first) {
				if(!GetNext()){
					//
				}
				TreeList p = new TreeList(id++, c.id);
				c.right = p.id;
				tree.add(p);
				c = p;
			}
			c.type = 1;//;
			
			TreeList p = new TreeList(id++, c.id);
			c.left = p.id;
			tree.add(p);
			prod(p);
			first = false;
		}while(currentlex!=null && currentlex.equals("|")); 
	}
	
	static void prod(TreeList p){
		boolean first = true;
		do{
			if(!first) {
				GetNext();
				TreeList c = new TreeList(id++, p.id);
				p.right = c.id;
				tree.add(c);
			
				p=c;
			}
			p.type=2;
			
			TreeList d = new TreeList(id++, p.id);
			p.left = d.id;
			tree.add(d);
			neg(d);
			first = false;
		}while(currentlex!=null && currentlex.equals("&"));
	}
	
	static void neg(TreeList p) {
		if (negativ(currentlex)){
			TreeList c = new TreeList(id++, p.id);
			p.left=c.id;
			p.type=3;
			tree.add(c);
			p=c;
			GetNext();
			op(p);
			return;}
		else { op(p);}	
		}
		
	
	
	static void op(TreeList p){
		if (currentlex.equals("(")){
			p.brac=true;
			GetNext();

			expr(p);
			GetNext();
		}
		else{
		if(world(currentlex)){
			p.type=4;
			p.data=currentlex;
			GetNext();
			return;
		}
		else {System.out.print("error");}	
			}
		}
	
		
	
	static int n=0;
	
	static boolean world(String wrld){
	
		if (wrld.matches("^[a-z]+")) {
			
		return true ;}
			
		else return false;			
}
	
	static boolean negativ(String ngt) {
		if (ngt.equals("!")) 
			return true ;
		else return false;
	}
	
	static boolean prodact(String ngt) {
		if (ngt.equals("&")) 
			return true ;
		else return false;
	}
	
	
	
	static boolean GetNext(){
		indexlex = indexlex+1 ;
		if(indexlex < masslex.length) {
			currentlex = masslex[indexlex];
			return true;
		}
		else {
			currentlex = null;
			return false;
		}
	}
	
/** 
 * Метод считывающий стороку, в которой находится выражение	
 */
	
	static void ReadLex(){
		Scanner scan = new Scanner(System.in);
		String lx = stroka;
		masslex = lx.split("[\\s*]");	
		
	}
	
	
	static int i=0,k=0;
	static int stay;
	static String s="";
	
	
	/**
	 * Метод считывающий лексему и заносящий ее в массив
	 * @return masslex -массив лексем 
	 */
	
	static String[] Lex(){
		Scanner scan = new Scanner(System.in);
		String lx = stroka;
	    masslex = new String[100];
	    k=0;
		while (i <  lx.length()){
			
		
		if (lx.charAt(i)==' '){
			if (stay==2){
				masslex[k]=s;
				k++;
				s="";
			}
					
			stay=0;
		}
		if (lx.charAt(i)=='(' || lx.charAt(i)==')' || lx.charAt(i)=='|' || lx.charAt(i)=='&'|| lx.charAt(i)=='!' ){
			if (stay==2){
				masslex[k]=s;
				s="";
			k++;
			}
			masslex[k]="" +lx.charAt(i);
			stay=1; 
			
			k++;
			
		}
		if (world(""+lx.charAt(i))){
			s=s+lx.charAt(i);
			stay=2;
			
		}
		i++;
		}
		if (stay==2){
			masslex[k]=s;
			k++;
			s="";
		}
		return masslex;
	}
	
	/**
	 * Метод приведения выражения к СКНФ или СДНФ
	 */
	
	static void Ss2(){
		boolean kon = false; // параметр конъюнкция или дизъюнкция
		StringBuilder sknf=new StringBuilder(""), sknf1= new StringBuilder("");
		String skn;
		String[] massp = tree.getN();
		int n= massp.length,x;
		TreeMap<String, Boolean> tm = new TreeMap<String, Boolean>();
		boolean f2 = true;
		for (int i=0; i<Math.pow(2,n); i++){
			tm.clear();
			x=i;
			for (int j=0; j < n; j++ ){ //для двух переменных проверяет только 0 и 3
				if (x%2==0){
					tm.put(massp[j], true);
				} else {
					tm.put(massp[j], false);
				}
				x=x/2;
				
			}
			
			if (kon){
			if(tree.getValue(tm)){

				boolean f1 = true;
				sknf1 = new StringBuilder("");
				for(String y: tm.keySet()) {
					
					if(tm.get(y)){
						if(f1){ sknf1.append( y); f1=false; }
						else {
						sknf1.append(" & " + y); }
					} else{
						if(f1){ sknf1.append(" ! " + y); f1=false; }
						else {
						sknf1.append(" & " + "!" + y);}
						
					}
				}
				if (f2){sknf.append(sknf1.toString()); f2=false;}
				else {sknf.append(" | " + sknf1.toString());}
			}
				
			}
			else {
				if(!tree.getValue(tm)){

					boolean f1 = true;
					sknf1 = new StringBuilder("");
					for(String y: tm.keySet()) {
						
						if(tm.get(y)){
							if(f1){ sknf1.append( y); f1=false; }
							else {
							sknf1.append(" | " + y); }
						} else{
							if(f1){ sknf1.append(" ! " + y); f1=false; }
							else {
							sknf1.append(" | " + "!" + y);}
							
						}
					}
					if (f2){sknf.append("(" + sknf1.toString()+ ")"); f2=false;}
					else {sknf.append(" & " + "(" + sknf1.toString() + ")");}
				}
				
			}
			}
			System.out.println(sknf);
		}

	
	public static void tests(){
		//сгенерировать крайние примеры 
			// запустить все функции
			//сгенерировать очень длинные выражения 
			// запустить функции 
			// через ген алгоритмы сгенерировать много выражений и проверить их
			
		}
	
	/**тест для проверки считывания и распознавания лексем
	 * 
	 **/
	static void test1()	{
		System.out.println(" ++++++");
		TreeMap<String, Boolean> map = generateMap(size);
		String stroka1=generator(7, 40,"");
		System.out.println(stroka1);	
		Lex();
		TreeList a = new TreeList(id++, -1);
		tree.add(a);
		expr(a);
		tree.printTree();
		System.out.println(tree.treeToStr(tree.getFirst()));
		String stroka2 = tree.treeToStr(tree.getFirst());
		if (stroka1.equals(stroka2)){
			System.out.println("true");
		}
		else{
			System.out.println("false");
		}
		//return false;	
	}

	/**тест для проверки удаления поддерева
	 * 
	 **/
	static void test2(){
		
	}
}
	
	



