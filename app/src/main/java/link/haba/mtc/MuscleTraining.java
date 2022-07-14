package link.haba.mtc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;


public class MuscleTraining implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  static DynamoDB dynamoDB = new DynamoDB(client);

  // handler 実装における参考サイト：https://qiita.com/blueband/items/d033720f9f5d0dcd2351
  // pointは、Lambda Proxy 統合にしないとパスパラメータなどが取得できず、
  // 複数のLmabda関数を実装する必要が出てくる。
  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent e, Context ctx) {

    // ログ出力
    System.out.println("event: " + e);
    System.out.println("context: " + ctx);
    System.out.println("body: " + e.getBody());
    System.out.println("path: " + e.getPath());
    System.out.println("method: " + e.getHttpMethod());
    System.out.println("header: " + e.getHeaders());
    System.out.println("query: " + e.getQueryStringParameters());
    
    // try {
    //   ObjectMapper om = new ObjectMapper();
    //   ob = om.readValue(in.json, Json.class);
    //   putItem(ob);
    // } catch (Exception e) {
    //   System.err.println(e.getMessage());
    // }

    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setStatusCode(200);
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");
    response.setHeaders(headers);
    response.setBody("{\"test\": \"body\"}");
    return response;
  }

  // TODO: sample of dynamodb put item
  private static void putItem(Json ob) {

    String tableName = "muscle-training-result";
    Table table = dynamoDB.getTable(tableName);

    try {
      System.out.println("Adding data to " + tableName);
      Item item = new Item().withPrimaryKey("date", ob.selectedDate)
          .withNumber("countAbdominalMuscles", ob.countAbdominalMuscles)
          .withNumber("countPushUp", ob.countPushUp)
          .withNumber("countSquat", ob.countSquat);
      table.putItem(item);
    }
    catch (Exception e) {
      System.err.println("Failed to create item in " + tableName);
      System.err.println(e.getMessage());
    }
}

  private static class Json {
    public String selectedDate;
    public Integer countAbdominalMuscles;
    public Integer countPushUp;
    public Integer countSquat;
  }
}