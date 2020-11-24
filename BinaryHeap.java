import java.util.*;

public class BinaryHeap {
	private static int d = 2, nil = 0;
	private int heapSize;
    public String[] heap;
	public BinaryHeap(int capacity) {
        heapSize = nil;
        heap = new String[capacity];
    }
	
    public boolean isFull( ) {
        return heapSize == heap.length;
    }
    
    public boolean isEmpty( ) {
        return heapSize == nil;
    }
    
    public void makeEmpty( ) {
        heapSize = nil;
    }
    
    private int parent(int i) {
        return (i - 1)/d;
    }
    
    private int xthChild(int i, int k) {
        return d*i + k;
    }
    
    public void insert(String nama) {
    	int x = convert(nama);
    	if (isFull( ) )
    		 System.out.println("Heap capacity is full\n");
    		 else {
    		System.out.println(nama + " ASCII = " + x);
    		 heap[heapSize++] = nama;
    		 heapifyUp(heapSize - 1);
    		 }
    }
    public int convert(String data) {
    	int total = nil;
    	for (int i= nil; i < data.length(); i++) {
    		total = total + ((int)data.charAt(i));
    		}
    	return total;
    }
    public int deleteMin() {
    	int keyItem = convert(heap[0]);
    	 delete(0);
    	 return keyItem;
    }
    public int delete(int ind) {
    	if (isEmpty() ) {
    		 System.out.println("Heap Telah Kosong");
    		 return 0;
    	} else {
    		 int keyItem = convert(heap[ind]);
    		 heap[ind] = heap[heapSize - 1];
    		 heapSize--;
    		 heapifyDown(ind);
    		 return keyItem;
    	}
    }
    private void heapifyUp(int childInd) {
    	String tmp = heap[childInd];
    	 while (childInd > 0 && convert(tmp) < convert(heap[parent(childInd)]))
    	 {
    	 heap[childInd] = heap[ parent(childInd) ];
    	 childInd = parent(childInd);
    	 }
    	 heap[childInd] = tmp;
    }
    private void heapifyDown(int ind) {
    	int child;
    	 String tmp = heap[ ind ];
    	 while (xthChild(ind, 1) < heapSize) {
    	 child = minChild(ind);
    	 if (convert(heap[child]) < convert(tmp))
    	 heap[ind] = heap[child];
    	 else
    	 break;
    	 ind = child;
    	 }
    	 heap[ind] = tmp;
    }
    private int minChild(int ind) {
    	int bestChild = xthChild(ind, 1);
    	 int k = 2;
    	 int pos = xthChild(ind, k);
    	 while ((k <= d) && (pos < heapSize)) {
    	 if (convert(heap[pos]) < convert(heap[bestChild]))
    	 bestChild = pos;
    	 pos = xthChild(ind, k++);
    	 }
    	 return bestChild;
    }
    public void PrintHeap() {
        for (int i = 0; i < heapSize; i++)
        	System.out.println(heap[i] + " ");
        System.out.println();
    }     
    
    public static void main(String[] args) {
        int pilih = 0;
        int c = 0;
        boolean check = false;
        Scanner scan = new Scanner(System.in);
        while(check == false) {
        	System.out.print("Masukkan Jumlah Data : ");
        	c = scan.nextInt();   
        	check = true;        
      } 
        
        BinaryHeap bha = new BinaryHeap(c);
        
        System.out.println("Ukuran Binary Heap = "+bha.heap.length);
        
        check =false;
        	while(check == false) {
            System.out.println("Menu :");
            System.out.println("1. Masukkan Data");
            System.out.println("2. Hapus Heap Terkecil");
            System.out.println("3. Tampilkan Heap");
            System.out.print("Masukkan Pilihan Anda : ");
            try {
            	pilih = scan.nextInt();
            	check = true;
            switch(pilih) {
                case 1 : System.out.println("Masukkan Nama : ");
                	String value = scan.next();
                	bha.insert(value);
            			
                	check = false;
                    break;
                         
                case 2 : bha.deleteMin();
                  	System.out.println("");
                	System.out.print("Heap yang tersisa :");
                	bha.PrintHeap();
                	check = false;
                	break;
                	
                case 3 : System.out.println("Data Heap :");                		
                	bha.PrintHeap();
                	
                	check = false;
                         break;
                         
                default : System.out.println("ERROR");
                		check = false;
                		break;
            }	} 
            	catch(InputMismatchException e) {
            	System.out.println("ERROR");
            	check = false;
            }	
           }	
        }
    }