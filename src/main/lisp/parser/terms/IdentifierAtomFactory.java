package main.lisp.parser.terms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import main.lisp.scanner.tokens.Token;
import util.trace.Tracer;

/**
 * This class allows for the class used to represent identifier atoms in the interpreter
 * to be changed.
 * 
 * @author Andrew Vitkus
 *
 */
public class IdentifierAtomFactory {
	private static final Class<? extends IdentifierAtom> defaultIdentifierAtomClass;
	private static Class<? extends IdentifierAtom> identiferAtomClass;
	
	static {
		defaultIdentifierAtomClass = IdentifierAtom.class;
		identiferAtomClass = defaultIdentifierAtomClass;
	}
	
	/**
	 * This method sets the class to use for representing identifier atoms.
	 * The class must contain a constructor taking a {@link Token}.
	 * 
	 * @param clazz new identifier atom class
	 * @throws IllegalArgumentException if the specified class does not have a constructor
	 *                                  taking an instance of {@link Token}
	 */
	public static void setClass(Class<? extends IdentifierAtom> clazz) {
		try {
			Constructor<? extends IdentifierAtom> c = clazz.getDeclaredConstructor(Token.class);
			int modifiers = c.getModifiers();
			boolean canAccess = false;
			if ((modifiers & Modifier.PUBLIC) != 0) {
				canAccess = true;
			} else if ((modifiers & Modifier.PROTECTED) != 0) {
				if (c.getDeclaringClass().getPackage().equals(IdentifierAtomFactory.class.getPackage())) {
					canAccess = true;
				}
			}
			if (!canAccess) {
				throw new IllegalArgumentException("Identifier Atom class' constructor is not accessible by the factory (is it private?)");
			}
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
			throw new IllegalArgumentException("Identifier Atom class must have a contructor with arguments (Token)", e);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
//		}
		identiferAtomClass = clazz;
	}
	
	/**
	 * This method returns the class currently registered for
	 * representing identifier atoms.
	 * 
	 * @return the s-expression class
	 */
	public static Class<? extends IdentifierAtom> getIdentifierAtomClass() {
		return identiferAtomClass;
	}
	
	/**
	 * Construct a new identifier atom with a name from the given token.
	 * 
	 * @param token the token with this identifier's name
	 * @return the new identifier atom
	 */
	public static IdentifierAtom newInstance(Token token) {
		IdentifierAtom ret = null;
		try {
			ret = (IdentifierAtom) identiferAtomClass.getDeclaredConstructor(Token.class).newInstance(token);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			try {
				ret = (IdentifierAtom) defaultIdentifierAtomClass.getDeclaredConstructor(Token.class).newInstance(token);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		Tracer.info(IdentifierAtomFactory.class, "New identifier atom: " + ret);
		return ret;
	}
}
