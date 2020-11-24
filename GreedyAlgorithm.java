import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GreedyAlgorithm {
	
	static class SortbyDensity implements Comparator<Product> {

		public int compare(Product o1, Product o2) {
			return (int) (o1.density - o2.density);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner fileScanner = null;
		
		ArrayList<Product> arList = new ArrayList<Product>();
		
		try {
			fileScanner = new Scanner(new File("src/product.txt"));
			
			while (fileScanner.hasNextLine()) {
				String data = fileScanner.nextLine();

				if (!data.substring(0, 1).equals("#")) {
					String[] dataArray = data.split("\t");
					
					Product p = new Product();
					
					p.name = dataArray[0];
					p.weight = Integer.parseInt(dataArray[1]);
					p.profit = Integer.parseInt(dataArray[2]);
					p.discount = Integer.parseInt(dataArray[3]);
					
					p.calculate();
					
					arList.add(p);
				}
			}
			
			System.out.println("Produk Yang Tersedia");
			System.out.println("==================================================");
			
			Integer i = 1;
			
			for (Product p : arList) {
				System.out.println((i++) + ". \t" + p.name + "\t" + p.weight + "\t" + p.profit + "\t" + p.discount + "\t" + p.density);
			}
			
			System.out.println("==================================================\n");
			
			// Sorting data on array list
			arList.sort(new GreedyAlgorithm.SortbyDensity());
			
			System.out.print("Masukkan Jumlah Uang Yang Anda Miliki ? ");
			
			Double uang = Double.parseDouble(input.next());
			Double uangBackup = uang;
			Double cost = 0d;
			
			System.out.println("\nBerikut Item Yang Dapat Anda Beli");
			System.out.println("==================================================");
			for (Product p : arList) {
				if (uang == 0) break;
				if (uang < p.price) continue;
				
				System.out.println("Anda Dapat Membeli " + p.weight + " " + p.name);
				
				uang -= p.price;
				cost += p.price;
			}
			
			if (cost == 0) {
				System.out.println("Uang Anda Tidak Mencukupi");
			}
			
			System.out.println("==================================================\n");
			
			System.out.println("Total Pengeluaran " + cost.intValue() + " Sisa Uang " + (uangBackup.intValue() - cost.intValue()));
			
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		
		input.close();
	}

}
