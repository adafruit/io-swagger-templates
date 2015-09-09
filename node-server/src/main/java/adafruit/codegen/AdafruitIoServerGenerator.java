package adafruit.codegen;

import io.swagger.codegen.*;
import io.swagger.codegen.languages.*;
import io.swagger.models.properties.*;

import java.util.*;
import java.io.File;
import java.util.regex.Pattern;

public class AdafruitIoServerGenerator extends NodeJSServerCodegen {

  public AdafruitIoServerGenerator() {

    outputFolder = "build/server";
    setModelPackage("lib/models");
    setApiPackage("lib/controllers");

    /**
     * Models.  You can write model files using the modelTemplateFiles map.
     * if you want to create one template for file, you can do so here.
     * for multiple files for model, just put another entry in the `modelTemplateFiles` with
     * a different extension
     */
    modelTemplateFiles.clear();
    modelTemplateFiles.put("model.mustache", ".js");

    /**
     * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
     * as with models, add multiple entries with different extensions for multiple files per
     * class
     */
    apiTemplateFiles.clear();
    apiTemplateFiles.put("controller.mustache", ".js");
    /**
     * Template Location.  This is the location which templates will be read from.  The generator
     * will use the resource stream to attempt to read the templates.
     */
    templateDir = "adafruitIoServer";

    /**
     * Reserved words.  Override this with reserved words specific to your language
     */
    reservedWords = new HashSet<String>(
        Arrays.asList(
          "break", "case", "class", "catch", "const", "continue", "debugger",
          "default", "delete", "do", "else", "export", "extends", "finally",
          "for", "function", "if", "import", "in", "instanceof", "let", "new",
          "return", "super", "switch", "this", "throw", "try", "typeof", "var",
          "void", "while", "with", "yield")
        );

    /**
     * Supporting Files.  You can write single files for the generator with the
     * entire object tree available.  If the input file has a suffix of `.mustache
     * it will be processed by the template engine.  Otherwise, it will be copied
     */
    supportingFiles.clear();

  }

  @Override
  public String apiPackage() {
    return apiPackage;
  }

  public String singularize(String word) {
    return Pattern.compile("s$", 0).matcher(word).replaceAll("");
  }

  @Override
  public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
      @SuppressWarnings("unchecked")
      Map<String, Object> objectMap = (Map<String, Object>) objs.get("operations");
      String classname = (String) objectMap.get("classname");
      objectMap.put("modelname", singularize(classname));
      @SuppressWarnings("unchecked")
      List<CodegenOperation> operations = (List<CodegenOperation>) objectMap.get("operation");
      for (CodegenOperation operation : operations) {
          operation.httpMethod = operation.httpMethod.toLowerCase();
          List<CodegenParameter> params = operation.allParams;
          if (params != null && params.size() == 0) {
              operation.allParams = null;
          }
          List<CodegenResponse> responses = operation.responses;
          if (responses != null) {
              for (CodegenResponse resp : responses) {
                  if ("0".equals(resp.code)) {
                      resp.code = "default";
                  }
              }
          }
          if (operation.examples != null && !operation.examples.isEmpty()) {
              // Leave application/json* items only
              for (Iterator<Map<String, String>> it = operation.examples.iterator(); it.hasNext(); ) {
                  final Map<String, String> example = it.next();
                  final String contentType = example.get("contentType");
                  if (contentType == null || !contentType.startsWith("application/json")) {
                      it.remove();
                  }
              }
          }
      }
      return objs;
  }

}
