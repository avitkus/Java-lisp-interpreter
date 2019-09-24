package main.lisp.evaluator.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public abstract class AbstractEnvironment implements Environment {
	
	private final CopyableScope scope;
	private final Environment parent;
	
	// must have public constructor that calls this in your version
	// called by factory, must be accessible
	protected AbstractEnvironment() {
		this(null);
	}

	// must have constructor that calls this in your version
	// called in your version to make child
	protected AbstractEnvironment(Environment parent) {
		this(parent, new BasicScope());
	}
	
	// must have constructor that calls this in your version
	// called in your version to make copy
	protected AbstractEnvironment(Environment parent, CopyableScope scope) {
		this.scope = scope;
		this.parent = parent;
	}
	
	@Override
	public void put(IdentifierAtom id, SExpression value) {
		scope.put(id, value);
	}

	@Override
	public void putFun(IdentifierAtom id, Function value) {
		scope.putFun(id, value);
	}

	@Override
	public void makeNameSpecial(IdentifierAtom id) {
		scope.makeNameSpecial(id);
	}

	@Override
	public void makeLocalSpecial(IdentifierAtom id) {
		scope.makeLocalSpecial(id);
	}

	@Override
	public boolean isSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
		if (scope.getSpecialNames().contains(idValue)) {
			return true;
		} else if (scope.getLocalSpecialNames().contains(idValue)) {
			return true;
		} else if (scope.get(id).isPresent()) {
			return false;
		} else if (parent != null) {
			return parent.isSpecial(id);
		} else {
			return false;
		}
	}
	
	@Override
	public Environment getParent() {
		return parent;
	}

	@Override
	public Map<String, SExpression> getValueMap() {
		return scope.getValueMap();
	}

	@Override
	public Map<String, Function> getFunctionMap() {
		return scope.getFunctionMap();
	}

	@Override
	public List<String> getSpecialNames() {
		return scope.getSpecialNames();
	}

	@Override
	public List<String> getLocalSpecialNames() {
		return scope.getLocalSpecialNames();
	}
	
	protected CopyableScope getScope() {
		return scope;
	}

	@Override
	public Optional<SExpression> get(IdentifierAtom id) {
		return scope.get(id);
	}

	@Override
	public Optional<Function> getFun(IdentifierAtom id) {
		return scope.getFun(id);
	}

}
