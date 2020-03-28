public class MusicBox {
  public static void main(String[] args) {
    MusicPlayer musicPlayer = new MusicPlayer("player1");
    MusicPlayer2 musicPlayer2 = new MusicPlayer2("player2");

    musicPlayer.start();
//    musicPlayer2.start();
      System.out.println("*****main thread id = " + musicPlayer.getId());

  }
}
