import sun.security.krb5.Config;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {
    protected CombinatorialOptimizationProblem cop;

    private class SearchResult {
        boolean success;
        int steps;
        
        public SearchResult(boolean success, int steps) {
            this.success = success;
            this.steps = steps;
        }
    }

    public HillClimbing(String args[]) {
        if (args.length == 0) {
            Errors.usageError("no combinatorial optimization problem given");
        }
        
        if (args[0].equals("8queens")) {
            cop = new EightQueensProblem();
        } else {
            Errors.usageError("unknown combinatorial optimization problem: " + args[0]);
        }
    }
    
    protected SearchResult search() {
        // TODO: Implement hill climbing here. Remember that our
        // implementation differs from the one presented in the lecture
        // in the fact that we are dealing with a minimization problem
        // rather than a maximization problem.
        Configuration current = cop.getInitialCandidate();
        ArrayList<Configuration> neighbors = cop.getNeighbors(current);

        // indices saves the indices of the configs with the lowest h values
        ArrayList<Integer> indices = new ArrayList<>();
        int h_current = cop.h(current);
        int h_min = h_current;

        // Iterates over all neighbors
        for (Configuration n : neighbors) {
            int h_n = cop.h(n);
            // Case 1: h is lower than h_min
            // ==> Overwrite h_min, clear indices and add h_min's index to the list
            if (h_n < h_min) {
                h_min = h_n;
                indices.clear();
                indices.add(neighbors.indexOf(n));
            }
            // Case 2: h is equal to h_min
            // ==> Add h's index to list
            else if (h_n == h_min) {
                indices.add(neighbors.indexOf(n));
            }
            // Case 3: h is higher than h_min
            // ==> Ignore and continue with loop
        }
        // Find neighbor with lowest h
        if (indices.size() == 1) {
            Configuration next = neighbors.get(indices.get(0));
        }
        else {
            // There are multiple neighbors with the same h value
            // ==> Choose one at random
            Random rand = new Random();
            int randomIndex = rand.nextInt(indices.size() - 1);
            Configuration next = neighbors.get(randomIndex);
        }

        if (h_min >= h_current) {
            // TODO: Return current
        }
        current = next;

        return null;
    }
    
    public static void main(String args[]) {
        HillClimbing hc = new HillClimbing(args);
        int succ = 0;
        int steps = 0;
        for (int i = 0; i < 1000; i++) {
            SearchResult result = hc.search();
            if (result.success) {
                succ++;
            }
            steps += result.steps;
        }
        System.out.println("Percentage of successful runs: " + succ/10.0 + "%");
        System.out.println("Average number of steps: " + steps/1000.0);
    }
}
