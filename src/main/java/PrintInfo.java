import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class PrintInfo implements IMyPrintClass {

    private StringBuilder str;

    PrintInfo() {
        str = new StringBuilder("");
    }

    @Logged(logMethodType = LogType.INFO, logClass = PrintInfo.class)
    @Override
    public String printInfo() {
        return str.toString();
    }

    @Logged(logMethodType = LogType.ERROR, logClass = PrintInfo.class)
    @Override
    public void del() {
        str.setLength(0);
    }

    @Logged(logMethodType = LogType.WARN, logClass = PrintInfo.class)
    @Override
    public void add(String str_message) {

        if (str.length()==0)
            str.append(str_message);
        else str.append("\n"+str_message);
    }
}
