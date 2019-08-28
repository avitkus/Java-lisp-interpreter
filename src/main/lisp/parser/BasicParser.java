package main.lisp.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.BasicExpression;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.QuoteAtom;
import main.lisp.parser.terms.StringAtom;
import main.lisp.scanner.tokens.IdentifierToken;
import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenType;

public class BasicParser implements Parser {
	private final Queue<Token> tokens;
	
	private int exprCount;
	private int parenCount;
	
	private boolean prevEndParen;
	
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
		prevEndParen = false;
	}
	
	private void parse() {
		exprCount --;
		root = parseStart();
		root = cleanEnd(root);
	}
	
	private SExpression cleanEnd(SExpression e) {
		if (e instanceof Atom) {
			return e;
		}
		if (e.getTail().getTail() instanceof NilAtom && !(e.getTail().getHead() instanceof NilAtom)) {// && (e.getTail().getHead() instanceof Atom)) {
			return new BasicExpression(cleanEnd(e.getHead()), cleanEnd(e.getTail().getHead()));
		} else {
			return new BasicExpression(cleanEnd(e.getHead()), cleanEnd(e.getTail()));
		}
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
			SExpression head = parseAtom();
			SExpression tail = parseLisp();
			if (tail.getTail() == null) {
				tail = tail.getHead();
			}
//			if (tail instanceof NilAtom) {
//				return head;
//			} else {
				return new BasicExpression(head, tail);
//			}
//			return new BasicExpression(parseAtom(), parseLisp());
		}
	}
	
	private SExpression parseLisp() {
		if (tokens.isEmpty()) {
			return new NilAtom();
		}
		Token cur = tokens.peek();
		
		if (cur.getType() == TokenType.OPEN) {
			tokens.poll();
			return new BasicExpression(parseList(), parseLisp());
		} else {
			SExpression head = parseAtom();
			SExpression tail = parseLisp();
			
//			if (tail == null) {
//				return null;
//			}
			
			if (tail.getTail() == null) {
				tail = tail.getHead();
			}
			
			return new BasicExpression(head, tail);
//			return new BasicExpression(parseAtom(), parseLisp());
		}
	}
	
	private SExpression parseLispSingle() {
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
	
	private SExpression parseList() {
		SExpression head = parseLispSingle();
		
		if (head == null) {
			return new NilAtom();
		}
		
		SExpression tail = parseList();
		
		if (tail != null && tail.getTail() == null) {
			if (!prevEndParen) {//!tokens.isEmpty()) {
				tail = tail.getHead();
			} else {
				tail = new BasicExpression(tail.getHead(), new NilAtom());
			}
		}
		
		return new BasicExpression(head, tail);
	}
	
	private SExpression parseAtom() {
		if (tokens.isEmpty()) {
			return new NilAtom();
		}
		prevEndParen = false;
		
		Token cur = tokens.poll();
		SExpression ret = null;
		if (cur.getType() == TokenType.INTEGER){
			ret = new IntegerAtom(cur);
		} else if (cur.getType() == TokenType.DECIMAL) {
			ret = new DecimalAtom(cur);
		} else if (cur.getType() == TokenType.STRING) {
			ret = new StringAtom(cur);
		} else if (cur.getType() == TokenType.QUOTE) {
			ret = new QuoteAtom(parseLispSingle());
		} else if (cur.getType() == TokenType.CLOSE) {
			ret = null;
			prevEndParen = true;
		} else if (cur.getType() == TokenType.IDENTIFIER) {
			if (cur.getValue().equalsIgnoreCase("nil")) {
				ret = new NilAtom();
			} else {
				ret = new IdentifierAtom(cur);
			}
		} else {
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
