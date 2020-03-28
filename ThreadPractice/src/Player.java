public class Player extends Thread {
  private String name;
  private Integer id;

  public Player(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    synchronized (this) {
//      try {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("****Player: " + this.name + " thread id = " + this.getId());
        for (int i = 0; i < 10; i++) {
          System.out.println("Player: " + this.name + " started running");
        }
        for (int i = 0; i < 10; i++) {
          System.out.println("Player: " + this.name + " finished running");
        }
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
    }

    System.out.println("outside synchronized");
  }
}
