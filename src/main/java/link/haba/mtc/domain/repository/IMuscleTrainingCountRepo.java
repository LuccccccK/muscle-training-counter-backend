package link.haba.mtc.domain.repository;

import link.haba.mtc.domain.model.MuscleTrainingCount;
import link.haba.mtc.domain.model.entity.MuscleTrainingCountEntity;

public interface IMuscleTrainingCountRepo {
    public void update(MuscleTrainingCount m);
    public void regist(MuscleTrainingCount m);
    public MuscleTrainingCountEntity get(String date);
}
