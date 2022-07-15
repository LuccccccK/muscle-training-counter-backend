package link.haba.mtc.application.usecase;

import link.haba.mtc.domain.model.MuscleTrainingCount;

public interface IResultUsecase {
    public void get(String date);
    public void regist(MuscleTrainingCount m);
    public void update(MuscleTrainingCount m);
}
