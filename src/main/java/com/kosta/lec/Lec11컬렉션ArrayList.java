package com.kosta.lec;

/** 
int	   단일      타입
int[]  다중(같은) 타입

ArraryList = 중복O, 순서보장O

배열(Array)
	- (같은 타입) 여러 데이터를 하나의 변수로 저장 가능
	- 메모리 연속적으로 할당
	- 반드시 (정확한 갯수)를 지정해서 메모리에 생성 후 사용
	  int[] age = new int[5]; //정확한 갯수 지정

컬렉션
	- (정해진 크기 없이) 여러개의 데이터를 저장
	- (다른 타입) : 다양하게 여러 데이터 담기 가능
	- [I] java.util.Collection  ,  java.lang.Iterable<T>  ---- Map<K,V>
        -- [I]  List<E>                Set<E>          Queue 
        -- [C]  ArrayList  Vector      HashSet   

public interface Set<E> extends Iterable<E>, Collection<E>{
      public abstract boolean add(E e);
}
-----------------------------------------------------------
java.lang.Object
  java.util.AbstractCollection<E>
    java.util.AbstractSet<E>
      java.util.HashSet<E>
public class HashSet  extends AbstractSet<E> implements Set<E>{
     @Override
     public boolean add(E e) {   }
} 

-----------------------------------------------------------
import java.util.HashSet;
public class {
    ... main() ... {
         HashSet hs = new HashSet();           //생성자(Constructors)
         HashSet hs = new HashSet(16);   
         HashSet hs = new HashSet(16, 0.75);   //Constructors
         HashSet hs = new HashSet(Collection); //--->캐스팅
    }
}
-----------------------------------------------------------
*/

import java.util.ArrayList;

public class Lec11컬렉션ArrayList {

	public static void main(String[] args) {
		//create table list ( );
		ArrayList list = new ArrayList();
		
		// C insert into list values("abc");
		// C insert into list values("10");
		list.add("abc");
		list.add(10);      
		list.add('A');
		
		System.out.println(list.get(1).getClass());  //o instanceof UserClass
		
		// R select count(1) from list;
		System.out.println("list.size():" + list.size());
		
		System.out.println(list);  //  [abc, 10, A, def] 
		//--------------- 컬렉션 ArrayList 사용 ------------------
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		
		// U update list set col='zzz' where seq=0; 
		list.add(3, "def");  //3번째 def 추가	
		list.set(0, "zzz");  //0번째 zzz 수정
		System.out.println("3추가def  0수정zzz:" +list);  //  [zzz, 10, A, def]
		
		// D
//		list.remove(1);            //인덱스 1번째 삭제
//		list.remove("10");   	   //"10"은 없어서 삭제안됨 ,  정수값10으로 삭제
		list.remove(Integer.valueOf(10));
		System.out.println("remove(Integer.valueOf(10)):" + list);
		
		//추가기능
		int idx = list.indexOf("def");
		System.out.println("def index값:" + idx);
		
		
//		--------------- 배열 String[] 사용 ------------------
//		String[] strArr = new String[] {"abc","10","A","def"};
//		for(int i=0; i<strArr.length; i++) {
//			System.out.print(strArr[i] + " ");
//		} 
	}

}
