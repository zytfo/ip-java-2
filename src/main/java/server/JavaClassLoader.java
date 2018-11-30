package server;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class JavaClassLoader extends ClassLoader implements Callable<Object> {
    private String[] arguments;
    private final static Logger logger = Logger.getLogger(Server.class);

    JavaClassLoader(String[] arguments) {
        this.arguments = arguments;
    }

    public Object call() {
        try {
            return invokeClassMethod("plugins." + arguments[0].substring(1), "call", Arrays.copyOfRange(arguments, 1, arguments.length));
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public static ArrayList<Class> getClasses(ClassLoader cl, String pack) {
        String dottedPackage = pack.replaceAll("[/]", ".");
        ArrayList<Class> classes = new ArrayList<>();
        URL upackage = cl.getResource(pack);
        try {
        BufferedReader dis = new BufferedReader(new InputStreamReader((InputStream) upackage.getContent()));
        String line;
            while ((line = dis.readLine()) != null) {
                if (line.endsWith(".class")) {
                    classes.add(Class.forName(dottedPackage + "." + line.substring(0, line.lastIndexOf('.'))));
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return classes;
    }

    private boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Object invokeClassMethod(String classBinName, String methodName, String[] arguments) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class loadedMyClass = classLoader.loadClass(classBinName);
            Constructor constructor = loadedMyClass.getConstructors()[0];
            Object myClassObject = null;
            if (arguments.length == 1) {
                myClassObject = constructor.newInstance(Integer.parseInt(arguments[0]));
            } else if (arguments.length == 2) {
                if (isInteger(arguments[1])) {
                    myClassObject = constructor.newInstance(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                } else {
                    myClassObject = constructor.newInstance(Integer.parseInt(arguments[0]), arguments[1]);
                }
            } else {
                System.exit(-1);
            }
            Method method = loadedMyClass.getMethod(methodName);
            return method.invoke(myClassObject);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
