public class MusicPlayer extends Thread {
  private String name;

  public MusicPlayer(String name) {
    this.name = name;
  }

  public void play() {
    for (int i = 0; i < 2; i++) {
      try {
        System.out.println("MusicPlayer (" + name + ") is playing.");
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

  public void pause() {
    for (int i = 0; i < 10; i++) {
      System.out.println("MusicPlayer (" + name + ") paused.");
    }
  }

  @Override
  public void run() {
    System.out.println("****MusicPlayer: MusicPlayer thread id = " + this.getId());
    Player player1 = new Player("player1");
    Player player2 = new Player("player2");
    System.out.println("****MusicPlayer: player1 thread id = " + player1.getId());
    System.out.println("****MusicPlayer: player2 thread id = " + player2.getId());

    try {
      this.play();
      player1.start();
      player2.start();
      Thread.sleep(5000);
      this.pause();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
