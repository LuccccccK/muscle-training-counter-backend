package link.haba.mtc.infrastructure;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import link.haba.mtc.domain.model.MuscleTrainingCount;

public class AWSDynamoDB {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  static DynamoDB dynamoDB = new DynamoDB(client);

  // TODO DBへの保存とEntityの組み立てが混在しているので分離した方がよい
  public static void putItem(String tblName, MuscleTrainingCount m) {
    Table tbl = dynamoDB.getTable(tblName);

    try {
      System.out.println("Adding data to " + tbl.getTableName());
      Item item = new Item().withPrimaryKey("date", m.selectedDate)
          .withNumber("countAbdominalMuscles", m.countAbdominalMuscles)
          .withNumber("countPushUp", m.countPushUp)
          .withNumber("countSquat", m.countSquat);
      tbl.putItem(item);
    }
    catch (Exception e) {
      System.err.println("Failed to create item in " + tbl.getTableName());
      System.err.println(e.getMessage());
    }
  }
}
