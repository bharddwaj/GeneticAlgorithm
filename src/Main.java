
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {

        String target = "hello I don't think this is efficient";
        int popmax = 2000;
    //    float mutationRate =  50 / 100; thats rlly funny
        float mutationRate = .02f;
        Population population = new Population(target, popmax, mutationRate);


        ArrayList<DNA> pop = population.getPopulation();
      /*  System.out.println("Printing the DNA");
        for (int j = 0; j < pop.size(); j ++) {
            System.out.println(pop.get(j));
        } */
        population.evaluate();

        while (!population.getFinished()) {



            // Generate mating pool

            //Create next generation
            population.generate();
            // Calculate fitness

            population.calcFitness();

            population.evaluate();
            System.out.println("Number of Generations: " + population.getGenerations());

        }

        System.out.println("Number of Generations: " + population.getGenerations());
        System.out.println("Size of final Population" + population.getPopulationListSize());
        System.out.println("Number of Mutation Count " + population.getMutationCount());
        System.out.println("Number of Children " + population.getChildren());

    }
}

