package net.javavideotutorials.assignment4;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

/**
 * If all the tests below pass, then you have completed the assignment.
 * Note: you must put some code into the @Before init() method before these
 * tests will successfully run. 
 * 
 * @author Trevor Page
 *
 */
public class Tests 
{
  AnagramSolver sut = null;
  
  @Before
  public void init ()
  {
    sut = new AnagramSolverImpl();
  }
  
  @Test
  public void should_return_false_with_two_null_values ()
  {
    boolean anAnagram = sut.isAnAnagram(null, null);
    
    assertThat(anAnagram, is(false));
  }
  
  @Test
  public void should_return_false_with_one_null_value ()
  {
    boolean anAnagram = sut.isAnAnagram(null, "JavaVideoTutorials");
    
    assertThat(anAnagram, is(false));
  }
  
  @Test
  public void should_return_false_if_two_words_are_different_lengths ()
  {
    boolean anAnagram = sut.isAnAnagram("tool", "tools");
    
    assertThat(anAnagram, is(false));
  }
  
  @Test
  public void should_return_true_for_anagrams ()
  {
    boolean anAnagram = sut.isAnAnagram("cloud", "could");
    
    assertThat(anAnagram, is(true));
  }
  
  @Test
  public void should_handle_duplicate_letters ()
  {
    boolean anAnagram = sut.isAnAnagram("tool", "toll");
    
    assertThat(anAnagram, is(false));
  }
  
  @Test
  public void should_treat_uppercase_and_lowercase_equally ()
  {
    boolean anAnagram = sut.isAnAnagram("CARE", "race");
    
    assertThat(anAnagram, is(true));
  }
  
}
