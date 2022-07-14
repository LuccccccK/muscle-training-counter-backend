package link.haba.mtc.application.usecase;

import link.haba.mtc.domain.model.MuscleTrainingCount;
import link.haba.mtc.domain.service.IResultService;

public class ResultUsecase implements IResultUsecase {

    private IResultService s;

    public ResultUsecase(IResultService s) {
        this.s = s;
    }
    
    public void update() {
        System.out.println("Start: ResultUsecase - update");
        MuscleTrainingCount m = new MuscleTrainingCount();
        s.update(m);
    }

    @Override
    public void regist() {
        // TODO Auto-generated method stub
        
    }
}
