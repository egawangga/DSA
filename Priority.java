package Praktikum2;

import java.util.*;

public class Priority {

	public static void main(String[] args) {
		
		String[] name = new String[10];
		Integer[] order = new Integer[10];
		Integer[] amount = new Integer[10];
		Integer[] date = new Integer[10];
		Integer[] prior = new Integer[10];
		
		Scanner scan = new Scanner(System.in);
		
		for(int i=0;i<10; i++) {
			System.out.print("Nama\t\t\t: ");
			name[i] = scan.next();
			System.out.print("Jenis Pesanan\t\t: ");
			order[i] = scan.nextInt();
			System.out.print("Jumlah Pesanan\t\t: ");
			amount[i] = scan.nextInt();
			System.out.print("Tanggal Pemesanan\t: ");
			date[i] = scan.nextInt();
		}
		
		for(int i=0;i<10;i++) {
			if(order[i] == 1) {
				prior[i] = 5*amount[i] + date[i];
			}
			else if(order[i] == 2) {
				prior[i] = 1*amount[i] + date[i];
			}
			else if(order[i] == 3) {
				prior[i] = 3*amount[i] + date[i];
			}
		}
		
		for(int i=0;i<10;i++) {
			for(int j=0; j<9-i;j++) {
				if(prior[j]>prior[j+1]) {
					int c;
					String temp;
					c = prior[j];
					prior[j] = prior[j+1];
					prior[j+1] = c;

					temp = name[j];
					name[j] = name[j+1];
					name[j+1] = temp;

					c = order[j];
					order[j] = order[j+1];
					order[j+1] = c;
					
					c = amount[j];
					amount[j] = amount[j+1];
					amount[j+1] = c;
					
					c = date[j];
					date[j] = date[j+1];
					date[j+1] = c;
				}
			}
		}
		System.out.println("\n Data Pesanan Pelanggan(Nama, Jenis Pesanan, Jumlah Pesanan, Tanggal Jadi) :\n");
		for(int i=0; i<10;i++) {
			System.out.println(i+1 + ". " + name[i] + "-" + order[i] + "-" + amount[i] + "-" + prior[i] + "\n");
		}
	}
}