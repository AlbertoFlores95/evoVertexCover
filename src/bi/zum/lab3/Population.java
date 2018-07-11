package bi.zum.lab3;

import cz.cvut.fit.zum.api.ga.AbstractEvolution;
import cz.cvut.fit.zum.api.ga.AbstractIndividual;
import cz.cvut.fit.zum.api.ga.AbstractPopulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population extends AbstractPopulation {

    public Population(AbstractEvolution evolution, int size) {

        individuals = new Individual[size];

        for (int i = 0; i < individuals.length; i++) {

            individuals[i] = new Individual(evolution, true);
            individuals[i].computeFitness();

        }

    }


    public List<AbstractIndividual> selectIndividuals(int count) {

        ArrayList<AbstractIndividual> selected = new ArrayList<>();

        while (selected.size() < count) {

            Random r = new Random();

            AbstractIndividual i = individuals[r.nextInt(individuals.length)];
            AbstractIndividual j = individuals[r.nextInt(individuals.length)];

            if (i.getFitness() < j.getFitness()) {
                selected.add(j);
            } else {
                selected.add(i);
            }
        }

        return selected;
    }
}