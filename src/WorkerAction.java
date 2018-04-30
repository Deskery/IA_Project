public class WorkerAction implements IWorkerActions {

    @Override
    public void moveTo(WorkerAgent wa, Tile tileDestination) {
        wa.setCurrentTile(tileDestination);
    }

    @Override
    public void buildAmenagment(Tile tile) {
        tile.setAmenagement(true);
    }
}
