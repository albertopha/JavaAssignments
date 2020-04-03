package net.javavideotutorials.assignment5;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Spy;

public class Tests {
  Factorial sut = new Factorial();
  @Spy Factorial spy = spy(sut);
  
  @Test
  public void should_return_6_for_3_factorial() {
    assertThat(sut.factorial(3), is(6));
  }
  
  @Test
  public void should_return_24_for_4_factorial() {
    assertThat(sut.factorial(4), is(24));
  }
  
  @Test
  public void should_return_120_for_5_factorial() {
    assertThat(sut.factorial(5), is(120));
  }
  
  @Test
  public void should_return_720_for_6_factorial() {
    assertThat(sut.factorial(6), is(720));
  }
  
  // The purpose of this test is to ensure that you are
  // completing this assignment as a recurring algorithm.
  @Test
  public void ensure_this_is_a_recursive_program () {
    spy.factorial(6);
    
    verify(spy, atLeast(5)).factorial(anyInt());
    
  }
}
