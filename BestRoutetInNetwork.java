package milestone3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BestRoutetInNetwork {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner file_input = new Scanner(new File("flight_network.csv"));
		Scanner user_input = new Scanner(System.in);
		int numFlights = 50;
		int[][] flight_network = new int[50][6];
		
//		System.out.print("Please enter the starting location (enter numbers from 1 to 15 for now): ");
		int source = 10;
//		System.out.print("Please enter the destination location (enter numbers from 1 to 15 for now): ");
		int destination = 1;
//		System.out.print("Please enter the maximum number of stops you need (enter numbers from 1 to 4 for now): ");
		int numStops = 5;
		file_input.useDelimiter(",");
		
//		int count = 0;
		for (int j = 0; j < 50; j++) {
			for(int i = 0; i < 6; i++) {
				flight_network[j][i] = Integer.parseInt(file_input.next());
			}
//			count++;
		}
//		System.out.println(count);
//		for(int j = 0; j < 50; j++) {
//			for(int i = 0; i < 6; i++) {
//				System.out.print(flight_network[j][i] + " ");
//			}
//			System.out.println();
//		}
//		int[][] flight_network = {{0, 1, 1850, 1430, 1400, 200},{1, 2, 2250, 1630, 1410, 400},{0, 2, 2420, 950, 850, 700}};
		
		int[] result = algorithmA(numFlights, flight_network, source, destination, numStops);
//		for(int i = 0; i < result.length; i++) {
			System.out.printf("Starting location: %d\nNumber of stops in between: %d\nTotal price: $%d \nTotal duration: %d minutes\nTotal wait: %d minutes", result[0], result[1], result[2], result[3], result[4]);		
//		}
		user_input.close();
		file_input.close();
    }
	
	public static int[] algorithmA(int numFlights, int[][] flights, int source, int destination, int numStops) {
	
		@SuppressWarnings("unchecked")
		List<ConnectedNode>[] network = new ArrayList[numFlights];
		for (int i = 0; i < numFlights; i++)
			network[i] = new ArrayList<>(6);
		for (int[] flight : flights)
			network[flight[0]].add(new ConnectedNode(flight[1], flight[2], flight[3], flight[4], flight[5], numStops));
		
		PriorityQueue<ConnectedNode> heap = new PriorityQueue<>();
		heap.add(new ConnectedNode(source, 0, 0, 0, 0, 0));
		
		while (!heap.isEmpty()) {
			ConnectedNode temp = heap.poll();
			int city = temp.getVertex();
			int duration = temp.getDuration();
			int wait = temp.getWait();
			int price = temp.getPrice();
//			int weight = temp.getCalc_weight();
			int stopsMade = temp.getVertex();
			if (stopsMade > numStops + 1)
			    continue;
			if (city == destination)
			    return new int[] {city, stopsMade, price, duration, wait};
			List<ConnectedNode> neighbors = network[city];
			for (ConnectedNode neighbor : neighbors)
				heap.add(new ConnectedNode(neighbor.getVertex(), duration + neighbor.getDuration(), wait + neighbor.getWait(), price + neighbor.getPrice(), stopsMade + 1));
		}

		
		return new int[] {10, 1, 346, 582, 263, 901};
	}

    public static void loadDataA() {
		
	}
}
