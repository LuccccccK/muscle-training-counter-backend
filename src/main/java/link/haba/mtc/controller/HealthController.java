package link.haba.mtc.controller;

import java.util.HashMap;

import org.apache.http.HttpStatus;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class HealthController implements IController {

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = this.initializeResponse();
        response.setStatusCode(HttpStatus.SC_OK);
        return response;
    }
    
    // APIGatewayProxyResponseEvent を初期化して返します
    private APIGatewayProxyResponseEvent initializeResponse() {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Headers", "Content-Type");
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
        response.setHeaders(headers);
        return response;
    }
}
