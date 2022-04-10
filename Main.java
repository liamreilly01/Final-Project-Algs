import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
public class Main {
    public final static String one = "1";
    public final static String two = "2";
    public final static String three = "3";
    public final static String four = "4";
    
    public static void main(String[] args){
        
        System.out.println("Welcome to the Vancouver Transport System Interface!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("For information regarding the shortest path between two bus stops type '1'"+"\n"+"For information regarding stop names type '2'"
        +"\n"+"For information regarding arrival times type '3'"+"\n"+"To exit the interface type '4'");
        String input = scanner.nextLine();
        if(input.equals(two)){
            System.out.println("Choose a bus stop: ");
            String stop = scanner.next();
            String filename = "stops.txt";
            TST tree = new TST(filename);
            searchBusStop(tree,stop.toUpperCase());
            
        }
        else if(input.equals(three)){
            System.out.println("Please choose an arival time: ");
            String arrivalTime = scanner.nextLine();
            String filename = "stop_times.txt";
            File file = new File(filename);
            ArrayList<String> arrivalTimes = new ArrayList<String>();
        try {
            Scanner times = new Scanner(file);
            ArrivalTime timeObject = new ArrivalTime();
            times.nextLine();
            while(times.hasNextLine()){
                String line = times.nextLine();
                String[] lineInformation = line.trim().split(",");
                String time = lineInformation[1].trim();
                if(!timeObject.checkValidArrivalTime(arrivalTime)){
                    check();
                    break;
                }
                else if(timeObject.checkValidArrivalTime(arrivalTime)){
                    if(timeObject.checkRequiredArrivalTime(arrivalTime,time)){
                        String s = "Trip ID:"+lineInformation[0]+" Arrival Time:"+lineInformation[1]+" Departure Time:"+lineInformation[2]+
                        " Stop ID:"+lineInformation[3]+" Sequence Stop:"+lineInformation[4]+" Headsign:"+lineInformation[5]+" Pickup Type:"+lineInformation[6]+" Drop Off Type:"+lineInformation[7];
                        arrivalTimes.add(s);
                        
                    } 
                   
                }
            }
            if(arrivalTimes.get(0)==null){
                System.out.println("Sorry! There are no arrival times available for your input.");
            }
            Collections.sort(arrivalTimes);
            for(int i = 0;i < arrivalTimes.size();i++){
                System.out.println(arrivalTimes.get(i));
        }
     } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    else if(input.equals(one)){
        try{
        System.out.println("Type in the bus stop ID that you want to start at: ");
        int startStop = Integer.parseInt(scanner.next());
        System.out.println("Type in the bus stop ID that you want to get to: ");
        int endStop = Integer.parseInt(scanner.next());
        ShortestPath.getPath(startStop, endStop);
        }
        catch(Exception e){
            System.out.println("No such bus stop ID");
        }

    }
    else if(input.equals(four)){
        System.out.println("Thank you for visiting the Vancouver Bus System interface. Have a great day!");
    }
    else{
        System.out.println("Incorrect input!");
    }
        }
public static void check(){
    System.out.println("Incorrect time given.");
}
public static void searchBusStop(TST tree,String stopName){
    
    Iterable<String> stops = tree.keysWithPrefix(stopName);
    boolean validStops = false;
    for(String key : stops){
        if(tree.contains(key)){
            validStops = true;
            break;
        }
    }
    
    if(validStops){
    for(String key : stops){
    Stop stop = tree.get(key);
    System.out.println("Stop Name:"+stop.stopName+" || Stop Number:"+stop.stopNumber+"."+stop.stopCode);
   }
  }
  else{
      System.out.println("No stops!");
  }
 }
}
