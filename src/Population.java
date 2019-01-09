import java.util.ArrayList;
import java.util.Random;
public class Population {
    private ArrayList<DNA> Population;
    private String [] alphabet ={ "A", "B" ,"C", "D", "E", "F", "G", "H", "I","J", "K","L", "M", "N","O", "P","Q" ,"R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
    private ArrayList<String> Basic;
    private float mutationRate;
    private int PopulationSize;
    private String target;
    private ArrayList<DNA> matingPool;
    private int generations;
    private boolean finished;
    private int perfectScore;
    private double worldRecord;
    private int mutationCount;
    private int children;

    public Population(String target, int PopulationSize, float mutationRate){
        this.Population = new ArrayList<DNA>();
        this.target = target;
        this.PopulationSize = PopulationSize;
        this.mutationRate = mutationRate;
        Random r = new Random();
        for (int i = 0; i < this.PopulationSize; i++) {

            this.Population.add(new DNA(target.length())); }

            this.calcFitness();
        this.matingPool = new ArrayList<DNA>();
        this.generations = 0;
        this.perfectScore = 1;
        this.finished = false;


    }
    public void calcFitness() {
        DNA c = new DNA();

        for (int i = 0; i < this.Population.size(); i++) {
            c.calcFitness(this.target,this.Population.get(i));
        }
    }

    // Generate a mating pool
    public void naturalSelection() {
        // Clear the ArrayList
        matingPool.clear();

        float maxFitness = 0;
        for (int i = 0; i < Population.size(); i++) {
            if (Population.get(i).fitness > maxFitness) {
                maxFitness = Population.get(i).fitness;
            }
        }

        // Based on fitness, each member will get added to the mating pool a certain number of times
        // a higher fitness = more entries to mating pool = more likely to be picked as a parent
        // a lower fitness = fewer entries to mating pool = less likely to be picked as a parent
        if (maxFitness != 0) {
            for (int i = 0; i < Population.size(); i++) {

                //float fitness = Population[i].fitness,0,maxFitness,0,1);
                int n = (int) (Population.get(i).fitness * 100);  // Arbitrary multiplier, we can also use monte carlo method
                for (int j = 0; j < n; j++) {              // and pick two random numbers
                    matingPool.add(Population.get(i));
                }
            }
        }
        else{
            for (int i = 0; i < Population.size(); i++) {
                matingPool.add(Population.get(i));
            }
        }
    }

    public void generate(){
        Random r = new Random();
        int popsize = this.Population.size();
        this.Population.clear();
        for (int i = 0; i < popsize; i++) {
            int a = r.nextInt(this.matingPool.size());
            int b = r.nextInt(this.matingPool.size());
            DNA childZero = this.matingPool.get(a).crossover(this.matingPool.get(b),0);
            this.Population.add(childZero);
            this.children ++;
            boolean mutationZero = childZero.mutate(this.mutationRate);
           // System.out.println(mutationZero);
            if (mutationZero){
                this.mutationCount++;
            }
         /*   DNA childOne= this.matingPool.get(a).crossover(this.matingPool.get(b),1);
            this.Population.add(childOne);
            this.children ++;
            boolean mutationOne = childOne.mutate(this.mutationRate);
            if (mutationOne){
                this.mutationCount++;
            } */
            this.Population.add(childZero);

        }
        this.generations++;

    }

    public void evaluate() {
       // double worldrecord = 0.0;
        this.worldRecord = 0;
        int index = 0;
        for (int i = 0; i < this.Population.size(); i++) {
            if (this.Population.get(i).fitness > this.worldRecord) {
                index = i;
                this.worldRecord = this.Population.get(i).fitness;
            }
        }

      //  this.best = this.Population[index].getPhrase();
        if (this.worldRecord == this.perfectScore) {
            this.finished = true;
        }

    }

    //Getters

    public ArrayList<DNA> getPopulation(){
        return this.Population;
    }

    public boolean getFinished(){
        return this.finished;
    }
    public int getGenerations(){
        return this.generations;
    }
    public double getWorldRecord(){
        return this.worldRecord;
    }
    public int getPopulationListSize(){
        return this.Population.size();
    }
    public int getMutationCount(){
        return mutationCount;
    }
    public int getChildren(){ return children;}

}
