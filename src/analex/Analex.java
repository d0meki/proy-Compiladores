package analex;

public class Analex {
    private Cinta M;
    private Token R;
    private String ac;
    private int pos; // Posición de inicio del lexema del preanalisis(), calculado en el dt().
                     // Use Cinta.getPos() o sea pos=M.getPos();

    public Analex(Cinta c) {
        M = c;
        R = new Token();
        // init();
    }

    public final void init() {
        M.init();
        avanzar(); // Calcular el primer preanalisis.
    }

    public Token Preanalisis() {
        return R;
    }

    public String lexema() {
        return ac;
    }

    public void avanzar() {
        dt();
    }

    private void dt() {
        int estado = 0;
        this.ac = "";
        while (true) {
            System.out.println("sigue en el while");
            switch (estado) {
                case 0:
                    System.out.println("El estado es 0");
                    switch (this.M.cc()) {
                        case ':':
                            this.M.avanzar();
                            estado = 11;
                            break;
                        case ',':
                            this.M.avanzar();
                            estado = 7;
                            break;
                        case ';':
                            this.M.avanzar();
                            estado = 9;
                            break;
                        case '(':
                            this.M.avanzar();
                            estado = 15;
                            break;
                        case ')':
                            this.M.avanzar();
                            estado = 17;
                            break;
                        case '{':
                            this.M.avanzar();
                            estado = 19;
                            break;
                        case '}':
                            this.M.avanzar();
                            estado = 21;
                            break;
                        case '!':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 38;
                            break;
                        case '&':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 42;
                        case '|':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 44;
                        case '+':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 25;
                            break;
                        case '-':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 3;
                            break;
                        case '*':
                            this.M.avanzar();
                            estado = 23;
                            break;
                        case '%':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 51;
                        case '/':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 46;
                            break;
                        case '=':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 36;
                            break;
                        case '>':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 32;
                            break;
                        case '<':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 28;
                            break;
                        case '"':
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 55;
                            break;
                        default:
                            if (this.esEspacio(this.M.cc())) {
                                this.M.avanzar();
                                estado = 0;
                            } else {
                                if (this.esLetra(this.M.cc())) {
                                    this.ac += this.M.cc();
                                    this.M.avanzar();
                                    estado = 53;
                                } else {
                                    if (this.M.cc() == Cinta.EOF) {
                                        estado = 1;
                                    } else {
                                        estado = 2;
                                    }
                                }
                            }

                            break;
                    }
                    break;
                case 1:
                    this.R = new Token(Token.FIN);
                    // estado = 0;
                    return;
                case 2:
                    this.R = new Token(Token.ERROR);
                    // this.ac = "EOF";
                    return;
                case 3:
                    if (this.M.cc() == '-') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 5;
                        break;
                    } else {
                        estado = 4;
                    }
                    break;
                case 4:
                    this.R = new Token(Token.MENOS);
                    return;
                case 5:
                    estado = 6;
                    break;
                case 6:
                    this.R = new Token(Token.DEC);
                    return;
                case 7:
                    estado = 8;
                    break;
                case 8:
                    this.R = new Token(Token.COMA);
                    return;
                case 9:
                    estado = 10;
                    break;
                case 10:
                    this.R = new Token(Token.PTOCOMA);
                    return;
                case 11:
                    if (this.M.cc() == '=') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 13;
                        break;
                    } else {
                        estado = 12;
                    }
                    break;
                case 12:
                    this.R = new Token(Token.DOSPUNTOS);
                    return;
                case 13:
                    estado = 14;
                    break;
                case 14:
                    this.R = new Token(Token.ASSIGN);
                    return;
                case 15:
                    estado = 16;
                    break;
                case 16:
                    this.R = new Token(Token.PA);
                    return;
                case 17:
                    estado = 18;
                    break;
                case 18:
                    this.R = new Token(Token.PC);
                    return;
                case 19:
                    estado = 20;
                    break;
                case 20:
                    this.R = new Token(Token.LA);
                    return;
                case 21:
                    estado = 22;
                    break;
                case 22:
                    this.R = new Token(Token.LC);
                    return;
                case 23:
                    estado = 24;
                    break;
                case 24:
                    this.R = new Token(Token.POR);
                    return;
                case 25:
                    if (this.M.cc() == '+') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 27;
                        break;
                    } else {
                        estado = 26;
                    }
                    break;

