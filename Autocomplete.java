import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class TrieNode{
    
    char content; 
    boolean isEnd; 
    LinkedList<TrieNode> childNodesList;
    
    public TrieNode(char c)
    {
        childNodesList = new LinkedList<TrieNode>();
        isEnd = false;
        content = c;
    } 
  
    public TrieNode subNode(char c)
    {
        if (childNodesList != null)
            for (TrieNode eachChild : childNodesList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }   
} 


class Trie{
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode(' '); 
    }
    
    public void insert(String word)
    {
        if (search(word.trim()) == true) 
            return;  
        
        TrieNode current = root; 
        
        for (char ch : word.trim().toCharArray())
        {	
            TrieNode child = current.subNode(ch);    // return reference to subchildren character 
            
            if (child != null)
                current = child;
            else 
            {
//            	if(current.childNodesList.contains(ch))
                 current.childNodesList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
        }
        current.isEnd = true;
    }
    
    /* Function to search for word */
    public boolean search(String word)
    {
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        if (current.isEnd == true) 
            return true;
        return false;
    }
    
    
    public List<String> autoComplete(String prefix)
	{
    	System.out.println("Prefix");
    	System.out.println(prefix);
    	
		if(prefix == null || prefix.isEmpty())
			return null;
		
		char[] chars = prefix.trim().toCharArray();
		
		TrieNode start = root;
		boolean flag = false;
		
		for(char c : chars) {
			System.out.println(c);
		}
		
//		for(char c : chars)
		for(int i=0;i<chars.length;i++)
		{	char c = chars[i];
			if(start.childNodesList.size() > 0)  // If root have child node
			{
				for(TrieNode node : start.childNodesList)
				{	
					if(node.content == c)
					{	
//						System.out.println(c);
						start = node;
						if(i== chars.length-1) {
							flag=true;
							break;	
						}
						
					}
				}
			}
			else
			{
				flag = false;
				break;
			}
		}
		
		
		if(flag)
		{
			List<String> matches = getAllWords(start,prefix);
			return matches;
		}
		
		return null;
	}
    
    
    
    private List<String> getAllWords(TrieNode start,final String prefix)
	{
    	
		if(start.childNodesList.size() == 0)
		{
			List<String> list = new ArrayList();
			list.add(prefix);
			return list;
		}
		else
		{
			List<String> list = new ArrayList();
			for(TrieNode n: start.childNodesList)
			{
				list.addAll(getAllWords(n, prefix.trim()+n.content));
			}
			return list;
		}
	} 
}


