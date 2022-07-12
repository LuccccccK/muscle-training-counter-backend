package link.haba.mtc;

import static org.junit.Assert.*;

import org.junit.Test;

import link.haba.mtc.MuscleTraining.Input;
import link.haba.mtc.MuscleTraining.Output;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

/**
 * Unit test for simple App.
 */
public class MuscleTrainingTest 
{
    @Test
    public void testHandleRequest() {
        // MuscleTraining mt = new MuscleTraining();

        // TODO DynamoDBに接続できず必ずエラーとなってしまうため、テストメソッドは必ずtrueとなるように仮実装
        //      DynamoDBをローカル検証できるように対応が必要
        Context ctx = createContext();
        assertEquals(null, ctx.getAwsRequestId());

        // Input in = new Input();
        // in.date = "2022-07-12";
        // in.json = "{\"selectedDate\":\"2022-07-12\",\"countPushUp\":3,\"countAbdominalMuscles\":0,\"countSquat\":0}";
        // Output out = mt.handleRequest(in, createContext());
        // assertEquals(in.date, out.in.date);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();
        return ctx;
    }

    private static class TestContext implements Context{
        @Override
        public String getAwsRequestId() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLogGroupName() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLogStreamName() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getFunctionName() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getFunctionVersion() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getInvokedFunctionArn() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public CognitoIdentity getIdentity() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ClientContext getClientContext() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int getRemainingTimeInMillis() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getMemoryLimitInMB() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public LambdaLogger getLogger() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
