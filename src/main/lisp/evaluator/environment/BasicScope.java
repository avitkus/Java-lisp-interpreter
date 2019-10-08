package main.lisp.evaluator.environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;
import util.trace.Tracer;

public class BasicScope implements CopyableScope {
	private final Map<String, SExpression> idMap;
	private final Map<String, Function> funMap;
	
	private final List<String> specialNames;
	private final List<String> localSpecial;

	protected BasicScope() {
		this(new ArrayList<>());
		Tracer.info(BasicScope.class, "New scope");
	}
	
	private BasicScope(Scope original) {
		idMap = new HashMap<>();
		funMap = new HashMap<>();
		
		localSpecial = new ArrayList<>();
		specialNames = new ArrayList<>();
		
		idMap.putAll(original.getValueMap());
		funMap.putAll(original.getFunctionMap());
		
		localSpecial.addAll(original.getLocalSpecialNames());
		specialNames.addAll(original.getSpecialNames());
	}
	
	private BasicScope(List<String> specialNames) {
		idMap = new HashMap<>();
		funMap = new HashMap<>();
		this.specialNames = specialNames;
		localSpecial = new ArrayList<>();
	}
	
	@Override
	public void put(IdentifierAtom id, SExpression value) {
		String idValue = id.getValue();
		idMap.put(idValue, value);
//		Tracer.info(this, "Variable '" + id + "' set to '" + value + "' in scope:\n" + this);
	}
	
	@Override
	public void putFun(IdentifierAtom id, Function value) {
		String idValue = id.getValue();
		funMap.put(idValue, value);
//		Tracer.info(this, "Function '" + id + "' set to '" + value + "' in scope:\n" + this);
	}

	
	@Override
	public Optional<SExpression> get(IdentifierAtom id) {
		String idValue = id.getValue();
		if (idMap.containsKey(idValue)) {
			return Optional.of(idMap.get(idValue));
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Function> getFun(IdentifierAtom id) {
		String idValue = id.getValue();
		if (funMap.containsKey(idValue)) {
			return Optional.of(funMap.get(idValue));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void makeNameSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
//		Tracer.info(this, "Name '" + idValue + "' marked dynamic");
		specialNames.add(idValue);
	}
	
	@Override
	public void makeLocalSpecial(IdentifierAtom id) {
		String idValue = id.getValue();
//		Tracer.info(this, "Variable '" + idValue + "' marked dynamic in scope:\n" + this);
		localSpecial.add(idValue);
	}
	
	@Override
	public boolean isSpecial(IdentifierAtom id) {
		String idVal = id.getValue();
		if (specialNames.contains(idVal) || localSpecial.contains(idVal)) {
			return true;
		} else if (idMap.containsKey(idVal)){
			return false;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, SExpression> getValueMap() {
		return Collections.unmodifiableMap(idMap);
	}

	@Override
	public Map<String, Function> getFunctionMap() {
		return Collections.unmodifiableMap(funMap);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("===============\n");
		boolean empty = true;
		if (!idMap.isEmpty()) {
			sb.append("=== Values ====\n");
			buildMapStr(idMap, sb);
			empty = false;
		}
		if (!funMap.isEmpty()) {
			sb.append("== Functions ==\n");
			buildMapStr(funMap, sb);
			empty = false;
		}
		if (empty) {
			sb.append("<empty>\n");
		}
		return sb.toString();
	}
	
	private <T> void buildMapStr(Map<String, T> map, StringBuilder sb) {
		for(Map.Entry<String, T> entry : map.entrySet()) {
			String id = entry.getKey();
			T value = entry.getValue();
			if (specialNames.contains(id) || localSpecial.contains(id)) {
				sb.append("*");
			}
			sb.append(id).append(" = ").append(value).append("\n");
		}
	}

	@Override
	public List<String> getSpecialNames() {
		return Collections.unmodifiableList(specialNames);
	}

	@Override
	public List<String> getLocalSpecialNames() {
		return Collections.unmodifiableList(localSpecial);
	}

	@Override
	public CopyableScope copy() {
		return new BasicScope(this);
	}
}
