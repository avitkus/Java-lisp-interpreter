package main.lisp.evaluator.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

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
		Tracer.info(AbstractEnvironment.class, "New environment \nparent =\n" + parent + (parent == null ? "\n" : "") +"local scope = \n" + scope);
	}
	
	@Override
	public void put(IdentifierAtom id, SExpression value) {
		scope.put(id, value);
		boolean oldPrintEvals = LispInterpreterSettings.doesThunkPrintEval();
		LispInterpreterSettings.setThunkPrintEvals(false);
		Tracer.info(this, "Variable '" + id + "' set to '" + value + "' in environment:\n" + this);
		LispInterpreterSettings.setThunkPrintEvals(oldPrintEvals);
	}

	@Override
	public void putFun(IdentifierAtom id, Function value) {
		scope.putFun(id, value);
		Tracer.info(this, "Function '" + id + "' set to '" + value + "' in environment:\n" + this);
	}

	@Override
	public void makeNameSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
		scope.makeNameSpecial(id);
		Tracer.info(this, "Name '" + idValue + "' marked dynamic");
	}

	@Override
	public void makeLocalSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
		scope.makeLocalSpecial(id);
		Tracer.info(this, "Variable '" + idValue + "' marked dynamic in environment:\n" + this);
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(scope.toString());
		if (getParent() != null) {
			sb.append(getParent().toString());
		}
		return sb.toString();
	}
}
