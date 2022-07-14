package link.haba.mtc;

import org.apache.http.HttpStatus;

import link.haba.mtc.application.usecase.ResultUsecase;
import link.haba.mtc.application.usecase.IResultUsecase;
import link.haba.mtc.controller.IController;
import link.haba.mtc.controller.ResultController;
import link.haba.mtc.domain.repository.IMuscleTrainingCountRepo;
import link.haba.mtc.domain.service.IResultService;
import link.haba.mtc.domain.service.ResultService;
import link.haba.mtc.infrastructure.repository.MuscleTrainingCountRepo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class MuscleTraining implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private IController c = null;

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
    
    // ルーティングをここで定義し、依存関係を注入（予定）
    switch (e.getPath()) {
      case "/api/result":
        IMuscleTrainingCountRepo repo = new MuscleTrainingCountRepo(null);
        IResultService s = new ResultService(repo);
        IResultUsecase uc = new ResultUsecase(s);
        c = new ResultController(uc);
        
    }

    // リソースが未定義の場合は、Not Found ステータスを返して終了
    // 本来はAPI Gateway側でも未定義のリソースとなるため、ここを通過するケースは想定外
    if (c == null) {
      final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
      response.setIsBase64Encoded(false);
      response.setStatusCode(HttpStatus.SC_NOT_FOUND);
      return response;
    }
    return c.handle(e);
  }
}