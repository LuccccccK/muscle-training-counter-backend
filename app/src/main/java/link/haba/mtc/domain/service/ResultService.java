package link.haba.mtc.domain.service;

import link.haba.mtc.domain.repository.IMuscleTrainingCountRepo;

public class ResultService implements IResultService {

    private IMuscleTrainingCountRepo repo;

    public ResultService(IMuscleTrainingCountRepo repo) {
        this.repo = repo;
    }

    @Override
    public void hoge() {
        // TODO Auto-generated method stub
        System.out.println("domain service");
        repo.hoge();
    }
}
