package link.haba.mtc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Unit test for simple App.
 */
public class MuscleTrainingTest 
{
    @Test
    public void testHandleRequest() {
        MuscleTraining mt = new MuscleTraining();
        
        APIGatewayProxyRequestEvent e = new APIGatewayProxyRequestEvent();
        e.setPath("/api/result");
        e.setHttpMethod("POST");

        Context ctx = createContext();
        
        APIGatewayProxyResponseEvent res = mt.handleRequest(e, ctx);
        assertEquals("{\"test\": \"body\"}", res.getBody());
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
