package link.haba.mtc.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpStatus;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import link.haba.mtc.domain.model.MuscleTrainingCount;

public class SummaryController implements IController {

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent e) {
        switch (e.getHttpMethod()) {
            case "GET":
                return this.get(e);
            default:
                System.out.println("未実装の処理です");
                
                final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
                response.setIsBase64Encoded(false);
                response.setStatusCode(HttpStatus.SC_NOT_IMPLEMENTED);
                return response;
        }
    }

    // Get Method
    private APIGatewayProxyResponseEvent get(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = this.initializeResponse();

        // Map<String, String> q = e.getQueryStringParameters();
        MuscleTrainingCount o = new MuscleTrainingCount("2022-07-20");
        o.countAbdominalMuscles = 100;
        o.countPushUp = 200;
        o.countSquat = 300;

        MuscleTrainingCount o2 = new MuscleTrainingCount("2022-07-21");
        o.countAbdominalMuscles = 1;
        o.countPushUp = 2;
        o.countSquat = 3;

        ArrayList<MuscleTrainingCount> hoge = new ArrayList<MuscleTrainingCount>();
        hoge.add(o);
        hoge.add(o2);
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(hoge);
            response.setBody(json);
            response.setStatusCode(HttpStatus.SC_OK);
        } catch (Exception ex) {
            System.err.println("Class Object を JSON 文字列に変換できませんでした");
            response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
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
