package main.lisp.scanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ScannerFactory {
	private static final Class<? extends Scanner> defaultScannerClass;
	private static Class<? extends Scanner> scannerClass;
	
	static {
		defaultScannerClass = BasicScanner.class;
		scannerClass = defaultScannerClass;
	}
	
	public static void setClass(Class<? extends Scanner> clazz) {
		try {
			Constructor<? extends Scanner> c = clazz.getDeclaredConstructor();
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(ScannerFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Scanner class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Scanner class must have a contructor with no arguments", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		scannerClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * use as the scanner
	 * 
	 * @return the scanner class
	 */
	public static Class<? extends Scanner> getScannerClass() {
		return scannerClass;
	}
	
	public static Scanner newInstance() {
		try {
			return (Scanner) scannerClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			try {
				return (Scanner) defaultScannerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}
}
