package link.haba.mtc.infrastructure.repository;

import link.haba.mtc.domain.model.MuscleTrainingCount;
import link.haba.mtc.domain.repository.IMuscleTrainingCountRepo;
import link.haba.mtc.infrastructure.AWSDynamoDB;

public class MuscleTrainingCountRepo implements IMuscleTrainingCountRepo {

    private AWSDynamoDB db;

    public MuscleTrainingCountRepo(AWSDynamoDB db) {
        this.db = db;
    }

    @Override
    public void update(MuscleTrainingCount m) {
        System.out.println("Start: MuscleTrainingCountRepo - update");
        db.putItem("muscle-training-result", m);
    }

    @Override
    public void regist(MuscleTrainingCount m) {
        System.out.println("Start: MuscleTrainingCountRepo - regist");
        db.putItem("muscle-training-result", m);
    }
}
