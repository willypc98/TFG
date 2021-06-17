package utils;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import play.libs.Json;
 
public class ApplicationUtil {
 
    public static ObjectNode createResponse(Object response, boolean ok) {
        ObjectNode result = Json.newObject();
        result.put("status", ok);
        if (response instanceof String)
            result.put("response", (String) response);
        else result.set("response", (JsonNode) response);
 
        return result;
    }
    /*
    public static Configuration createConfiguration(){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);

        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setNumberFormat("computer");
    }

     */
}