package io.github.ralfspoeth.xml.io;

import java.io.IOException;
import java.io.InputStream;

class Lexer implements AutoCloseable {

    enum TokenType {
        WHITESPACE
    }

    record Token(TokenType type, String value) {}

    private final InputStream source;

    Lexer(InputStream source) {
        this.source = source;
    }

    public boolean hasNext() throws IOException {
        readNextToken();
        return nextToken!=null;
    }

    public Token next() {
        if(nextToken==null) {
            throw new IllegalStateException("Lexer has no next token");
        } else {
            var tmp = nextToken;
            nextToken = null;
            return tmp;
        }
    }

    // internal state
    private enum State {
        S0,
        WS,
        SQUOTE, DQUOTE,
        EOF
    }

    private State state = State.S0;
    private final StringBuilder buffer = new StringBuilder();
    private Token nextToken = null;

    private void readNextToken() throws IOException {

        while(state!=State.EOF && nextToken==null) {
            int r = source.read();
            if(r==-1) {
                switch (state) {
                    case S0 -> state = State.EOF;
                    case EOF -> state = State.EOF;
                }
            }
            else {
                byte b = (byte)r;
                switch (b) {
                    // whitespace
                    case 0x20, 0x9, 0xd, 0xa -> {
                        switch (state) {
                            case S0 -> {
                                state = State.WS;
                                buffer.append(b);
                            }
                            default -> throw new IllegalStateException("TODO");
                        }
                    }
                    default -> throw new IllegalStateException("TODO");
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        source.close();
    }
}
