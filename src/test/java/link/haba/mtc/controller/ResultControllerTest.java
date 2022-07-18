package link.haba.mtc.controller;

import org.junit.Test;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import link.haba.mtc.application.usecase.IResultUsecase;
import link.haba.mtc.domain.model.MuscleTrainingCount;

public class ResultControllerTest {
    @Test
    public void testHandle() {
        ResultUsecaseTest uc = new ResultUsecaseTest();
        ResultController c = new ResultController(uc);

        APIGatewayProxyRequestEvent e = new APIGatewayProxyRequestEvent();
        e.setPath("/api/result");
        e.setHttpMethod(HttpMethod.POST.toString());
        e.setBody("{\"selectedDate\":\"2022-07-18\",\"countPushUp\":30,\"countAbdominalMuscles\":0,\"countSquat\":0}");

        c.handle(e);
    }

    private class ResultUsecaseTest implements IResultUsecase {

        @Override
        public MuscleTrainingCount get(String date) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void regist(MuscleTrainingCount m) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void update(MuscleTrainingCount m) {
            // TODO Auto-generated method stub
            
        }
        
    }
}
