package cn.nicollcheng.tools.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author <a href="mailto:email.nicollcheng@gmail.com">nicollcheng</a>
 * <b>Creation Time:</b> 2021-10-10 15:14.
 * sun.misc.Unsafe 反射工具类
 * 应用：https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 */
public class UnsafeReflection {
    private UnsafeReflection() {
        throw new UnsupportedOperationException();
    }

    private static Unsafe createUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Can't use unsafe", e);
        }
    }
}
