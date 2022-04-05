import java.util.*;
public class Main {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the first stop: ");
        int src = input.nextInt();
        System.out.println("Please enter the last stop: ");
        int dest = input.nextInt();
        ShortestPath.getPath(src, dest);
    }
}
