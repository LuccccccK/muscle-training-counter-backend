package link.haba.mtc.domain.service;

import link.haba.mtc.domain.model.MuscleTrainingCount;
import link.haba.mtc.domain.repository.IMuscleTrainingCountRepo;

public class ResultService implements IResultService {

    private IMuscleTrainingCountRepo repo;

    public ResultService(IMuscleTrainingCountRepo repo) {
        this.repo = repo;
    }

    @Override
    public void update(MuscleTrainingCount m) {
        System.out.println("Start: ResultService - update");
        repo.update(m);
    }

    @Override
    public void regist(MuscleTrainingCount m) {
        System.out.println("Start: ResultService - regist");
        repo.regist(m);
    }
}
