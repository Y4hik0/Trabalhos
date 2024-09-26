import java.io.File;

public class TesteCrypto {
    public static void main(String[] args) throws Exception {
        String sMsgClara = "Funcionou?";
        String sMsgCifrada = null;
        String sMsgDecifrada = null;
        byte[] bMsgClara = sMsgClara.getBytes("ISO-8859-1");
        byte[] bMsgCifrada;
        byte[] bMsgDecifrada;

        // Instancia objeto da classe Impressora
        Impressora prn = new Impressora();
        // Imprime marcador de bloco
        System.out.println("---------------------");
        // Imprime Texto
        System.out.println(">>> Imprimindo mensagem original...");
        System.out.println("");

        // Imprime cabeçalho da mensagem
        System.out.println("Mensagem Clara (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgClara));
        System.out.println("");

        // Imprime cabeçalho da mensagem
        System.out.println("Mensagem Clara (String):");
        System.out.println(sMsgClara);
        System.out.println("");

        /*
         * Criptografia Dummy -------
         */
        System.out.println(">>> Cifrando com o algoritmo Dummy...");
        System.out.println("");

        CryptoDummy cdummy = new CryptoDummy();
        File chaveDummy = new File("chave.dummy");
        
        // Verifica se a chave existe, caso contrário, deve gerar
        if (!chaveDummy.exists()) {
            cdummy.geraChave(chaveDummy);
        }

        cdummy.geraCifra(bMsgClara, chaveDummy);
        bMsgCifrada = cdummy.getTextoCifrado();
        sMsgCifrada = new String(bMsgCifrada, "ISO-8859-1");

        // Imprime mensagens cifradas
        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgCifrada));
        System.out.println("");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada);
        System.out.println("");

        System.out.println(">>> Decifrando com o algoritmo Dummy...");
        System.out.println("");
        cdummy.geraDecifra(bMsgCifrada, chaveDummy);
        bMsgDecifrada = cdummy.getTextoDecifrado();
        sMsgDecifrada = new String(bMsgDecifrada, "ISO-8859-1");

        // Imprime mensagens decifradas
        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgDecifrada));
        System.out.println("");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada);
        System.out.println("");

        /*
         * Criptografia AES -------- 
         */
        System.out.println(">>> Criptografando com o algoritmo AES...");
        System.out.println("");
        CryptoAES caes = new CryptoAES();
        File chaveAES = new File("chave.simetrica");

        if (!chaveAES.exists()) {
            caes.geraChave(chaveAES);
        }
        
        caes.geraCifra(bMsgClara, chaveAES);
        bMsgCifrada = caes.getTextoCifrado();
        sMsgCifrada = new String(bMsgCifrada, "ISO-8859-1");

        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgCifrada));
        System.out.println("");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada);
        System.out.println("");

        System.out.println(">>> Decifrando com o algoritmo AES...");
        System.out.println("");
        caes.geraDecifra(bMsgCifrada, chaveAES);
        bMsgDecifrada = caes.getTextoDecifrado();
        sMsgDecifrada = new String(bMsgDecifrada, "ISO-8859-1");

        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgDecifrada));
        System.out.println("");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada);
        System.out.println("");

        /*
         * Criptografia RSA -------- 
         */
        System.out.println(">>> Cifrando com o algoritmo RSA...");
        System.out.println("");
        CryptoRSA crsa = new CryptoRSA();
        File chavePublica = new File("chave.publica");
        File chavePrivada = new File("chave.privada");

        crsa.geraParDeChaves(chavePublica, chavePrivada);
        crsa.geraCifra(bMsgClara, chavePublica);
        bMsgCifrada = crsa.getTextoCifra();
        sMsgCifrada = new String(bMsgCifrada, "ISO-8859-1");

        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgCifrada));
        System.out.println("");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada);
        System.out.println("");

        System.out.println(">>> Decifrando com o algoritmo RSA...");
        System.out.println("");
        crsa.geraDecifra(bMsgCifrada, chavePrivada);
        bMsgDecifrada = crsa.getTextoDecifrado();
        sMsgDecifrada = new String(bMsgDecifrada, "ISO-8859-1");

        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.print(prn.hexBytesToString(bMsgDecifrada));
        System.out.println("");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada);
        System.out.println("");
    }
}
