package milestone3;

public class Pair implements Comparable<Pair> {
    
	private int vertex, duration, wait, price, calc_weight, numStops;
	
    public Pair(int vertex, int arrival_time, int departure_time, int scheduled_departure_time, int price, int numstops) {
		super();
		setVertex(vertex);
		setDuration(Math.abs(arrival_time - departure_time));
		setWait(Math.abs(scheduled_departure_time - departure_time));
		setPrice(price);
		setCalc_weight((int)((0.8 * (duration + wait)) + (0.2 * price)));
		setNumStops(numstops);
    }
    
    public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int cost) {
		this.price = cost;
	}

	public int getCalc_weight() {
		return calc_weight;
	}

	public void setCalc_weight(int cacl_weight) {
		this.calc_weight = cacl_weight;
	}

	public int getNumStops() {
		return numStops;
	}

	public void setNumStops(int stops) {
		this.numStops = stops;
	}

	@Override
    public int compareTo(Pair o) {
        return this.calc_weight - o.calc_weight;
    }
	
}