                case 26:
                    this.R = new Token(Token.MAS);
                    return;
                case 27:
                    this.R = new Token(Token.INC);
                    return;
                case 28:
                    if (this.M.cc() == '=') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 30;
                        break;
                    } else {
                        estado = 29;
                    }
                    break;
                case 29:
                    this.R = new Token(Token.MEN);
                    return;
                case 30:
                    estado = 31;
                    break;
                case 31:
                    this.R = new Token(Token.MEI);
                    return;
                case 32:
                    if (this.M.cc() == '=') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 34;
                        break;
                    } else {
                        estado = 33;
                    }
                    break;
                case 33:
                    this.R = new Token(Token.MAY);
                    return;
                case 34:
                    estado = 35;
                    return;
                case 35:
                    this.R = new Token(Token.MAI);
                    return;
                case 36:
                    estado = 37;
                    break;
                case 37:
                    this.R = new Token(Token.IGUAL);
                    return;
                case 38:
                    if (this.M.cc() == '=') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 40;
                        break;
                    } else {
                        estado = 39;
                    }
                case 39:
                    this.R = new Token(Token.NOT);
                    return;
                case 40:
                    estado = 41;
                    break;
                case 41:
                    this.R = new Token(Token.OPREL, Token.DIS);
                    return;
                case 42:
                    estado = 43;
                    break;
                case 43:
                    this.R = new Token(Token.AND);
                    return;
                case 44:
                    estado = 45;
                    break;
                case 45:
                    this.R = new Token(Token.OR);
                    return;

                case 46:
                    if (this.M.cc() == '/') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 50;
                    } else {
                        if (this.M.cc() == '*') {
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 48;
                        }else{
                            estado = 47;
                        }
                    }
                    break;
                case 47:
                    this.R = new Token(Token.DIV);
                    return;
                case 48:
                    if (this.M.cc() != '*' && this.M.cc() != Cinta.EOF) {
                        estado = 48;
                    } else {
                        if (this.M.cc() == '*') {
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 49;
                        } else {// EOF error, no cerré comentario multilínea
                            estado = 2;
                        }
                    }
                    break;
                case 49:
                    if (this.M.cc() != '/' && this.M.cc() != Cinta.EOF) {
                        estado = 48;
                    } else {
                        if (this.M.cc() == '/') {
                            // this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 0;
                        } else {// EOF error, no cerré comentario multilínea
                            estado = 2;
                        }
                    }
                    break;
                case 50:
                    if (this.M.cc() != Cinta.EOLN && this.M.cc() != Cinta.EOF) {
                        estado = 50;
                    } else {
                        if (this.M.cc() == Cinta.EOLN) {
                            // this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 0;
                        } else {// EOF error, no cerré comentario multilínea
                            estado = 1;
                        }
                    }
                    break;
                case 51:
                    estado = 52;
                    break;
                case 52:
                    this.R = new Token(Token.MOD);
                    return;
                case 53:
                    System.out.println("entró al 53");
                    if (this.esLetra(this.M.cc()) || this.esDigito(this.M.cc())) {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 53;
                    } else {
                        estado = 54;
                    }
                    break;
                case 54:
                    this.R = Token.verificar_tablareservada(this.ac);
                    if (this.R != null) {
                        return;
                    } else {
                        int pTS = -1;
                        R = new Token(Token.ID, pTS);
                    }
                    return;
                case 55:
                    if (this.M.cc() == '"') {
                        this.ac += this.M.cc();
                        this.M.avanzar();
                        estado = 56;
                    } else {
                        if (this.M.cc() != '"' &&
                                this.esEOL(this.M.cc()) &&
                                this.esEOF(this.M.cc())) {
                            this.ac += this.M.cc();
                            this.M.avanzar();
                            estado = 55;
                        } else {
                            if (this.esEOL(this.M.cc()) || this.esEOF(this.M.cc())) {
                                this.M.avanzar();
                                estado = 2;
                            }
                        }
                    }
                    break;
                case 56:
                    estado = 57;
                    break;
                case 57:
                    this.R = new Token(Token.STRINGctte);
                    return;
                default:
                    System.out.println("Tampoco entra por el default");
                    break;
            }
        }
    }

    public void resaltar() { // Para resaltar el lexema del Preanalisis en el progFuente.
        comunicate(pos, lexema());
    }

    public void comunicate(int pos, String lexema) { // Overridable. Para la Interfaz.

    }

    private Boolean esLetra(char c) {
        return ((c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z') ||
                c == '_');
    }

    private Boolean esDigito(char c) {
        return (c >= '0' && c <= '9');
    }

    private Boolean esEOL(char c) {
        return c == 10;
    }

    private Boolean esEOF(char c) {
        return c == 0;
    }

    private Boolean esEspacio(char c) {
        return c == ' ' || c == Cinta.EOLN;
    }

}
