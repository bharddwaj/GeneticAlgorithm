import java.util.Random;
public class DNA {
    // The genetic sequence
    char[] genes;
    char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
    float fitness;
    boolean perfect;
    // Constructor (makes a random DNA)
    public DNA(int num) {
        Random r = new Random();
        this.genes = new char[num];
        this.fitness = 0;
        for (int i = 0; i < num; i++) {
            this.genes[i] = alpha[r.nextInt(27)];  // Pick from range of chars
        }
    }
    public DNA (){

    }

    // Converts character array to a String
    public String getPhrase() {
        String genes = new String(this.genes);
        return genes;
    }




    // Fitness function (returns floating point % of "correct" characters)
    public float calcFitness(String target, DNA d) {
        float score = 0;
        for (int i = 0; i < d.genes.length; i++) {
            if (d.genes[i] == Character.toLowerCase(target.charAt(i))) {
                score++;
                }
            }
        d.fitness = score / target.length();
        if (d.fitness >= 1.0){
            d.perfect = true;
        }
       // float fitnessValue =(float)(Math.pow((double)(2),(double)(d.fitness*10)));
        //float fitnessValue =(float)(Math.pow((double)(d.fitness*10),(double)(2)));
        //d.fitness = fitnessValue;
       // System.out.println("fitness " + fitnessValue );
        return d.fitness;
    }

        // Crossover
      public DNA  crossover(DNA partner,int childNumber) {
            // A new child
            DNA child = new DNA(this.genes.length);
            Random r = new Random();
            int midpoint = r.nextInt(this.genes.length); // Pick a midpoint

            // Half from one, half from the other
            if (childNumber == 0) {
              for (int i = 0; i < this.genes.length; i++) {
                  if (i > midpoint) child.genes[i] = this.genes[i];
                  else child.genes[i] = partner.genes[i];
              }
              return child;
          }
            else{
                for (int i = 0; i < this.genes.length; i++) {
                    if (i > midpoint) child.genes[i] = partner.genes[i];
                    else child.genes[i] = this.genes[i];
                }
                return child;
            }
        }

        // Based on a mutation probability, picks a new random character
       public boolean  mutate(float mutationRate) {
            Random r = new Random();
            boolean mutationHappened = false;
            for (int i = 0; i < this.genes.length; i++) {
                float randomFloat = r.nextInt(100);
                if (randomFloat <= mutationRate*100) {
                    this.genes[i] = alpha[r.nextInt(27)];
                    mutationHappened = true;

                }
            }
            return mutationHappened;
        }

        public String toString(){
            String something = "";
            for (int i = 0; i < this.genes.length;i++){
                something += (this.genes[i]);
            }
            return something;
        }

    }

