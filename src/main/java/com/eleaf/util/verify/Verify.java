package com.eleaf.util.verify;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 通用验证工具包：判空全家桶(?)
 * 
 * @author CTidy
 */
public class Verify {
	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isEmpty(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class<?> objClass = (Class<?>) object.getClass(); // 通过反射获取该类
		Field[] objFields = objClass.getDeclaredFields(); // 获取该类所有属性
		for (Field f : objFields) {
			f.setAccessible(true); // 获取将属性设为公有（public）
			Object val = f.get(object); // 获取该属性的值
			if (val != null && !"".equals(val)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotFull(Object object, String... fieldNames)
			throws IllegalArgumentException, IllegalAccessException {
		Class<?> objClass = (Class<?>) object.getClass(); // 通过反射获取该类
		Field[] objFields = objClass.getDeclaredFields(); // 获取该类所有属性
		for (Field f : objFields) {
			for (String fieldName : fieldNames) {
				if (f.toString().substring(f.toString().lastIndexOf(".") + 1).equals(fieldName)) {
					f.setAccessible(true); // 获取将属性设为公有（public）
					Object val = f.get(object); // 获取该属性的值
					if (val == null || "".equals(val)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isBlank(String str) {
		return str.trim().isEmpty();
	}

	public static boolean isInvalid(Object object, String... fieldNames) {
		try {
			return (isNull(object) || isNotFull(object, fieldNames));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isInvalid(Object object) {
		try {
			return (isNull(object) || isEmpty(object));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isInvalid(Collection<?> collection) {
		return (isNull(collection) || collection.isEmpty());
	}

	public static boolean isInvalid(String str) {
		return (isNull(str) || str.isEmpty() || isBlank(str));
	}
}
