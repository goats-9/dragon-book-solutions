void S() {
    if (lookahead == '(') {
        match('('); S(); match(')'); S();
    }
}

void match(terminal t) {
    if (lookahead == t) lookahead = nextTerminal;
    else report("syntax error");
}