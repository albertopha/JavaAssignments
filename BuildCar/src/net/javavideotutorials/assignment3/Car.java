package net.javavideotutorials.assignment3;

import net.javavideotutorials.assignment3.component.*;

import java.util.ArrayList;
import java.util.List;

public class Car {
  private static final Integer NUM_TIRES_REQ = 4;
  private static final Integer NUM_SEATS_REQ = 5;
  private static final Integer NUM_ENGINE_REQ = 1;
  private static final Integer NUM_FRAME_REQ = 1;

  private List<Tire> tires = new ArrayList<>();
  private List<Seat> seats = new ArrayList<>();
  private List<Engine> engines = new ArrayList<>();
  private List<Frame> frames = new ArrayList<>();

  public Car () {
    build();
  }
  
  /**
   * This is where you should piece together and build each component for the
   * car.
   */
  public void build() {
    System.out.println("Car: start building");

    for (int i = 0; i < NUM_TIRES_REQ; i++) {
      tires.add(new Tire());
    }

    for (int i = 0; i < NUM_SEATS_REQ; i++) {
      seats.add(new Seat());
    }

    for (int i = 0; i < NUM_ENGINE_REQ; i++) {
      engines.add(new Engine());
    }

    for (int i = 0; i < NUM_FRAME_REQ; i++) {
      frames.add(new Frame());
    }
  }

  public Boolean isBuilt() {
    for (Tire tire : tires) {
      if (!tire.isBuilt()) {
        return false;
      }
    }

    for (Seat seat : seats) {
      if (!seat.isBuilt()) {
        return false;
      }
    }

    for (Engine engine : engines) {
      if (!engine.isBuilt()) {
        return false;
      }
    }

    for (Frame frame : frames) {
      if (!frame.isBuilt()) {
        return false;
      }
    }

    return true;
  }
}
