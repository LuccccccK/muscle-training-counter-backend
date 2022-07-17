package link.haba.mtc.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Override
    public MuscleTrainingCount get(String date) {
        System.out.println("Start: MuscleTrainingCountRepo - get");
        String json = db.getItem("muscle-training-result", "date", date);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MuscleTrainingCount m = objectMapper.readValue(json, MuscleTrainingCount.class);
            return m;
        } catch (Exception e) {
            System.err.println("MuscleTrainingCount object への Parseに失敗しました");
            System.err.println("取得結果： " + json);
            return new MuscleTrainingCount();
        }
    }
}
