package link.haba.mtc.application.interactor;

import link.haba.mtc.application.usecase.ResultUsecase;
import link.haba.mtc.domain.service.IResultService;

public class ResultInteractor implements ResultUsecase {

    private IResultService s;

    public ResultInteractor(IResultService s) {
        this.s = s;
    }
    
    public void hoge() {
        System.out.println("usecase 呼び出し");
        s.hoge();
    }
}
