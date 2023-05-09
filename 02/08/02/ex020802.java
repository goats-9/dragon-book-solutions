class If extends Stmt {
    Expr E;
    Stmt S;
    public For(Expr e, Stmt s) {
        E = e; S = s;
    }
    public void gen() {
        E.gen();
        Label end = new Label();
        emit("ifEqual " + E.rvalue().toString() + " 0 goto " + end);
        S.gen();
        // Code after the loop
        emit(end + ":");
    }
}