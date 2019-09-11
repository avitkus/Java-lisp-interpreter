package main.lisp.scanner;

public class ScannerFactory {
	private static final Class<? extends Scanner> defaultScannerClass;
	private static Class<? extends Scanner> scannerClass;
	
	static {
		defaultScannerClass = BasicScanner.class;
		scannerClass = defaultScannerClass;
	}
	
	public static void setClass(Class<? extends Scanner> clazz) {
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
