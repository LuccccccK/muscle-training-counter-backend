package link.haba.mtc.domain.repository;

import link.haba.mtc.domain.model.MuscleTrainingCount;

public interface IMuscleTrainingCountRepo {
    public void update(MuscleTrainingCount m);
    public void regist(MuscleTrainingCount m);
}
