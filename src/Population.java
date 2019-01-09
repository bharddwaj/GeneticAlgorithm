import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
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

    public DNA acceptReject(float maxFitness){
        Random rand = new Random();
        System.out.println("max fitness" + maxFitness);
            while (true) {
                int index = rand.nextInt(this.Population.size());
                System.out.println(index);
                DNA partner = this.Population.get(index);
                int r = rand.nextInt((int) (maxFitness));
                if (r < partner.fitness) {
                    return partner;
                }
            }

    }

    public void generate(){
        this.calcFitness();
        Float [] fitnessArray = new Float[Population.size()];
        float maxFitness = 0;
        float fitness = 0;
        float sum = 0;

        for (int i = 0; i < Population.size(); i++) {
            fitness = Population.get(i).fitness;
            fitnessArray[i] = fitness;
            if (fitness > maxFitness){
                maxFitness = fitness;
            }
            sum += fitness;
        }

        float arrayMax = Collections.max(Arrays.asList(fitnessArray));
        float arrayMin =  Collections.min(Arrays.asList(fitnessArray));
        float average = sum/((float)(fitnessArray.length));

        Random r = new Random();
        //int popsize = this.Population.size();
        this.matingPool.clear();

        for (int i = 0; i < this.Population.size(); i++) {

            DNA partnerA = this.acceptReject(arrayMax);
            DNA partnerB = this.acceptReject(arrayMax);

            DNA childZero = partnerA.crossover(partnerB,0);

            this.matingPool.add(childZero);
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


        }
        this.Population.clear();
        for(int i = 0; i < this.matingPool.size(); i ++){
            this.Population.add(this.matingPool.get(i));
        }
        System.out.println("population size " + this.Population.size() );
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
            if (this.Population.get(i).perfect){
                this.finished = true;
            }
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
