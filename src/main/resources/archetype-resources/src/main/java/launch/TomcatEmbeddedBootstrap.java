package ${package}.launch;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatEmbeddedBootstrap {
    private static final String WEBAPP_DIR_PATH = "src/main/webapp";
    private static final String PORT = "8080";
    private static final String CLASS_PATH = "target/classes";

    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = PORT;
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(WEBAPP_DIR_PATH).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + WEBAPP_DIR_PATH).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                CLASS_PATH, "/"));

        ctx.setResources(resources);

        tomcat.start();
        System.out.println("The tomcat is started.");
        tomcat.getServer().await();
    }
}
