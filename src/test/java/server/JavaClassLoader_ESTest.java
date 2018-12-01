/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 01 11:51:29 GMT 2018
 */

package server;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.ArrayList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import server.JavaClassLoader;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class JavaClassLoader_ESTest extends JavaClassLoader_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      JavaClassLoader javaClassLoader0 = new JavaClassLoader((String[]) null);
      // Undeclared exception!
      try { 
        JavaClassLoader.getClasses(javaClassLoader0, "Commands:\n/select <chatID>: select one of the chats\n/chats: get list of possible chats\n/custom: get list of custom commands\n/disconnect: disconnect");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.mock.java.net.MockURL", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      String[] stringArray0 = new String[3];
      stringArray0[0] = "{WB7ohc_Vz";
      JavaClassLoader javaClassLoader0 = new JavaClassLoader(stringArray0);
      Object object0 = javaClassLoader0.call();
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      String[] stringArray0 = new String[3];
      JavaClassLoader javaClassLoader0 = new JavaClassLoader(stringArray0);
      ArrayList<Class> arrayList0 = (ArrayList<Class>)JavaClassLoader.getClasses(javaClassLoader0, "log4j.properties");
      assertTrue(arrayList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      String[] stringArray0 = new String[3];
      JavaClassLoader javaClassLoader0 = new JavaClassLoader(stringArray0);
      Object object0 = javaClassLoader0.call();
      assertNull(object0);
  }
}
