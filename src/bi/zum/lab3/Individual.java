package bi.zum.lab3;

import cz.cvut.fit.zum.api.ga.AbstractEvolution;
import cz.cvut.fit.zum.api.ga.AbstractIndividual;
import cz.cvut.fit.zum.api.Node;
import cz.cvut.fit.zum.data.StateSpace;
import cz.cvut.fit.zum.data.Edge;
import cz.cvut.fit.zum.util.Pair;
import java.util.ArrayList;
import java.util.Random;

public class Individual extends AbstractIndividual {

    private double fitness = Double.NaN;
    private AbstractEvolution evolution;

    ArrayList<Boolean> visited_nodes = new ArrayList<Boolean>();

    public Individual(AbstractEvolution evolution, boolean randomInit) {
        this.evolution = evolution;

        if (randomInit) {
            for (int x = 0; x < StateSpace.nodesCount(); x++) {
                //we will decide whether if the node is visited or not.
                Random val = new Random();
                visited_nodes.add(val.nextBoolean());
                //System.out.println(visited_nodes.get(x));
            }
        }
    }

    public boolean isNodeSelected(int j) {
        return visited_nodes.get(j);
    }



    public void computeFitness() {
        // Hint: use the StateSpace object
        double fe = 0;
        double fn = 0;
        for (Edge e : StateSpace.getEdges()) {
            if (isNodeSelected(e.getFromId()) || isNodeSelected(e.getToId())) {
                fe++;
            }

        }
        for (Node n : StateSpace.getNodes()) {
            if (!isNodeSelected(n.getId())) {
                fn++;
            }
        }
        fitness = ((fe / StateSpace.edgesCount()) * 85.0) + ((fn / StateSpace.nodesCount()) * 15.0);
    }
    public double getFitness() {
        return this.fitness;
    }

    public void mutate(double mutationRate) {

        Random r = new Random();
        int j= r.nextInt(visited_nodes.size()-1);
        this.visited_nodes.set(j, !this.visited_nodes.get(j));

    }

    @Override
    public Pair crossover(AbstractIndividual other) {
        Pair<Individual, Individual> result = new Pair();
        int mid = visited_nodes.size() / 2;
        Individual i = (Individual) other;

        result.a.visited_nodes.addAll(this.visited_nodes.subList(0, mid));
        result.a.visited_nodes.addAll(i.visited_nodes.subList(mid + 1, i.visited_nodes.size()-1));
        result.b.visited_nodes.addAll(i.visited_nodes.subList(0, mid));
        result.b.visited_nodes.addAll(this.visited_nodes.subList(mid + 1, this.visited_nodes.size()-1));

        return result;
    }

    @Override
    public Individual deepCopy() {
        Individual newOne = new Individual(evolution, false);

        for (int i = 0; i < this.visited_nodes.size(); i++) {
            newOne.visited_nodes.add(this.visited_nodes.get(i)); // object

        }

        newOne.fitness = this.fitness;
        return newOne;
    }
}