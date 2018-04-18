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
        // rather than a maximiztion problemm.
        Configuration rootConf = cop.getInitialCandidate();
        ArrayList<Configuration> neighbors = cop.getNeighbors(rootConf);

        int temp_low = cop.h(rootConf);

        for (Configuration a:
             neighbors) {
            int zwisch_low = cop.h(a);
            if (zwisch_low < temp_low) {
                temp_low = zwisch_low;
            }
        }
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
