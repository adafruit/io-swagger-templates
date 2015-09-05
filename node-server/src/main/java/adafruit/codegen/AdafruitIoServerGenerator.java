package adafruit.codegen;

import io.swagger.codegen.*;
import io.swagger.codegen.languages.*;
import io.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class AdafruitIoServerGenerator extends NodeJSServerCodegen {

  public AdafruitIoServerGenerator() {
    // set the output folder here
    outputFolder = "build/server";

    /**
     * Models.  You can write model files using the modelTemplateFiles map.
     * if you want to create one template for file, you can do so here.
     * for multiple files for model, just put another entry in the `modelTemplateFiles` with
     * a different extension
     */
    modelTemplateFiles.clear();

    /**
     * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
     * as with models, add multiple entries with different extensions for multiple files per
     * class
     */
    apiTemplateFiles.put(
        "controller.mustache",   // the template to use
        ".js");       // the extension for each file to write

    /**
     * Template Location.  This is the location which templates will be read from.  The generator
     * will use the resource stream to attempt to read the templates.
     */
    templateDir = "node-server";

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
     * Additional Properties.  These values can be passed to the templates and
     * are available in models, apis, and supporting files
     */
    additionalProperties.put("apiVersion", apiVersion);
    additionalProperties.put("serverPort", serverPort);

    /**
     * Supporting Files.  You can write single files for the generator with the
     * entire object tree available.  If the input file has a suffix of `.mustache
     * it will be processed by the template engine.  Otherwise, it will be copied
     */
    // supportingFiles.add(new SupportingFile("controller.mustache",
    //   "controllers",
    //   "controller.js")
    // );
    supportingFiles.add(new SupportingFile("swagger.mustache",
          "api",
          "swagger.json")
        );
    supportingFiles.add(new SupportingFile("index.mustache",
          "",
          "index.js")
        );
    if (System.getProperty("noservice") == null) {
      apiTemplateFiles.put(
          "service.mustache",   // the template to use
          "Service.js");       // the extension for each file to write
    }
  }

}
