package projectfi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;
import repast.simphony.util.SimUtilities;

public class InfectedHuman {
    private ContinuousSpace<Object> space;
    private Grid<Object> grid;
    private static double TRANSMISSION_PROBABILITY = 0.1;

    public InfectedHuman(ContinuousSpace<Object> space, Grid<Object> grid) {
        this.space = space;
        this.grid = grid;
    }
    
    @ScheduledMethod(start = 1, interval = 1)
    public void step() {
        GridPoint pt = grid.getLocation(this);
        GridCellNgh<HealthyHuman> nghCreator = new GridCellNgh<HealthyHuman>(grid, pt, HealthyHuman.class, 1, 1);
        List<GridCell<HealthyHuman>> gridCells = nghCreator.getNeighborhood(true);
        SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

        GridPoint pointWithMostHumans = null;
        int maxCount = -1;
        for (GridCell<HealthyHuman> cell : gridCells) {
            if (cell.size() > maxCount) {
                pointWithMostHumans = cell.getPoint();
                maxCount = cell.size();
            }
        }

        moveTowards(pointWithMostHumans);

        if (new Random().nextDouble() <= TRANSMISSION_PROBABILITY) {
            infect();
        }
    }

    public void moveTowards(GridPoint pt) {
        if (!pt.equals(grid.getLocation(this))) {
            NdPoint myPoint = space.getLocation(this);
            NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
            double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
            space.moveByVector(this, 1, angle, 0);
            myPoint = space.getLocation(this);
            grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
        }
    }

    public void infect() {
        GridPoint pt = grid.getLocation(this);
        List<Object> healthyHumans = new ArrayList<Object>();
        for (Object obj : grid.getObjectsAt(pt.getX(), pt.getY())) {
            if (obj instanceof HealthyHuman) {
                healthyHumans.add(obj);
            }
        }
        if (healthyHumans.size() > 0) {
            int index = RandomHelper.nextIntFromTo(0, healthyHumans.size() - 1);
            Object obj = healthyHumans.get(index);
            NdPoint spacePt = space.getLocation(obj);
            Context<Object> context = ContextUtils.getContext(obj);
            context.remove(obj);
            InfectedHuman infectedHuman = new InfectedHuman(space, grid);
            context.add(infectedHuman);
            space.moveTo(infectedHuman, spacePt.getX(), spacePt.getY());
            grid.moveTo(infectedHuman, pt.getX(), pt.getY());
        }
    }
}
