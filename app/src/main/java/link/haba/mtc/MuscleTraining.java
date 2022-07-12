package link.haba.mtc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import link.haba.mtc.MuscleTraining.Input;
import link.haba.mtc.MuscleTraining.Output;

public class MuscleTraining implements RequestHandler<Input, Output> {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  static DynamoDB dynamoDB = new DynamoDB(client);

  // private Region REGION = Region.AP_NORTHEAST_1;

  @Override
  public Output handleRequest(Input in, Context context) {
    
    Json ob = null;
    
    try {
      ObjectMapper om = new ObjectMapper();
      ob = om.readValue(in.json, Json.class);
      putItem(ob);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    final Output out = new Output();
    out.in = in;
    out.ob = ob;
    
    return out;
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

  public static class Input {
    public String date;
    public String json;
  }

  public static class Output {
    public Input in;
    public Json ob;
  }

  private static class Json {
    public String selectedDate;
    public Integer countAbdominalMuscles;
    public Integer countPushUp;
    public Integer countSquat;
  }
}