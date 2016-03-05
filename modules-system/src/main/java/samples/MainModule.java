package samples;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

public class MainModule {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ClassLoader rtClassLoader = Object.class.getClassLoader();
        printHierarchy(rtClassLoader);
        ClassLoader sampleClassLoader = ForSerialization.class.getClassLoader();
        printHierarchy(sampleClassLoader);
        Set<String> sampleClassLoaderClasses = retrieveLoadedClasses(sampleClassLoader);
        ClassLoader commonLangClassLoader = SerializationUtils.class.getClassLoader();
        printHierarchy(commonLangClassLoader);
        Set<String> commonLangClassLoaderClasses = retrieveLoadedClasses(commonLangClassLoader);
        ClassLoader commonCollectionClassLoader = HashBag.class.getClassLoader();
        printHierarchy(commonCollectionClassLoader);
        Set<String> commonCollectionClassLoaderClasses = retrieveLoadedClasses(commonCollectionClassLoader);
        ClassLoader appClassLoader = sampleClassLoader.getParent();
        Set<String> appClassLoaderClasses = retrieveLoadedClasses(appClassLoader);
        ClassLoader extClassLoader = appClassLoader.getParent();
        Set<String> extClassLoaderClasses = retrieveLoadedClasses(extClassLoader);
        for (String c : sampleClassLoaderClasses) {
            if (extClassLoaderClasses.contains(c)) {
                System.out.println("ext class loader load " + c);
            }
            if (appClassLoaderClasses.contains(c)) {
                System.out.println("app class loader load " + c);
            }
        }
        System.out.println("=========================");
        System.out.println("app classloader classes :");
        appClassLoaderClasses.forEach(System.out::println);
        System.out.println("==========================");
        System.out.println("ext classloader classes :");
        extClassLoaderClasses.forEach(System.out::println);
        System.out.println("==========================");
        System.out.println("commons lang classloader classes :");
        commonLangClassLoaderClasses.forEach(System.out::println);
        System.out.println("==========================");
        System.out.println("commons collection classloader classes :");
        commonCollectionClassLoaderClasses.forEach(System.out::println);
        System.out.println("==========================");
    }

    private static Set<String> retrieveLoadedClasses(ClassLoader classLoader) throws NoSuchFieldException,
            IllegalAccessException {
        Field classesField = ClassLoader.class.getDeclaredField("classes");
        classesField.setAccessible(true);
        return ((Vector<Class>) classesField.get(classLoader)).stream().map(Class::getName).collect(Collectors.toSet());
    }

    private static void printHierarchy(ClassLoader classLoader) {
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("==============================");
    }

    private static class ForSerialization {
    }
}
