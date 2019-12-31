import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, IllegalAccessException, InstantiationException, InterruptedException {

        MyClassLoader loader1 = new MyClassLoader();
        Class cl = loader1.findClass("PrintInfo");
        IMyPrintClass iprint = (IMyPrintClass) cl.newInstance();
        iprint.add("Hello");
        System.out.println( iprint.printInfo());
        System.out.println( "-----");
        iprint.add("World!");
        System.out.println( iprint.printInfo());

    }
}
