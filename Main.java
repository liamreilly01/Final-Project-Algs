import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
public class Main {
    public final static String one = "1";
    public final static String two = "2";
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vancouver Transport System Interface!");
        System.out.println("For infomration regarding stop names type '1'"+"\n"+"For information regarding arrival times type '2'");
        String input = scanner.nextLine();
        System.out.println(input);
        if(input.equals(one)){
            
            System.out.println("Choose a bus stop: ");
            String stop = scanner.next();
            String filename = "stops.txt";
            TST tree = new TST(filename);
            searchBusStop(tree,stop.toUpperCase());
        }
        else if(input.equals(two)){
            System.out.println("Please choose an arival time: ");
            String arrivalTime = scanner.nextLine();
            String filename = "stop_times.txt";
            File file = new File(filename);
            ArrayList<String> arrivalTimes = new ArrayList<String>();
        try {
            Scanner times = new Scanner(file);
            ArrivalTime a = new ArrivalTime();
            times.nextLine();
            while(times.hasNextLine()){
                String line = times.nextLine();
                String[] lineInformation = line.trim().split(",");
                String time = lineInformation[1].trim();
                if(a.checkValidArrivalTime(arrivalTime)){
                    if(a.checkRequiredArrivalTime(arrivalTime,time)){
                        String s = "Trip ID:"+lineInformation[0]+" Arrival Time:"+lineInformation[1]+" Departure Time:"+lineInformation[2]+
                        " Sequence Stop:"+lineInformation[3];
                        arrivalTimes.add(s);
                        
                    } 
                }
                
            }
            
            for(int i = 0;i < arrivalTimes.size();i++){
                System.out.println(arrivalTimes.get(i));
            
        }
     } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
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
    System.out.println("Stop Name:"+stop.stopName+" || Stop Number:"+stop.stopNumber+".");
    }
}}

}
