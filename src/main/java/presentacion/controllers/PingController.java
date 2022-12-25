package presentacion.controllers;

import spark.Request;
import spark.Response;

public class PingController extends BaseController {

  public Object ping(Request req, Response res) {
    return ok("pong", res);
  }
}
