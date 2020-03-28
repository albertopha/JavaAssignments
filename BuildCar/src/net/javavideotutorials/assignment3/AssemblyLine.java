package net.javavideotutorials.assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.javavideotutorials.assignment3.component.*;

public class AssemblyLine implements Runnable {
  private static final Integer MAX_COMP_BUILD = 3;

  private static  AssemblyLine assemblyLine = null;
  private Queue<Component> neededPartsQueue = new LinkedList<Component>();
  protected List<Component> componentsBeingBuilt = new ArrayList<Component>();
  private List<Car> carsBuilt = new ArrayList<>();
  public boolean assemblyLineWorking = false;


  private AssemblyLine() {}

  /**
   * This method should return the single instance of the AssemblyLine object.
   * @return the Singleton instance of the <code>AssemblyLine</code> object
   */
  public static synchronized AssemblyLine getInstance() {
    if (assemblyLine == null) {
      assemblyLine = new AssemblyLine();
    }
    return assemblyLine;
  }
  
  /**
   * This method should be used to start the process of building a car.  Remember that
   * each new Car should be built on a new Thread.
   */
  public void buildCar() {
    assemblyLineWorking = true;
    Thread thread = new Thread(AssemblyLine.getInstance());
    thread.start();
  }
  
  /**
   * This method is used to start building a Component.  If the assembly line has room
   * to build a <code>Component</code>, then construction can start immediately, if the assembly line
   * is already at maximum capacity (3 <code>Components</code>), then this <code>Component</code> should be placed
   * on the <code>neededPartsQueue</code>. 
   * @param component to be built
   */
  public synchronized void addComponentToBeBuilt(Component component) {
    assemblyLineWorking = true;
    if (componentsBeingBuilt.size() < MAX_COMP_BUILD) {
      componentsBeingBuilt.add(component);
      Thread thread = new Thread((Runnable) component);
      thread.start();
    } else {
      neededPartsQueue.add(component);
    }
  }

  /**
   * This method should remove the completed component from the ArrayList
   * of components being built.  It should also pull the next component to 
   * be built from the Queue of parts that are waiting to be built.  If there
   * are no more Components to build, then the assembly line should be marked
   * as no longer working.
   * @param component to remove from assembly line
   */
  public synchronized void notifyComponentDoneAssembly(Component component) {
    componentsBeingBuilt.remove(component);

    if (neededPartsQueue.size() > 0) {
      Component next = neededPartsQueue.poll();
      addComponentToBeBuilt(next);
    }

    if (componentsBeingBuilt.size() == 0 && neededPartsQueue.size() == 0) {
      assemblyLineWorking = false;
    }
  }



  @Override
  public void run() {
    System.out.println("Starting to build the car");
    Car car = new Car();
    while (!car.isBuilt()) {
    }
    carsBuilt.add(car);
    System.out.println("Finished building the car");
  }

  /**
   * This method should return a list of all successfully built cars
   * @return list of all cars built
   */
  public List<Car> getCarsBuilt() 
  {
    return carsBuilt;
  }

  public void setCarsBuilt(List<Car> carsBuilt) 
  {
    this.carsBuilt = carsBuilt;
  }
  
}
