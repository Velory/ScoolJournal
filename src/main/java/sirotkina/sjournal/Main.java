package sirotkina.sjournal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sirotkina.sjournal.ui.Login;
import sirotkina.sjournal.utils.DatabaseUtils;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        DatabaseUtils.migrate();
        Login.main(args);
        LOG.info("Hello logger");
        LOG.error("Main error");
    }


}
