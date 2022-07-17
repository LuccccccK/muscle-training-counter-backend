package link.haba.mtc.application.usecase;

import link.haba.mtc.domain.model.MuscleTrainingCount;
import link.haba.mtc.domain.service.IResultService;

public class ResultUsecase implements IResultUsecase {

    private IResultService s;

    public ResultUsecase(IResultService s) {
        this.s = s;
    }
    
    public void update(MuscleTrainingCount m) {
        System.out.println("Start: ResultUsecase - update");
        s.update(m);
    }

    @Override
    public void regist(MuscleTrainingCount m) {
        System.out.println("Start: ResultUsecase - regist");
        s.regist(m);
    }

    @Override
    public void get(String date) {
        System.out.println("Start: ResultUsecase - get");
        s.get(date);
    }
}
