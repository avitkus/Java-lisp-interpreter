package main.lisp.evaluator.lazy;

import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.SExpression;

public interface Thunk extends Atom<SExpression> {
}
