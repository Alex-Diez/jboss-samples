package samples;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class AppMain {

    public static void main(String[] args) {
        ClassLoader rtClassLoader = Object.class.getClassLoader();
        System.out.println(rtClassLoader);
        ClassLoader extClassLoader = Ignore.class.getClassLoader();
        System.out.println(extClassLoader);
        ClassLoader appClassLoader = AppMain.class.getClassLoader();
        System.out.println(appClassLoader);
    }
}
