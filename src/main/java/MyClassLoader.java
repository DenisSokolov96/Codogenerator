import javassist.*;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name){

        try {
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("org.apache.log4j");
            CtClass ctClass = pool.get(name);

            for (CtMethod cMethod : ctClass.getDeclaredMethods()) {
                if (cMethod.hasAnnotation(Logged.class)) {
                    String strBefore = "{Logger log =  LogManager.getLogger(\"\");"
                            + "String str = \"run method[ '" + cMethod.getName()+"' ] \";"
                            + "String strParam = \"parameters:[ '\";"
                            + "for (int i = 0; i < $args.length; i++ )"
                            + " strParam += $args[i] + \"' \";"
                            + "if ($args.length == 0) str += \"not parameters\";"
                            + "else str += strParam+\"]\";"
                            + "log.info(str);}";
                    cMethod.insertBefore(strBefore);
                    String strAfter = "{Logger log = LogManager.getLogger(\"\");"
                            + "if ($_ != null) log.info( \"stop method[ '" + cMethod.getName() + "' ] return: \" + $_);"
                            + "else log.info( \"stop method[ '" + cMethod.getName() + "' ] not return\");"
                            + "}";
                    cMethod.insertAfter(strAfter);
                }
            }

            return ctClass.toClass();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        catch (NotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}