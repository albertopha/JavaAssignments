public class MusicPlayer2 extends Thread {
  private String name;

  public MusicPlayer2(String name) {
    this.name = name;
  }

  public void play() {
    for (int i = 0; i < 10; i++) {
      System.out.println("MusicPlayer2 (" + name + ") is playing.");
    }
  }

  public void pause() {
    for (int i = 0; i < 10; i++) {
      System.out.println("MusicPlayer2 (" + name + ") paused.");
    }
  }

  @Override
  public void run() {
    try {

      this.play();
      Thread.sleep(5000);
      this.pause();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
