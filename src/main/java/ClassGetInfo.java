import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

public class ClassGetInfo {

    StringBuilder str = new StringBuilder();

    public void getMyClassInfo(String name) throws ClassNotFoundException {

        Class ClassInfo = Class.forName(name);
        int t = 0;
        getClassInfo(ClassInfo, t);
        System.out.println(str);
        str.setLength(0);
    }

    public void tab(String s, int t){
        if (s!="") {
            for (int j = 0; j < t; j++)
                str.append("   ");
            str.append(s + " ");
        }
    }
    public void getClassInfo(Class cl, int t)throws ClassNotFoundException {

        int temp = t;
        t++;
        tab("\n", t);
        tab(cl+"\n", t);
        tab("Родительский класс: " + cl.getSuperclass()+"\n",t);

        if ( cl.getInterfaces().length > 0) {
            getAnnotationInfo(cl.getAnnotations(), t);
            tab("Интерфейсы класса:\n",t);
            print(cl.getInterfaces(), t);
        }
        if ( cl.getConstructors().length > 0){
            tab("Конструкторы класса:\n", t);
            for (Constructor c: cl.getConstructors()) {
                getAnnotationInfo(c.getAnnotations(), t);
                tab(c.getName() + "\n", t);
            }
        }
        if ( cl.getDeclaredFields().length > 0) {
            tab("Поля класса:"+"\n", t);
            for (Field f: cl.getDeclaredFields()) {
                getAnnotationInfo(f.getAnnotations(), t);
                tab(f.getName() + "\n", t);
            }
        }
        if ( cl.getDeclaredMethods().length > 0){
            tab("Методы класса:"+"\n", t);
            for (Method m: cl.getDeclaredMethods()) {
                //получение аннотаций
                getAnnotationInfo(m.getAnnotations(), t);
                tab(m.getName() + "\n", t);
            }
        }
        tab("**********************************************\n",t);

        if (cl.getSuperclass()!=null) getClassInfo(cl.getSuperclass(), t);
        if (cl.getInterfaces()!=null) getInterfaceInfo(cl.getInterfaces(), t);
    }

    public void getInterfaceInfo(Class[] clazz, int t)throws ClassNotFoundException{

        int i=0;
        try{
            while (true){
                getClassInfo(clazz[i], t);
                i++;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
        }
    }

    public void print(Object[] masObj, int t){
        for (int i = 0; i < masObj.length; i++) {
            tab(masObj[i].toString()+"\n", t);
        }
    }

    public void getAnnotationInfo(Annotation[] at, int t){
        for (Annotation an : at) {
            tab(an.toString()+ "\n", t );
        }
    }

}
