public class Player {
    private int idPlayer;
    private Supervisor workerSupervisor;

    public Player(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Supervisor getWorkerSupervisor() {
        return workerSupervisor;
    }

    public void setWorkerSupervisor(Supervisor workerSupervisor) {
        this.workerSupervisor = workerSupervisor;
    }

}
