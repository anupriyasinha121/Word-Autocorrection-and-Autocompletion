import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		
		System.out.println("Enter the digit(0,1,2):");
		System.out.println("1 for word autocorrection.\n2 for word autocompletion.\n0 to exit\n");
		
		String query = null;
		
		Scanner ip = new Scanner(System.in);
		int op = ip.nextInt();
		
		switch(op){
			case 1: System.out.print("Enter query string for autocorrection : ");
					query = ip.next();
				
					LevenshteinEditDistance ed = new LevenshteinEditDistance();
					ed.editDistance1(query);
					break;
				
			case 2: File file = new File("english dictionary words"); 
					Trie t = new Trie();   
					try {
					          
					              Scanner sc = new Scanner(file);
					              while (sc.hasNextLine()) {
					                  String y = sc.nextLine();
					                  t.insert(y);
					          }
					          
					      } catch (FileNotFoundException e) {
					          e.printStackTrace();
					      }
					
					System.out.print("Enter query string for autocompletion : ");
			        
			        List<String> m = new ArrayList<>();
			        
			        query = ip.next();
			        
			        m = t.autoComplete(query.trim());
//			        System.out.println(t.search(query));
			        
					if(m==null || m.size() == 0)
					{
						System.out.printf("Sorry, No word found with prefix %s", query);
					}
					else
					{	
						System.out.printf("\nFollowing are the complete words with prefix %s : \n", query );
						
						for(String str : m)
						{
							System.out.println(str);
						}
					}
				
			default: System.out.println("Exiting");
						break;	
		}
		
	}
}

