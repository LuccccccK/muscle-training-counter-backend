package link.haba.mtc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import link.haba.mtc.Sample.Input;
import link.haba.mtc.Sample.Output;

public class Sample implements RequestHandler<Input, Output> {

  @Override
  public Output handleRequest(Input in, Context context) {

    final Output out = new Output();
    out.in = in;
    out.fullName = in.firstName + "_" + in.lastName;

    return out;
  }

  public static class Input {
    public String firstName;
    public String lastName;
  }

  public static class Output {
    public Input in;
    public String fullName;

  }

}