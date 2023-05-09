void S() {
    switch (lookahead) {
        case '0':
            match('0'); S(); match('1');
        case '1':       // Fallthrough!
            break;
        default:
            report("syntax error");
    }
}

void match(terminal t) {
    if (lookahead == t) lookahead = nextTerminal;
    else report("syntax error");
}