import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ShortestPath{
    public EdgeWeightedDigraph graph;
    public HashMap <Integer, Integer> map = new HashMap <Integer, Integer>();
    int index;

    ShortestPath(String filename){
        graph = Vertices(filename);
        graph = StopTimes();
        graph = Transfers();

    }

    public EdgeWeightedDigraph Vertices(String filename){
        int numberOfStops = -1;
        File file = new File(filename);
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                numberOfStops++;
                scanner.nextLine();
            }
            graph = new EdgeWeightedDigraph(numberOfStops);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return graph;

    }

    public EdgeWeightedDigraph StopTimes(){
        String categories;
        String current;
        String next;
        double weight;
        File file = new File("stop_times.txt");
        try{
        Scanner scanner = new Scanner(file);
        categories = scanner.nextLine();
        current = scanner.nextLine();
        while(scanner.hasNextLine()){
            next = scanner.nextLine();
            String [] lineInformation1 = current.split(",");
            String [] lineInformation2 = next.split(",");
            if(lineInformation1[0].equals(lineInformation2[0])){
                String currentStopNumber = lineInformation1[3];
                String nextStopNumber = lineInformation2[3];
                int currentStopNumberInt = Integer.parseInt(currentStopNumber);
                int nextStopNumberInt = Integer.parseInt(nextStopNumber);
                if(map.get(currentStopNumberInt) == null){
                    map.put(currentStopNumberInt, index);
                    index++;
                }
                if(map.get(nextStopNumberInt) == null){
                    map.put(nextStopNumberInt, index);
                    index++;
                }
                weight = 1;
                DirectedEdge edge = new DirectedEdge(map.get(currentStopNumberInt), map.get(nextStopNumberInt), weight);
                graph.addEdge(edge);
            }
            current = next;
        }
        scanner.close();
            }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return graph;
    }

    public EdgeWeightedDigraph Transfers(){
        String categories;
        String current;
        File file = new File("transfers.txt");
        try{
        Scanner scanner = new Scanner(file);
        categories = scanner.nextLine();
        while(scanner.hasNextLine()){
            current = scanner.nextLine();
            String [] lineInformation1 = current.split(",");
            int srcStop = Integer.parseInt(lineInformation1[0]);
            int destStop = Integer.parseInt(lineInformation1[1]);
                if(map.get(srcStop) == null){
                    map.put(srcStop, index);
                    index++;
                }
                if(map.get(destStop) == null){
                    map.put(destStop, index);
                    index++;
                }
                double weight = 0;
                int transferWeight = Integer.parseInt(lineInformation1[2]);
                if(transferWeight == 0){
                    weight = 2;
                }
                else {
                    weight = (Double.parseDouble(lineInformation1[3]))/100;
                }
                DirectedEdge edge = new DirectedEdge(map.get(srcStop), map.get(destStop), weight);
                graph.addEdge(edge);
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return graph;
    }
    public static boolean getPath(int srcStop, int destStop){
        ShortestPath sp = new ShortestPath("stops.txt");
        String source = "Enter source stop: ";
        String destination = "Enter destination stop: ";
        int sourceIndex = sp.map.get(srcStop);
        int destinationIndex = sp.map.get(destStop);
        Dijkstra d = new Dijkstra(sp.graph, sourceIndex);
        if(d.hasPathTo(destinationIndex)){
            System.out.println("Cost: "+d.distTo(destinationIndex));
            Iterable<DirectedEdge> stopRoute = d.pathTo(destinationIndex);
            System.out.println("Stops en route: ");
            for(DirectedEdge stop : stopRoute){
                System.out.println("Stop ID: " + stop.to() + " cost " + stop.weight()+" ");
            }
            return true;
        }
        else{
            System.out.println("Sorry! There are no paths from stop:" + sourceIndex + " to stop:" + destinationIndex + ".");
        }
        return false; 
    }

}


