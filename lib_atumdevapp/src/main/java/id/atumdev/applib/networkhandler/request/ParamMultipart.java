package id.atumdev.applib.networkhandler.request;

import org.apache.http.entity.ContentType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ANGGA on 10/24/2015.
 */
public class ParamMultipart {

    protected File file;
    protected String paramFile;
    protected ContentType contentType;
    protected Map<String, String> otherParams = new HashMap<>();


    public String getParamFile() {
        return paramFile;
    }

    public void setParamFile(String paramFile) {
        this.paramFile = paramFile;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, String> otherParams) {
        this.otherParams = otherParams;
    }
}
