import java.util.ArrayList;
import java.util.Random;

public class EightQueensProblem implements CombinatorialOptimizationProblem {
    private class EightQueensConfiguration  implements Configuration {
        public int[] pos;

        public EightQueensConfiguration(int[] pos) {
            this.pos = pos;
        }

        public void dump() {
            System.out.println(" -------- ");
            for (int i = 0; i < 8; i++) {
                System.out.print("|");
                for (int j = 0; j < 8; j++) {
                    if (j == this.pos[i]) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println("|");
            }
            System.out.println(" -------- ");
        }
    }
    
    public Configuration getInitialCandidate() {
        int[] pos = new int[8];
        Random rand = new Random();
        for (int i = 0; i < pos.length; i++) {
            pos[i] = rand.nextInt(8);
        }
        return new EightQueensConfiguration(pos);
    }
    
    public ArrayList<Configuration> getNeighbors(Configuration _conf) {
        EightQueensConfiguration conf = (EightQueensConfiguration)_conf;
        ArrayList<Configuration> result = new ArrayList<Configuration>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == conf.pos[i]) {
                    continue;
                }
                int[] copy = new int[8];
                System.arraycopy(conf.pos, 0, copy, 0, 8);
                copy[i] = j;
                result.add(new EightQueensConfiguration(copy));
            }            
        }
        return result;
    }
    
    public int h(Configuration _conf) {
        // TODO: Implement the heuristic for the 8 queens problem that
        // has been presented in the lecture (count the number of pairs
        // of queens threatening each other)
    }

    public boolean isSolution(Configuration conf) {
        return (this.h(conf) == 0);
    }
}
