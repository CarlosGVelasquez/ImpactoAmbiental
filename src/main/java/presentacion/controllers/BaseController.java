package presentacion.controllers;
import org.eclipse.jetty.http.HttpStatus;
import presentacion.security.UserClaims;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static shared.json.Json.toJson;

public abstract class BaseController {
  protected static Map<String, UserClaims> sessions = new HashMap<>();

  protected String ok(Object body, Response res) {
    res.status(HttpStatus.OK_200);
    return toJson(body);
  }

  protected String created(Object body, Response res){
    res.status(HttpStatus.CREATED_201);
    return toJson(body);
  }

  protected String noContent(Object body, Response res) {
    res.status(HttpStatus.NO_CONTENT_204);
    return toJson(body);
  }

  protected String conflict(String message, Response res) {
    res.status(HttpStatus.CONFLICT_409);
    String body = toJson(new ErrorResponse(HttpStatus.CONFLICT_409, message));
    res.body(body);
    return body;
  }

  protected String badRequest(String message, Response res) {
    res.status(HttpStatus.BAD_REQUEST_400);
    String body = toJson(new ErrorResponse(HttpStatus.BAD_REQUEST_400, message));
    res.body(body);
    return body;
  }

  protected String notFound(String message, Response res) {
    res.status(HttpStatus.NOT_FOUND_404);
    String body = toJson(new ErrorResponse(HttpStatus.NOT_FOUND_404, message));
    res.body(body);
    return body;
  }

  protected String unprocessableEntity(String message, Response res) {
    res.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
    String body = toJson(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY_422, message));
    res.body(body);
    return body;
  }

  protected String unauthorized(String message, Response res) {
    res.status(HttpStatus.UNAUTHORIZED_401);
    String body = toJson(new ErrorResponse(HttpStatus.UNAUTHORIZED_401, message));
    res.body(body);
    return body;
  }

  protected String internalServerError(String message, Response res) {
    res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
    String body = toJson(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR_500, message));
    res.body(body);
    return body;
  }

  protected UserClaims getUserClaims(Request req) {
    String sessionId = req.headers("JSESSIONID");
    return sessions.get(sessionId);
  }

}

