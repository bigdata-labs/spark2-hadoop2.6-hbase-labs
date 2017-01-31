package codes.showme.exception;

import java.io.IOException;

/**
 * Created by jack on 11/13/16.
 */
public class LoadConfigFromPropertiesException extends RuntimeException {
    public LoadConfigFromPropertiesException(IOException e) {
        super(e);
    }
}
