package link.haba.mtc.controller;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import org.apache.http.HttpStatus;

public class ResultController implements IController {

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent e) {
        switch (e.getHttpMethod()) {
            case "POST":
                return this.post(e);
            default:
                System.out.println("未実装の処理です");
                
                final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
                response.setIsBase64Encoded(false);
                response.setStatusCode(HttpStatus.SC_NOT_IMPLEMENTED);
                return response;
        }
    }
    
    // Post Method
    private APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        response.setHeaders(headers);
        response.setBody("{\"test\": \"body\"}");
        return response;
    }
}
