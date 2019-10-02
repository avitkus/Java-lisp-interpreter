package main.lisp.evaluator.utility;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class DeclareEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		expr = expr.getTail();
		
		
		while(!(expr instanceof NilAtom)) {
			SExpression decl = expr.getHead();
			processDeclaration(decl, environment);
			expr = expr.getTail();
		}
		
		return new NilAtom();
	}
	
	private void processDeclaration(SExpression decl, Environment env) {
		if (decl instanceof NilAtom) {
		} else if (decl instanceof IdentifierAtom) {
			IdentifierAtom pName = (IdentifierAtom)decl;
			throw new IllegalStateException("Propery '" + pName.getValue() + "' is not supported");
		} else if (decl.getHead() instanceof IdentifierAtom) {
			IdentifierAtom pName = (IdentifierAtom)decl.getHead();
			SExpression detail = decl.getTail();
			switch(pName.getValue()) {
			case "SPECIAL":
					processSpecialDecl(detail, env);
				break;
				default:
					throw new IllegalStateException("Propery '" + pName.getValue() + "' is not supported");
			}
		} else {
			throw new IllegalStateException("Property must be an identifer, a list beginning with an identifer, or empty");
		}
	}
	
	private void processSpecialDecl(SExpression details, Environment env) {
		if (details.getHead() instanceof IdentifierAtom) {
			IdentifierAtom vNameId = (IdentifierAtom)details.getHead();
			env.makeLocalSpecial(vNameId);
		} else {
			throw new IllegalStateException("Cannot set property 'SPECIAL' without an identifier");
		}
	}
}
