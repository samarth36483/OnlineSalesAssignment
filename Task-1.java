/* Kindly run the below code on any online ide */
import java.util.*;
public class Main {
  public static String getOutcome(HashMap<String, Integer> probabilities){
    /* assuming that totalProbability is 100 in every case */
    int totalProbability = 100;
    /* It generates a random number between 1 and totalProbability, +1 is addded 
    to make sure that the random value lies between [1, totalProbability] instead 
    of [0, totalProbability-1] */
    int randomVal = new Random().nextInt(totalProbability) + 1;

    int cumulativeProbability = 0;
    for (Map.Entry<String, Integer> entry : probabilities.entrySet()) {
      cumulativeProbability += entry.getValue();
      if (randomVal <= cumulativeProbability) {
          return entry.getKey();
      }
    }

    return null;
  }
  public static HashMap<String, Integer> EventSimulation(HashMap<String, Integer> probability, int occurences){
    HashMap<String, Integer> outcome = new HashMap<>();

    /* initialising new Hashmap with probability of all events as zero */
    for(Map.Entry<String, Integer> entry : probability.entrySet()){
      outcome.put(entry.getKey(), 0);
    }

    /* this loop is calculating the occurence of every option and storing its 
    frequency into the map.
    */
    for(int i = 0; i < occurences; i++){
      String occ = getOutcome(probability);
      outcome.put(occ, outcome.get(occ)+1);
    }

    return outcome;
  }
  public static void main(String[] args) {
    /* sample input for rolling dice */
      HashMap<String, Integer> diceProbability = new HashMap<>();
      System.out.println("Dice rolling simulation results:");
      diceProbability.put("1", 10);
      diceProbability.put("2", 30);
      diceProbability.put("3", 15);
      diceProbability.put("4", 15);
      diceProbability.put("5", 30);
      diceProbability.put("6", 0);

      int noOfOccurences = 1000;

      HashMap<String, Integer> diceResult = EventSimulation(diceProbability, noOfOccurences);

      for(Map.Entry<String, Integer> entry : diceResult.entrySet()){
        System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
      }

    // Flipping biased coin
    HashMap<String, Integer> coinProbabilities = new HashMap<>();
    coinProbabilities.put("Head", 35);
    coinProbabilities.put("Tail", 65);

    HashMap<String, Integer> coinResult = EventSimulation(coinProbabilities, noOfOccurences);
    System.out.println("\nCoin Flip Simulation results:");
    for (Map.Entry<String, Integer> entry : coinResult.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
    }
  }
}