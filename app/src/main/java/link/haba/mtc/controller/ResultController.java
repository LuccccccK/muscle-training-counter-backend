package link.haba.mtc.controller;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpStatus;

import link.haba.mtc.application.usecase.IResultUsecase;
import link.haba.mtc.domain.model.MuscleTrainingCount;

public class ResultController implements IController {

    private IResultUsecase uc;

    public ResultController(IResultUsecase uc) {
        this.uc = uc;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent e) {
        switch (e.getHttpMethod()) {
            case "POST":
                return this.post(e);
            case "PUT":
                return this.put(e);
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
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        response.setHeaders(headers);

        String b = e.getBody();
        if (b == "") {
            System.err.println("リクエストボディが取得できませんでした");
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
            return response;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MuscleTrainingCount m = objectMapper.readValue(b, MuscleTrainingCount.class);
            this.uc.regist(m);
            response.setStatusCode(HttpStatus.SC_OK);
        } catch (Exception ex) {
            System.err.println("MuscleTrainingCount object への Parseに失敗しました");
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
        }
        return response;
    }

    // Put Method
    private APIGatewayProxyResponseEvent put(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        response.setHeaders(headers);

        String b = e.getBody();
        if (b == "") {
            System.err.println("リクエストボディが取得できませんでした");
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
            return response;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MuscleTrainingCount m = objectMapper.readValue(b, MuscleTrainingCount.class);
            this.uc.update(m);
            response.setStatusCode(HttpStatus.SC_OK);
        } catch (Exception ex) {
            System.err.println("MuscleTrainingCount object への Parseに失敗しました");
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
        }
        return response;
    }
}
