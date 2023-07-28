package cn.bigcore.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class MyTestClassLoader extends ClassLoader {

    private String classPath;

    private MyTestClassLoader(String classPath) {
        System.out.println("初始化：" + classPath);
        this.classPath = classPath;
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
        int len = fileInputStream.available();
        byte[] data = new byte[len];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    public static void main(String[] args) throws Exception {
        MyTestClassLoader myTestClassLoader = new MyTestClassLoader("C:\\Users\\wangxuhui\\develop\\develop-person\\micro-system\\micro-example\\target\\classes");
        Class clazz = myTestClassLoader.loadClass("cn.bigcore.example.ExampleApplication");
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("main", String.class);
        method.invoke(object, "Word j");
        System.out.println(clazz.getClassLoader().getClass().getName());
    }

}



