/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 01 11:54:48 GMT 2018
 */

package client;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import client.MessageReceiver;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class MessageReceiver_ESTest extends MessageReceiver_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      byte[] byteArray0 = new byte[7];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 0, (byte) (-97));
      DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
      MessageReceiver messageReceiver0 = new MessageReceiver(dataInputStream0);
      messageReceiver0.stopReceiving();
  }


  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      byte[] byteArray0 = new byte[7];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 0, (byte) (-97));
      DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
      MessageReceiver messageReceiver0 = new MessageReceiver(dataInputStream0);
      messageReceiver0.startReceiving();
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      MessageReceiver messageReceiver0 = new MessageReceiver((DataInputStream) null);
      // Undeclared exception!
      try { 
        messageReceiver0.stopReceiving();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("client.MessageReceiver", e);
      }
  }
}
