package presentacion.controllers;

import shared.enums.EnumMetadata;
import spark.Request;
import spark.Response;

import java.util.List;

public class MetadataController extends BaseController {

  public Object getSystemEnums(Request req, Response res) {
    return ok(EnumMetadata.getSystemEnumsModel(), res);
  }
}