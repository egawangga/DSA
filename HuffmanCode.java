package huffmannCode;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

	class Code {
		char value;
		int frekuensi;
		String code;
	}

	abstract class Tree implements Comparable<Tree> {
	    public final int frekuensi;
	    public Tree (int freq) {
	        frekuensi = freq;
	    }
	    public int compareTo (Tree tree) {
	        return frekuensi - tree.frekuensi;
	    }
	}

	class Leaf extends Tree {
	    public final char value; 
	    public Leaf(int freq, char val) {
	        super(freq);
	        value = val;
	    }
	}

	class Node1 extends Tree {
	    public final Tree left, right;
	    public Node1(Tree l, Tree r) {
	        super(l.frekuensi + r.frekuensi);
	        left = l;
	        right = r;
	    }
	}

	public class HuffmanCode {
		static String[] huruf = new String[256];
	    static String[] kode = new String[256];
	    static HashMap<Character, Code> map = new HashMap<Character, Code>();
	    static int k = 0;
	    static int fr = 0;
	    
	    public static int[] buildCharFreqs(String str) {
	    	int[] charFrekuensi = new int[256];
	        for (char c : str.toCharArray()) {
	        	if (c == 8220 || c == 8221)
	        		c = '"';
	        	
	            charFrekuensi[c]++;
	        }
	        
	        return charFrekuensi;
	    }
	    
    public static Tree buildTree(int[] charFreqs) {
        PriorityQueue<Tree> trees = new PriorityQueue<Tree>();
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                trees.offer(new Leaf(charFreqs[i], (char) i));  
            }
        }
        assert trees.size() > 0;
        while (trees.size() > 1) {
            Tree a = trees.poll();
            Tree b = trees.poll();
            trees.offer(new Node1(a, b)); 
        }
        return trees.poll();
    }
    
    public static Double generateEficiency() {
    	Double LHuffman = 0.0;
    	Double Entropy = 0.0;
    	
    	for (Entry<Character, Code> c : map.entrySet()) {
    		
    		Double pK = c.getValue().frekuensi / (fr + 0.0);
    		
    		LHuffman += c.getValue().code.length() * pK;
    		Entropy += pK * (Math.log10(pK) / Math.log10(2));

    	}

    	Entropy = -Entropy;

    	return (Entropy / LHuffman) * 100;
    }
    
    public static void code(Tree tree, StringBuffer prefix) {
        String cetak = "";
        assert tree != null;
        if (tree instanceof Leaf) {
            Leaf leaf = (Leaf) tree;
            
            Code c = new Code();
            c.value = leaf.value;
            c.frekuensi = leaf.frekuensi;
            c.code = prefix.toString();
            
            fr += leaf.frekuensi;
            
            map.put(c.value, c);
            
            cetak = "";
            cetak += leaf.value;
            huruf[k] = cetak;
            kode[k] = prefix.toString();
            k++;
        } else if (tree instanceof Node1) {
            Node1 node = (Node1) tree;
            prefix.append('0');
            code(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
            prefix.append('1');
            code(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1); }
    }

    public static String cetak(String h) {
        HashMap<String, String> compressed = new HashMap<>();
        StringBuilder str = new StringBuilder();
        String d = "";
        for (int i = 0; i < k; i++)
            compressed.put(huruf[i], kode[i]);
        for (int y = 0; y < h.length(); y++) {
            d += h.charAt(y);
            if (compressed.get(d) != null)
                str.append(compressed.get(d));
            d = ""; 
        }
        
        return str.toString();
    }
}
