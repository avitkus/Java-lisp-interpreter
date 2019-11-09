package main;

public class LispInterpreterSettings {
	public static enum EvaluationMode {
		LAZY, EAGER, NORMAL
	}
	
	protected static EvaluationMode evaluationMode = EvaluationMode.NORMAL;
	
	protected static boolean unboundDefaultLexical = true;
	
	protected static boolean deepCopyEnvironment = true;

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
		LispInterpreterSettings.unboundDefaultLexical = unboundLexical;
	}

	/**
	 * @return the deepCopyFinal
	 */
	public static boolean isDeepCopyEnvironment() {
		return deepCopyEnvironment;
	}

	/**
	 * @param deepCopyEnvironment the deepCopyFinal to set
	 */
	public static void setDeepCopyEnvironment(boolean deepCopyEnvironment) {
		LispInterpreterSettings.deepCopyEnvironment = deepCopyEnvironment;
	}
	
	
	
}
