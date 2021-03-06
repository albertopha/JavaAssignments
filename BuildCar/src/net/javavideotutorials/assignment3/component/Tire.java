package net.javavideotutorials.assignment3.component;


import net.javavideotutorials.assignment3.AssemblyLine;

public class Tire implements Component, Runnable {
  private static final String TYPE = "Tire";
  private static final Integer BUILD_TIME = 2000;
  private boolean isBuilt = false;
  private static AssemblyLine assemblyLine = AssemblyLine.getInstance();

  public Tire() {
    assemblyLine.addComponentToBeBuilt(this);
  }

  @Override
  public void run() {
    System.out.println("Starting to build the " + TYPE);

    try {
      Thread.sleep(BUILD_TIME);
      build();
      System.out.println("Finished building the " + TYPE);
      assemblyLine.notifyComponentDoneAssembly(this);
    } catch (InterruptedException e) {
      System.out.println("Failed to build the " + TYPE);
      e.printStackTrace();
    }
  }

  @Override
  public void build() {
    isBuilt = true;
    
  }

  @Override
  public boolean isBuilt() {
    return isBuilt;
  }

  @Override
  public String getComponentType() {
    return TYPE;
  }
}
