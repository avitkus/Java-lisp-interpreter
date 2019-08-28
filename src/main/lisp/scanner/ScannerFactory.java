package main.lisp.scanner;

public class ScannerFactory {
	private static final Class<?> defaultScannerClass;
	private static Class<?> scannerClass;
	
	static {
		defaultScannerClass = BasicScanner.class;
		scannerClass = defaultScannerClass;
	}
	
	public void setClass(Class<? extends Scanner> clazz) {
		scannerClass = clazz;
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
