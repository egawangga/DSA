package psda11;
import java.util.Scanner;

public class RatInMaze {

	public int[][] solution;
	public String kode = "";
	public static int backstracking = 0;
	public static int langkah = 0;
	public static int[] back;
	public static int a = 0;
	private static Scanner inp;

	// initialize the solution matrix in constructor.
	public RatInMaze(int N) {
		solution = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solution[i][j] = 0;
			}
		}
	}

	public void solveMaze(int[][] maze, int N) {
		if (findPath(maze, 0, 0, N, "down")) {
			System.out.println("( Perjalanan dari Finish ke Start )");
			System.out.println();
			System.out.println("Solusi perjalanan : ");
			print(solution, N);

		} else {
			System.out.println("\nNO PATH FOUND");
		}
	}

	public boolean findPath(int[][] maze, int x, int y, int N, String direction) {
		// check if maze[x][y] is feasible to move
		if (x == N - 1 && y == N - 1) {// we have reached
			solution[x][y] = 1;
			System.out.print("\n\nDalam bentuk arah : \nFinish - ");
			return true;
		}
		if (isSafeToGo(maze, x, y, N)) {
			// move to maze[x][y]
			solution[x][y] = 1;
			// now rat has four options, either go right OR go down or left or go up
			if (direction != "up" && findPath(maze, x + 1, y, N, "down")) { // go down
				System.out.print("bawah - ");
				return true;
			}
			// else go down
			if (direction != "left" && findPath(maze, x, y + 1, N, "right")) { // go right
				System.out.print("kanan - ");
				return true;
			}
			if (direction != "down" && findPath(maze, x - 1, y, N, "up")) { // go up
				System.out.print("atas - ");
				return true;
			}
			if (direction != "right" && findPath(maze, x, y - 1, N, "left")) { // go left
				System.out.print("kiri - ");
				return true;
			}
			// if none of the options work out BACKTRACK undo the move
			solution[x][y] = 0;
			kode += "F";
			System.out.print("F");//kode "FFF" show backtracking
			System.out.print(kode.substring(kode.length() - 4, kode.length()));

			if (kode.substring(kode.length() - 4, kode.length()).equals("XXXF")) {
				back[a] = x + 1;
				back[++a] = y + 1;
				backstracking++;
				a++;
			}
			return false;
		}
		return false;
	}

	// this function will check if mouse can move to this cell
	public boolean isSafeToGo(int[][] maze, int x, int y, int N) {
		// check if x and y are in limits and cell is not blocked
		if (x >= 0 && y >= 0 && x < N && y < N && maze[x][y] != 0) {
			kode += "Y";
			System.out.print("Y");
			langkah++;
			return true;
		} else {
			System.out.print("X");
			kode += "X";
		}
		return false;
	}

	public void print(int[][] solution, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" " + solution[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		inp = new Scanner(System.in);
		System.out.println("\n======= Maze with Backtracking =======\n");
		System.out.print("Inputkan dimensi matrix : ");
		int N = inp.nextInt();
		int[][] maze = new int[N][N];
		back = new int[N];
		System.out.println("Inputkan value matriks : ");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print("Indeks [" + (i + 1) + "][" + (j + 1) + "] : ");
				maze[i][j] = inp.nextInt();
			}
		}
		System.out.println("Bentuk Perjalanan : ");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++)
				System.out.print(maze[i][j] + " ");
			System.out.println();
		}

		System.out.println("\nRute Perjalanan");
		System.out.println("-----------------");
		System.out.println("Dalam Bentuk Kode : ");
		RatInMaze r = new RatInMaze(N);
		r.solveMaze(maze, N);

		System.out.println("\nBanyak Langkah : " + langkah);
		System.out.println("Banyak Terjadi Backstracking : " + backstracking);

		System.out.println("Tempat terjadinya Backstracking : ");
		for (int i = 0; i < back.length; i += 2) {
			if (back[i] != 0) {
				System.out.println("Cell : " + back[i] + " " + back[i + 1]);
			}
		}
	}
}
