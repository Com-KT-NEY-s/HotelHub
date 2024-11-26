package Sexao;

// Class to manage the session, specifically the logged-in user.
public class Sexsao {

    private static String usuarioLogado; // Stores the username of the currently logged-in user.

    // Getter to retrieve the logged-in user.
    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    // Setter to update the logged-in user.
    public static void setUsuarioLogado(String usuarioLogado) {
        Sexsao.usuarioLogado = usuarioLogado;
    }

}
