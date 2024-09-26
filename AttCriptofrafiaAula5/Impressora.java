public class Impressora {
  public String hexBytesToString(byte[] b) {
      String sOut = "";
      String sSpc = "                                               ";

      for (int i = 0; i < b.length; i++) {
          String sBgn = "";
          String sMd1 = "";
          String sEnd = "";

          if (i % 16 == 0) {
              sBgn += Integer.toHexString(i & 0xFFFF | 0X10000).substring(1, 5) + " - ";
          }

          sMd1 += Integer.toHexString(b[i] & 0xFF | 0x100).substring(1, 3) + " ";

          if (b[i] >= 32 && b[i] <= 126) {
              sEnd += (char) b[i];
          } else {
              sEnd += ".";
          }

          if ((i % 16 == 15) || (i == b.length - 1)) { // Corrigido para 'b.length - 1'
              int startIndex = 3 * ((i % 16) + 1);
              if (startIndex < sSpc.length()) { // Verifica se o índice está dentro do limite
                  sOut += sBgn + sMd1 + sSpc.substring(startIndex, sSpc.length()) + " - " + sEnd + "\n";
              } else {
                  sOut += sBgn + sMd1 + " [Índice fora do limite] - " + sEnd + "\n"; // Tratamento para índices inválidos
              }
          }
      }
      return sOut;
  }
}