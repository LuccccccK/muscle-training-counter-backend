package link.haba.mtc.controller;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface IController {
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent e);
}
