/*
Interface rpresentant les actions faisable par un worker
 */

public interface IWorkerActions {
    void moveTo(WorkerAgent wa, Tile tile);
    void buildAmenagment(Tile tile);
}
