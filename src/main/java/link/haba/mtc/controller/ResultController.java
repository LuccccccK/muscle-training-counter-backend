package link.haba.mtc.controller;

import java.util.HashMap;
import java.util.Map;

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
            case "GET":
                return this.get(e);
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

    // Get Method
    private APIGatewayProxyResponseEvent get(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = this.initializeResponse();

        Map<String, String> q = e.getQueryStringParameters();
        MuscleTrainingCount m = this.uc.get(q.get("selectedDate"));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(m);
            response.setBody(json);
            response.setStatusCode(HttpStatus.SC_OK);
        } catch (Exception ex) {
            System.err.println("Class Object を JSON 文字列に変換できませんでした");
            response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    // Post Method
    private APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent e) {
        APIGatewayProxyResponseEvent response = this.initializeResponse();

        String b = e.getBody();
        if (b == "") {
            System.err.println("リクエストボディが取得できませんでした");
            response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
            return response;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultPostModel rpm = objectMapper.readValue(b, ResultPostModel.class);
            MuscleTrainingCount m = new MuscleTrainingCount(rpm.selectedDate);
            m.countAbdominalMuscles = rpm.countAbdominalMuscles;
            m.countPushUp = rpm.countPushUp;
            m.countSquat = rpm.countSquat;
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
        APIGatewayProxyResponseEvent response = this.initializeResponse();
        
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

    // Result Controller Post Model
    private class ResultPostModel {
        public String selectedDate;
        public Integer countAbdominalMuscles;
        public Integer countPushUp;
        public Integer countSquat;
    }
}
