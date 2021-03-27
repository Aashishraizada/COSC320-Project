package milestone3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BestRoutetInNetwork {
	
	public static void main(String[] args) {
		int numCities = 3;
		
		int[][] flight_network = {{0, 1, 1850, 1430, 1400, 200},{1, 2, 2250, 1630, 1410, 400},{0, 2, 2420, 950, 850, 700}};
		int source = 0;
		int destination = 2;
		int numStops = 1;
		
		int[] result = algorithmA(numCities, flight_network, source, destination, numStops);
		for(int i = 0; i < result.length; i++) {
			System.out.println(result[i]);		
		}
	
    }
	
	public static int[] algorithmA(int numCities, int[][] flights, int source, int destination, int numStops) {
	
		@SuppressWarnings("unchecked")
		List<ConnectedNode>[] network = new ArrayList[numCities];
		for (int i = 0; i < numCities; i++)
			network[i] = new ArrayList<>(7);
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

		
		return null;
	}

    public static void loadDataA() {
		
	}
}
