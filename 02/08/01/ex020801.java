class For extends Stmt {
    Expr E1, E2, E3;
    Stmt S;
    public For(Expr e1, Expr e2, Expr e3, Stmt s) {
        E1 = e1; E2 = e2; E3 = e3; S = s;
    }
    public void gen() {
        E1.gen();
        // Give a label for start and end
        Label start = new Label();
        Label end = new Label();
        emit(start + " ifFalse" + E2.rvalue().toString() + " goto " + end);
        S.gen();
        E3.gen();
        // Unconditional jump back to label for checking
        emit("goto " + start);
        // Code after the loop
        emit(end + ":");
    }
}