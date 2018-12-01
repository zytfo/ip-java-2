/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 01 11:52:36 GMT 2018
 */

package client;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import client.Client;
import client.InputReader;
import client.MessageReceiver;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Client_ESTest extends Client_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      MessageReceiver messageReceiver0 = mock(MessageReceiver.class, new ViolatedAssumptionAnswer());
      InputReader inputReader0 = mock(InputReader.class, new ViolatedAssumptionAnswer());
      Client client0 = new Client(messageReceiver0, inputReader0);
      client0.init();
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      InputReader inputReader0 = mock(InputReader.class, new ViolatedAssumptionAnswer());
      Client client0 = new Client((MessageReceiver) null, inputReader0);
      // Undeclared exception!
      try { 
        client0.init();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("client.Client", e);
      }
  }
}