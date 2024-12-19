package edu.upc.dsa.orm.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i = 0;

        for (Field f : fields) sFields[i++] = f.getName();

        return sFields;

    }

    public static String[] getFieldsDirectlyClass(Class theClass) {

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i = 0;

        for (Field f : fields) sFields[i++] = f.getName();

        return sFields;

    }


    public static void setter(Object object, String property, Object value) {
        try {
            // Construir el nom del mètode "setter" (e.g., "Name" -> "setName")
            String setterName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);

            for (Method method : object.getClass().getMethods()) {
                if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                    method.invoke(object, value);
                    return; // Sortir si es troba i executa correctament
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object getter(Object object, String property) {
        try {
            String getterName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
            Method method = object.getClass().getMethod(getterName);
            return method.invoke(object);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object getterDirectlyFromClass(Class theClass, String property) {
        try {
            String getterName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
            Method method = theClass.getMethod(getterName);
            Object object = theClass.getDeclaredConstructor().newInstance();
            return method.invoke(object);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
