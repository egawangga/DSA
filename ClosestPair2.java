package Praktikum8;

import java.util.*;

public class ClosestPair2 {
	public static void main(String[] args) {
		for (int i = 2; i < 201; i++) {
			List<Coordinate> points = new ArrayList<>();
			Random random = new Random();
			for (int x = 0; x < i; x++) {
				points.add(new Coordinate(random.nextInt() / i, random.nextInt() / i));
			}
			long start = System.nanoTime();
			bruteForce(points);
			long end = System.nanoTime();
			System.out.println("<" + i + "> Brute Force \t\t: " + (end - start));

			start = System.nanoTime();
			CoordinateDetail dqClosestPair = divideAndConquer(points);
			end = System.nanoTime();
			System.out.println("<" + i + "> Devide and Conquer \t: " + (end - start));
			System.out.println("<" + i + "> Result \t\t\t: " + dqClosestPair);
			System.out.println();
		}
	}

	private static class Coordinate {
		private int x;
		private int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	private static class CoordinateDetail {
		private Coordinate point1 = null;
		private Coordinate point2 = null;
		private double distance = 0.0;

		public CoordinateDetail(Coordinate point1, Coordinate point2, double distance) {
			this.point1 = point1;
			this.point2 = point2;
			this.distance = distance;
		}

		public Coordinate getPoint1() {
			return point1;
		}

		public Coordinate getPoint2() {
			return point2;
		}

		public double getDistance() {
			return distance;
		}

		public void set(Coordinate point1, Coordinate point2, double distance) {
			this.point1 = point1;
			this.point2 = point2;
			this.distance = distance;
		}

		public String toString() {
			return getPoint1() + " " + getPoint2() + " : distance = " + String.format("%.15f", getDistance());
		}
	}

	private static double calDistance(Coordinate p1, Coordinate p2) {
		double xdist = p2.getX() - p1.getX();
		double ydist = p2.getY() - p1.getY();
		return Math.hypot(xdist, ydist);
	}

	private static CoordinateDetail bruteForce(List<Coordinate> points) {
		int coorSize = points.size();
		if (coorSize < 2)
			return null;

		CoordinateDetail coorPoint = new CoordinateDetail(points.get(0), points.get(1),
				calDistance(points.get(0), points.get(1)));
		if (coorSize > 2) {
			for (int i = 0; i < coorSize - 1; i++) {
				Coordinate point1 = points.get(i);
				for (int j = i + 1; j < coorSize; j++) {
					Coordinate point2 = points.get(j);
					double distance = calDistance(point1, point2);
					if (distance < coorPoint.getDistance())
						coorPoint.set(point1, point2, distance);
				}
			}
		}
		return coorPoint;
	}

	private static void sortByX(List<Coordinate> points) {
		Collections.sort(points, new Comparator<Coordinate>() {
			public int compare(Coordinate point1, Coordinate point2) {
				if (point1.getX() < point2.getX())
					return -1;
				if (point1.getX() > point2.getX())
					return 1;
				return 0;
			}
		});
	}

	private static void sortByY(List<Coordinate> points) {
		Collections.sort(points, new Comparator<Coordinate>() {
			public int compare(Coordinate point1, Coordinate point2) {
				if (point1.getY() < point2.getY())
					return -1;
				if (point1.getY() > point2.getY())
					return 1;
				return 0;
			}
		});
	}

	public static CoordinateDetail divideAndConquer(List<Coordinate> points) {
		List<Coordinate> listofSortedX = new ArrayList<>(points);
		sortByX(listofSortedX);
		List<Coordinate> listofSortedY = new ArrayList<>(points);
		sortByY(listofSortedY);
		return divideAndConquer(listofSortedX, listofSortedY);
	}

	private static CoordinateDetail divideAndConquer(List<Coordinate> listofSortedX, List<Coordinate> listofSortedY) {
		int coorSize = listofSortedX.size();
		if (coorSize <= 3)
			return bruteForce(listofSortedX);

		int index = coorSize >>> 1;
		List<Coordinate> leftOfCenter = listofSortedX.subList(0, index);
		List<Coordinate> rightOfCenter = listofSortedX.subList(index, coorSize);

		CoordinateDetail closestPair = null;

		List<Coordinate> tempList = new ArrayList<>(leftOfCenter);
		sortByY(tempList);

		CoordinateDetail closestPairLeft = divideAndConquer(leftOfCenter, tempList);

		tempList.clear();

		tempList.addAll(rightOfCenter);
		sortByY(tempList);

		CoordinateDetail closestPairRight = divideAndConquer(rightOfCenter, tempList);

		if (closestPairRight.getDistance() < closestPairLeft.getDistance())
			closestPair = closestPairRight;
		else
			closestPair = closestPairLeft;

		tempList.clear();

		double shortestDistance = closestPair.getDistance();
		double centerX = rightOfCenter.get(0).getX();
		for (Coordinate point : listofSortedY)
			if (Math.abs(centerX - point.getX()) < shortestDistance)
				tempList.add(point);

		for (int i = 0; i < tempList.size() - 1; i++) {
			Coordinate point1 = tempList.get(i);
			for (int j = i + 1; j < tempList.size(); j++) {
				Coordinate point2 = tempList.get(j);
				if ((point2.getY() - point1.getY()) >= shortestDistance)
					break;
				double distance = calDistance(point1, point2);
				if (distance < closestPair.getDistance()) {
					closestPair.set(point1, point2, distance);
					shortestDistance = distance;
				}
			}
		}

		return closestPair;
	}

}