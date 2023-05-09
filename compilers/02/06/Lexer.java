package lexer;               // File Lexer.java   
import java.io.*; import java.util.*;
public class Lexer {
    public int line = 1;
    private char peek = ' ';
    // Reading extra / while checking for comments
    private boolean slashCheck = false;
    private Hashtable words = new Hashtable();
    void reserve(Word t) { words.put(t.lexeme, t); }
    public Lexer() {
        reserve( new Word(Tag.TRUE, "true") );
        reserve( new Word(Tag.FALSE, "false") );
    }
    public Token scan() throws IOException {
        for( ; ; peek = (char)System.in.read() ) {
            if( peek == ' ' || peek == '\t' ) continue;
            else if( peek == '\n' ) line = line + 1;
            else if( peek == '/') {        // Check for comments
                peek = (char)System.in.read();
                if (peek == '/') line = line + 1;
                else if (peek == '*') {
                    for ( ; ; peek = (char)System.in.read() ) {
                        if (peek == '*') {
                            peek = (char)System.in.read();
                            if (peek == '/') break;
                        }
                        if (peek == '\n') line = line + 1;
                    }
                } else {
                    slashCheck = true;
                    break;
                }
            }
            else break;
        }
        if( Character.isDigit(peek) || peek = '.' ) {
            if (peek == '.') {
                double flt = 0.0;
                long ctr = -1;
                peek = (char)System.in.read();
                if (!Character.isDigit(peek)) {
                    Token t = new Token('.');
                    peek = ' ';
                    return t;
                }
                do {
                    flt = flt + Character.digit(peek, 10)*Math.pow(10, ctr--);
                    peek = (char)System.in.read();
                } while ( Character.isDigit(peek) );
                return new Num(flt);
            }
            int v = 0;
            do {
                v = 10*v + Character.digit(peek, 10);
                peek = (char)System.in.read();
            } while( Character.isDigit(peek) );
            return new Num(v);
            if (peek == '.') {
                double flt = v;
                int ctr = -1;
                peek = (char)System.in.read();
                do {
                    flt = flt + Character.digit(peek, 10)*Math.pow(10, ctr--);
                    peek = (char)System.in.read();
                } while ( Character.isDigit(peek));
                return new Num(flt);
            }
        }
        if( Character.isLetter(peek) ) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                peek = (char)System.in.read();
            } while( Character.isLetterOrDigit(peek) );
            String s = b.toString();
            Word w = (Word)words.get(s);
            if( w != null ) return w;
            w = new Word(Tag.ID, s);
            words.put(s, w);
            return w;
        }
        if ( peek == '<' || peek == '>' ) {
            StringBuffer b = new StringBuffer();
            b.append(peek);
            peek = (char)System.in.read();
            if (peek == '=') b.append(peek);
            String s = b.toString();
            Word w = (Word)words.get(s);
            if (w != null) return w;
            words.put(s, w);
            return w;
        }
        if ( peek == '!' || peek == '=' ) {
            StringBuffer b = new StringBuffer();
            b.append(peek);
            peek = (char)System.in.read();
            if (peek == '=') {
                b.append(peek);
                String s = b.toString();
                Word w = (Word)words.get(s);
                if (w != null) return w;
                words.put(s, w);
                return w;
            } else {
                Token t = new Token(peek);
                peek = ' ';
                return t;
            }
        }
        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}
