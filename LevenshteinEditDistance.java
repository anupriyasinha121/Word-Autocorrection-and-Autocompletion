import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevenshteinEditDistance {
	
		static ArrayList<String> dict[] = new ArrayList[26];
		
		LevenshteinEditDistance () {
			for (int i = 0; i < 26; i++) { 
	            dict[i] = new ArrayList<String>(); 
	        }
		}
	
	public static void editDistance1(String x) {
		
		
		
		File file = new File("english dictionary words"); 
		try {
			
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine()) {
				
				String y = sc.nextLine(); 
//				System.out.println(y);
//				System.out.println((int)y.charAt(0)-97);
				dict[(int)y.charAt(0)-97].add(y);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
			float start = System.nanoTime(), end;
			int xlen = x.length(), ylen;
			int l=0;
			
			ArrayList<String> sim = new ArrayList<String>();
			
			
				for(int d=0;d<dict[(int)x.charAt(0)-97].size();d++) {
				
						String y = dict[(int)x.charAt(0)-97].get(d);
						ylen = y.length();
//						System.out.println(d); 
						l++;
						int edt[][] = new int[xlen+1][ylen+1];
						
						for(int i=0;i<=xlen; i++) {
							edt[i][0]=i;
						}
						
						for(int j=0;j<=ylen; j++) {
							edt[0][j]=j;
						}
						
						for(int i=1;i<=xlen;i++){
							for(int j=1;j<=ylen;j++) {
								if(x.charAt(i-1)!=y.charAt(j-1)) {
									edt[i][j] = Math.min(Math.min(edt[i-1][j]+1, edt[i][j-1]+1), edt[i-1][j-1]+1);
								}else {
									edt[i][j] = edt[i-1][j-1];
								}
							}
						}
						if(edt[xlen][ylen]<=2) {
							sim.add(y);
						}				
				}
				
				System.out.println("\nThe possible correct spelling can be following : \n");
				for(int s=0;s<sim.size();s++) {
					System.out.println(sim.get(s));
				}
				
			end = System.nanoTime();
		}

}

