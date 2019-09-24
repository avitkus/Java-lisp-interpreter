package main.lisp.evaluator.environment;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.environment.ConglomerateEnvironment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class BasicConglomerateEnvironment implements ConglomerateEnvironment {
	private final Environment lexical;
	private final Environment dynamic;
	
	protected BasicConglomerateEnvironment() {
		this(EnvironmentFactory.newInstance(), EnvironmentFactory.newInstance());
	}
	
	private BasicConglomerateEnvironment(ConglomerateEnvironment original) {
		lexical = original.getLexicalEnvironment().copy();
		dynamic = original.getDynamicEnvironment().copy();
	}
	
	/**
	 * @param lexical
	 * @param dynamic
	 * @param parent
	 */
	private BasicConglomerateEnvironment(Environment lexical, Environment dynamic) {
		this.lexical = lexical;
		this.dynamic = dynamic;
	}

	@Override
	public Optional<SExpression> lookup(IdentifierAtom id) {
		if (isSpecial(id)) {
			return dynamic.lookup(id);
		} else {
			Optional<SExpression> lexicalVal = lexical.lookup(id);
			if (!lexicalVal.isPresent() && !LispInterpreterSettings.areUnboundLexical()) {
				return dynamic.lookup(id);
			} else {
				return lexical.lookup(id);
			}
		}
	}

	@Override
	public Optional<Function> lookupFun(IdentifierAtom id) {
		return dynamic.lookupFun(id);
	}

	@Override
	public void put(IdentifierAtom id, SExpression value) {
		if (isSpecial(id)) {
			dynamic.put(id, value);
		} else {
			lexical.put(id, value);
		}
	}

	@Override
	public void putFun(IdentifierAtom id, Function value) {
		dynamic.putFun(id, value);
	}

	@Override
	public void assign(IdentifierAtom id, SExpression value) {
		if (isSpecial(id)) {
			dynamic.assign(id, value);
		} else {
			lexical.assign(id, value);
		}
	}

	@Override
	public void assignFun(IdentifierAtom id, Function value) {
		dynamic.assignFun(id, value);
	}

	@Override
	public void makeNameSpecial(IdentifierAtom id) {
		lexical.makeNameSpecial(id);
	}

	@Override
	public void makeLocalSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
		if (!dynamic.getValueMap().containsKey(idValue)) {
			if (lexical.getValueMap().containsKey(idValue)) {
				dynamic.put(id, lexical.getValueMap().get(idValue));
			}
		}
		lexical.makeLocalSpecial(id);
	}

	@Override
	public boolean isSpecial(IdentifierAtom id) {
		return lexical.isSpecial(id);
	}

	@Override
	public Environment newChild() {
		return new BasicConglomerateEnvironment(lexical.newChild(), dynamic.newChild());
	}
	
	@Override
	public Map<String, SExpression> getValueMap() {
		Map<String, SExpression> effectiveValues = new HashMap<>();
		
		Set<String> dynamics = new HashSet<>();
		
		dynamics.addAll(lexical.getSpecialNames());
		
		Environment parsingLexical = lexical;
		while (parsingLexical != null) {
			dynamics.addAll(parsingLexical.getLocalSpecialNames());
			for(Entry<String, SExpression> entry : parsingLexical.getValueMap().entrySet()) {
				String idValue = entry.getKey();
				SExpression value = entry.getValue();
				if (!dynamics.contains(idValue) && !effectiveValues.containsKey(idValue)) {
					effectiveValues.put(idValue, value);
				}
			}
		}
		
		Environment parsingDynamic = dynamic;
		while (parsingDynamic != null) {
			for(Entry<String, SExpression> entry : parsingDynamic.getValueMap().entrySet()) {
				String idValue = entry.getKey();
				SExpression value = entry.getValue();
				if (dynamics.contains(idValue) && !effectiveValues.containsKey(idValue)) {
					effectiveValues.put(idValue, value);
				}
			}
		}
		
		return Collections.unmodifiableMap(effectiveValues);
	}

	@Override
	public Map<String, Function> getFunctionMap() {
		return dynamic.getFunctionMap();
	}

	@Override
	public List<String> getSpecialNames() {
		return lexical.getSpecialNames();
	}

	@Override
	public List<String> getLocalSpecialNames() {
		return lexical.getLocalSpecialNames();
	}

	@Override
	public Environment getLexicalEnvironment() {
		return lexical;
	}

	@Override
	public Environment getDynamicEnvironment() {
		return dynamic;
	}

	public static ConglomerateEnvironment merge(Environment lexical, Environment dynamic) {
		if (lexical instanceof ConglomerateEnvironment) {
			lexical = ((ConglomerateEnvironment)lexical).getLexicalEnvironment();
		}
		if (dynamic instanceof ConglomerateEnvironment) {
			dynamic = ((ConglomerateEnvironment)dynamic).getDynamicEnvironment();
		}
		return new BasicConglomerateEnvironment(lexical, dynamic);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("===== LEXICAL =====\n");
		sb.append(lexical);
		sb.append("\n===== DYNAMIC =====\n");
		sb.append(dynamic);
		
		return sb.toString();
	}

	@Override
	public Environment getParent() {
		throw new UnsupportedOperationException("Conglomerate environments don't have parents");
	}

	@Override
	public Environment copy() {
		return new BasicConglomerateEnvironment(this);
	}

	@Override
	public Optional<SExpression> get(IdentifierAtom id) {
		if (lexical.isSpecial(id)) {
			return dynamic.get(id);
		} else {
			return lexical.get(id);
		}
	}

	@Override
	public Optional<Function> getFun(IdentifierAtom id) {
		return dynamic.getFun(id);
	}
}
