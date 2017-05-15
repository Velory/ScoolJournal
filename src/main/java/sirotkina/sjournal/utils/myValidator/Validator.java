package sirotkina.sjournal.utils.myValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class Validator {
    private Class c;
    private String message;

    public Validator (Class c){
        this.c = c;
    }

    private boolean isNotNull(Object o, Annotation annotation, Field f) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        boolean result = false;
        if (annotation.annotationType().getSimpleName().equals(NotNull.class.getSimpleName())){
            if ((Integer)f.get(o) != 0){
                result = true;
            } else {
                Method method = annotation.annotationType().getMethod("message");
                message = (String) method.invoke(annotation);
            }
        }
        return result;
    }

    private boolean isNotEmpty(Object o, Annotation annotation, Field f) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        boolean result = false;
        if (annotation.annotationType().getSimpleName().equals(NotEmpty.class.getSimpleName())){
            String str = (String)f.get(o);
            if (f.get(o) != null && !str.isEmpty()){
                result = true;
            } else {
                Method method = annotation.annotationType().getMethod("message");
                message = (String) method.invoke(annotation);
            }
        }
        return result;
    }

    private boolean isPattern(Object o, Annotation annotation, Field f) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        boolean result = false;
        if (annotation.annotationType().getSimpleName().equals(Pattern.class.getSimpleName())){
            Method method = annotation.annotationType().getMethod("regexp");
            String msgRegexp = (String) method.invoke(annotation);
            String value = (String) f.get(o);
            if (f.get(o) != null && !f.get(o).equals("") && value.matches(msgRegexp)){
                result = true;
            } else {
                method = annotation.annotationType().getMethod("message");
                message = (String) method.invoke(annotation);
            }
        }
        return result;
    }

    public Set<String> validate(Object o) {
        Set<String> messageList = new LinkedHashSet<>();
        try{
            Field [] fields = c.getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                Annotation[] annotations = f.getAnnotations();
                for (Annotation annotation: annotations){
                    if (annotation.annotationType().equals(NotNull.class)){
                        isNotNull(o, annotation, f);
                        if (message != null) {
                            messageList.add(message);
                        }
                    }

                    if (annotation.annotationType().equals(NotEmpty.class)){
                        isNotEmpty(o, annotation, f);
                        if (message != null) {
                            messageList.add(message);
                        }
                    }

                    if (annotation.annotationType().equals(Pattern.class)){
                        isPattern(o, annotation, f);
                        if (message != null) {
                            messageList.add(message);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return messageList;
    }

}
