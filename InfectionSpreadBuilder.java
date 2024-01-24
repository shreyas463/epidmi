package projectfi;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.random.RandomHelper;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.context.space.graph.NetworkBuilder;

public class InfectionSpreadBuilder implements ContextBuilder<Object> {

    @Override
    public Context<Object> build(Context<Object> context) {
        context.setId("projectfi");

        ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
        ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context,
                new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), 50, 50);
        GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
        Grid<Object> grid = gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>(
                new WrapAroundBorders(), new SimpleGridAdder<Object>(), true, 50, 50));

        int totalAgents = 55; // Total number of agents (60 healthy + 5 infected)
        int averageDegree = 10; // Average degree for Watts-Strogatz graph
        double rewiringProb = 0.2; // Rewiring probability for Watts-Strogatz graph

        WattsStrogatzGraph wattsStrogatzGraph = new WattsStrogatzGraph(totalAgents, averageDegree, rewiringProb);

        for (int i = 0; i < totalAgents; i++) {
            NdPoint position = wattsStrogatzGraph.getNodePosition(i);

            if (i < 5) { // First 5 nodes are infected humans
                InfectedHuman infected = new InfectedHuman(space, grid);
                context.add(infected);
                space.moveTo(infected, position.getX(), position.getY());
                grid.moveTo(infected, (int) position.getX(), (int) position.getY());
            } else { // Rest are healthy humans
                HealthyHuman healthy = new HealthyHuman(space, grid);
                context.add(healthy);
                space.moveTo(healthy, position.getX(), position.getY());
                grid.moveTo(healthy, (int) position.getX(), (int) position.getY());
            }
        }

        for (Object obj : context) {
            NdPoint pt = space.getLocation(obj);
            grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
        }

        return context;
    }
}
