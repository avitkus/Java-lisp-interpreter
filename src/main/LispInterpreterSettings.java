package main;

import util.trace.Tracer;

public class LispInterpreterSettings {
	public static enum EvaluationMode {
		LAZY, EAGER, NORMAL
	}
	
	protected static EvaluationMode evaluationMode = EvaluationMode.NORMAL;
	
	protected static boolean unboundDefaultLexical = true;
	
	protected static boolean deepCopyEnvironment = false;
	
	protected static boolean eagerPool = false;
	
	protected static boolean eagerDeepCopyEnvironment = false;
	
	protected static boolean lazyDeepCopyEnvironment = false;
	
	protected static boolean thunkPrintEvals = false;
	
	protected static boolean thunkMetaEvals = false;


	/**
	 * @return the evaluationMode
	 */
	public static EvaluationMode getEvaluationMode() {
		return evaluationMode;
	}

	/**
	 * @param evaluationMode the evaluationMode to set
	 */
	public static void setEvaluationMode(EvaluationMode evaluationMode) {
		Tracer.info(LispInterpreterSettings.class, "evaluationMode: " + LispInterpreterSettings.evaluationMode + " -> " + evaluationMode);
		LispInterpreterSettings.evaluationMode = evaluationMode;
	}

	/**
	 * @return the dynamicScope
	 */
	public static boolean areUnboundLexical() {
		return unboundDefaultLexical;
	}

	/**
	 * @param unboundLexical the dynamicScope to set
	 */
	public static void setUnboundLexical(boolean unboundLexical) {
		Tracer.info(LispInterpreterSettings.class, "unboundDefaultLexical: " + LispInterpreterSettings.unboundDefaultLexical + " -> " + unboundLexical);
		LispInterpreterSettings.unboundDefaultLexical = unboundLexical;
	}

	/**
	 * @return the deepCopyFinal
	 */
	public static boolean isDeepCopyEnvironment() {
		return deepCopyEnvironment;
	}

	/**
	 * @param deepCopyEnvironment should function calls use deep copies of environments
	 */
	public static void setDeepCopyFunctionEnvironment(boolean deepCopyEnvironment) {
		Tracer.info(LispInterpreterSettings.class, "deepCopyEnvironment: " + LispInterpreterSettings.deepCopyEnvironment + " -> " + deepCopyEnvironment);
		LispInterpreterSettings.deepCopyEnvironment = deepCopyEnvironment;
	}

	/**
	 * @return the eagerPool
	 */
	public static boolean isEagerPool() {
		return eagerPool;
	}

	/**
	 * @param eagerPool the eagerPool to set
	 */
	public static void setEagerPool(boolean eagerPool) {
		Tracer.info(LispInterpreterSettings.class, "eagerPool: " + LispInterpreterSettings.eagerPool + " -> " + eagerPool);
		LispInterpreterSettings.eagerPool = eagerPool;
	}

	/**
	 * @return the eagerDeepCopyEnvironment
	 */
	public static boolean isEagerDeepCopyEnvironment() {
		return eagerDeepCopyEnvironment;
	}

	/**
	 * @param eagerDeepCopyEnvironment the eagerDeepCopyEnvironment to set
	 */
	public static void setDeepCopyEagerEnvironment(boolean eagerDeepCopyEnvironment) {
		Tracer.info(LispInterpreterSettings.class, "eagerDeepCopyEnvironment: " + LispInterpreterSettings.eagerDeepCopyEnvironment + " -> " + eagerDeepCopyEnvironment);
		LispInterpreterSettings.eagerDeepCopyEnvironment = eagerDeepCopyEnvironment;
	}

	/**
	 * @return the lazyDeepCopyEnvironment
	 */
	public static boolean isDeepCopyLazyEnvironment() {
		return lazyDeepCopyEnvironment;
	}

	/**
	 * @param lazyDeepCopyEnvironment the lazyDeepCopyEnvironment to set
	 */
	public static void setDeepCopyLazyEnvironment(boolean lazyDeepCopyEnvironment) {
		LispInterpreterSettings.lazyDeepCopyEnvironment = lazyDeepCopyEnvironment;
	}

	public static boolean doesThunkPrintEval() {
		return thunkPrintEvals;
	}
	
	public static void setThunkPrintEvals(boolean printEvals) {
		LispInterpreterSettings.thunkPrintEvals = printEvals;
	}

	/**
	 * @return the thunkMetaEvals
	 */
	public static boolean isThunkMetaEvals() {
		return thunkMetaEvals;
	}

	/**
	 * @param thunkMetaEvals the thunkMetaEvals to set
	 */
	public static void setThunkMetaEvals(boolean thunkMetaEvals) {
		LispInterpreterSettings.thunkMetaEvals = thunkMetaEvals;
	}
}
