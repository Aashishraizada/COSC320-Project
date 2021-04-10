package milestone3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestRoutetInNetwork {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner user_input = new Scanner(System.in);
		int numFlights = 90000;	//(90000 for network5)(max 6.4 million for network3) (2 million for network4)
		int[][] flight_network = new int[numFlights][6];
		
//		System.out.print("Please enter the starting location (enter numbers from 1 to 15 for now): ");
//		int source = user_input.nextInt();
		int source = 1;
		while(source < 1 || source > 15) {
			System.out.print("Invalid input. Try again: ");
			source = user_input.nextInt();
		}
//		System.out.print("Please enter the destination location (enter numbers from 1 to 15 for now): ");
//		int destination = user_input.nextInt();
		int destination = 8;
		while(destination < 1 || destination > 15) {
			System.out.print("Invalid input. Try again: ");
			destination = user_input.nextInt();
		}
		
		System.out.println("----------- Algorithm A -----------");
		long start = System.currentTimeMillis();
		loadData(numFlights, flight_network);
		int[] result = algorithmA(numFlights, flight_network, source, destination);
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.printf("The program took " + time + " ms to run\n");
		if(result != null)
			System.out.printf("Starting location: %d\nDestination: %d\nTotal price: $%d \nFlight duration: %d minutes\nWait duration: %d minutes\nTotal duration: %d minutes\n", source, result[0], result[1], result[2], result[3] - result[2], result[3]);
		else
			System.out.println("Empty result");
		System.out.println("-----------------------------------\n");
		
		System.out.println("----------- Algorithm B -----------");
		start = System.currentTimeMillis();
		loadData(numFlights, flight_network);
		result = algorithmB(numFlights, flight_network, source, destination);
		end = System.currentTimeMillis();
		time = end - start;
		System.out.printf("The program took " + time + " ms to run\n");
		if(result != null)
			System.out.printf("Starting location: %d\nDestination: %d\nTotal price: $%d \nFlight duration: %d minutes\nWait duration: %d minutes\nTotal duration: %d minutes\n", source, result[0], result[1], result[2], result[3] - result[2], result[3]);
		else
			System.out.println("Empty result");
		System.out.println("-----------------------------------\n");
		
		user_input.close();
    }
	
	public static int[] algorithmA(int numFlights, int[][] flights, int source, int destination) {
	
		@SuppressWarnings("unchecked")
		List<ConnectedNode>[] network = new ArrayList[numFlights];
		for (int i = 0; i < numFlights; i++)
			network[i] = new ArrayList<>(6);
		for (int[] flight : flights)
			network[flight[0]].add(new ConnectedNode(flight[1], flight[2], flight[3], flight[4], flight[5]));
		
		PriorityQueue<ConnectedNode> heap = new PriorityQueue<>();
		heap.add(new ConnectedNode(source, 0, 0, 0, 0));
		
		while (!heap.isEmpty()) {
			ConnectedNode temp = heap.poll();
			int city = temp.getVertex();
			int duration = temp.getDuration();
			int wait = temp.getWait();
			int price = temp.getPrice();
			if (city == destination) {
			    return new int[] {city, price, duration, wait};
			}
			
			List<ConnectedNode> neighbors = network[city];
			for (ConnectedNode neighbor : neighbors)
				heap.add(new ConnectedNode(neighbor.getVertex(), duration + neighbor.getDuration(), wait + neighbor.getWait(), price + neighbor.getPrice()));
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int[] algorithmB(int numFlights, int[][] flights, int source, int destination) {
		
		List<ConnectedNode>[] network = new LinkedList[numFlights];
		for (int i = 0; i < numFlights; i++)
			network[i] = new LinkedList();
		for (int i = 0; i < flights.length; i++)
			network[flights[i][0]].add(new ConnectedNode(flights[i][1], flights[i][2], flights[i][3], flights[i][4], flights[i][5]));
		
		Queue<ConnectedNode> heap = new LinkedList<>();
		heap.add(new ConnectedNode(source, 0, 0, 0, 0));
		
		while (!heap.isEmpty()) {
			ConnectedNode temp = heap.poll();
			int city = temp.getVertex();
			int duration = temp.getDuration();
			int wait = temp.getWait();
			int price = temp.getPrice();
			if (city == destination) {
			    return new int[] {city, price, duration, wait};
			}
			
			List<ConnectedNode> neighbors = network[city];
			for (ConnectedNode neighbor : neighbors)
				heap.add(new ConnectedNode(neighbor.getVertex(), duration + neighbor.getDuration(), wait + neighbor.getWait(), price + neighbor.getPrice()));
		}

		return null;
	}
	
    public static void loadData(int numFlights, int[][] flight_network) throws FileNotFoundException {
		Scanner file_input = new Scanner(new File("data/final_flight_network.csv"));
		file_input.useDelimiter(",");
		for (int j = 0; j < numFlights; j++) {
			for(int i = 0; i < 6; i++) {
				flight_network[j][i] = Integer.parseInt(file_input.next());
			}
		}
		file_input.close();
	}
}
