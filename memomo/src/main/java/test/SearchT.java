package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Memo;

public class SearchT {
				public static void main(String[] args) {
					SearchTest ST = new SearchTest();
					List<Memo> testmemos = ST.search("2");
					
					testmemos.forEach(m -> System.out.println(m.getDaimei()
																	+ " : " +
							                                   m.getHonbun()));
					
					long mc = ST.searchcount("4");
					System.out.println(mc);
				}
			
			
	}  

class SearchTest {
	 			
	 		public List<Memo> search(String word) {
	 			List<Memo> Memos = new ArrayList<>();
	 			
	 			Memo m1 = new Memo(1,"test1","honbun1",1);
	 			Memo m2 = new Memo(1,"test2","honbun2",2);
	 			Memo m3 = new Memo(1,"test3","honbun12",3);
	 			
	 			Memos.add(m1);
	 			Memos.add(m2);
	 			Memos.add(m3);
	 			
	 			List<Memo> SearchedMemos = Memos.stream()
	 					.filter(m -> m.getDaimei().contains(word) || m.getHonbun().contains(word))
	 					.collect(Collectors.toList());
	 					
	 			return SearchedMemos;
	 			
	 		}
	 			public long searchcount(String word) {
		 			List<Memo> Memos = new ArrayList<>();
		 			
		 			Memo m1 = new Memo(1,"test1","honbun1",1);
		 			Memo m2 = new Memo(1,"test2","honbun2",2);
		 			Memo m3 = new Memo(1,"test3","honbun12",3);
		 			
		 			Memos.add(m1);
		 			Memos.add(m2);
		 			Memos.add(m3);
		 			
		 			
		 			
		 			long memocount = Memos.stream().filter(m -> m.getDaimei().contains(word) || m.getHonbun().contains(word)).count();
		 					
		 			return memocount;
	 		}
}

