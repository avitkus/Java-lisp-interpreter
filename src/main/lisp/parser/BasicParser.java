package main.lisp.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.QuoteAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.parser.terms.TAtom;
import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenType;

public class BasicParser implements Parser {
	private final Queue<Token> tokens;
	
	private int exprCount;
	private int parenCount;
	
	private boolean isQuoted;
	private int quotedParens;
	
	private SExpression root;
	
	public BasicParser() {
		tokens = new LinkedList<>();
		root = null;
		isQuoted = false;
		quotedParens = 0;
		parenCount = 0;
		exprCount = 0;
	}
	
	private void parse() {
		exprCount --;
		root = parseStart();
	}
	
	private SExpression parseStart() {
		if (tokens.isEmpty()) {
			return new NilAtom();
		}
		Token cur = tokens.peek();
		
		if (cur.getType() == TokenType.OPEN) {
			tokens.poll();
			return parseList();
		} else {
			return parseAtom();
		}
	}
	
	private SExpression parseLispSingle() {
		Token cur = tokens.peek();
		
		if (cur.getType() == TokenType.OPEN) {
			tokens.poll();
			return parseList();
		} else {
			return parseAtom();
		}
	}
	
	private SExpression parseList() {
		SExpression head = parseLispSingle();
		
		if (head == null) {
			return new NilAtom();
		}
		
		SExpression tail = parseList();
		
		return ExpressionFactory.newInstance(head, tail);
	}
	
	private SExpression parseAtom() {
		if (tokens.isEmpty()) {
			return new NilAtom();
		}
		
		SExpression ret;
		
		Token cur = tokens.poll();
		switch(cur.getType()) {
		case INTEGER:
			ret = new IntegerAtom(cur);
			break;
		case DECIMAL:
			ret = new DecimalAtom(cur);
			break;
		case STRING:
			ret = new StringAtom(cur);
			break;
		case QUOTE:
			ret = new QuoteAtom(parseLispSingle());
			break;
		case CLOSE:
			ret = null;
			break;
		case IDENTIFIER:
			if (cur.getValue().equalsIgnoreCase("nil")) {
				ret = new NilAtom();
			} else if (cur.getValue().equalsIgnoreCase("t")) {
				ret = new TAtom();
			} else {
				ret = new IdentifierAtom(cur);
			}
			break;
		default:
			ret = new IdentifierAtom(cur);
		}
		
		return ret;
	}
	
	@Override
	public void setTokens(List<Token> tokens) {
		for(Token token : tokens) {
			giveToken(token);
		}
	}

	@Override
	public void giveToken(Token token) {
		tokens.add(token);
		if (quotedParens == 0) {
			isQuoted = false;
		}
		if (token.getType() == TokenType.QUOTE) {
			isQuoted = true;
		}
		if (isQuoted) {
			if (token.getType() == TokenType.OPEN) {
				quotedParens ++;
			} else if (token.getType() == TokenType.CLOSE) {
				quotedParens --;
				if (quotedParens == 0) {
					isQuoted = false;
				}
			}
		} else {
			if (token.getType() == TokenType.OPEN) {
				parenCount ++;
			} else if (token.getType() == TokenType.CLOSE) {
				parenCount --;
				
			}
		}
		if (!isQuoted && parenCount == 0) {
			exprCount ++;
			if (root == null) {
				parse();
			}
		}
		
	}
	
	@Override
	public Optional<SExpression> getExpression() {
		Optional<SExpression> ret;
		if (root != null) {
			ret = Optional.of(root);
			root = null;
			if (exprCount > 0) {
				parse();
			}
		} else {
			ret = Optional.empty();
		}
		return ret;
	}

}
