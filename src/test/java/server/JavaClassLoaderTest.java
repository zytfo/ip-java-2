package server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

public class JavaClassLoaderTest {
    @Mock
    ClassLoader parent;
    @Mock
    ConcurrentHashMap<String, Object> parallelLockMap;
    @Mock
    Map<String, Certificate> package2certs;
    @Mock
    Vector<Class<?>> classes;
    @Mock
    ProtectionDomain defaultDomain;
    @Mock
    Set<ProtectionDomain> domains;
    @Mock
    HashMap<String, Package> packages;
    @Mock
    ClassLoader scl;
    @Mock
    Vector<String> loadedLibraryNames;
    @Mock
    Vector<ClassLoader> systemNativeLibraries;
    @Mock
    Vector<ClassLoader> nativeLibraries;
    @Mock
    Stack<ClassLoader> nativeLibraryContext;
    @Mock
    Object assertionLock;
    @Mock
    Map<String, Boolean> packageAssertionStatus;
    @Mock
    Map<String, Boolean> classAssertionStatus;
    @InjectMocks
    JavaClassLoader javaClassLoader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCall() throws Exception {
        Object result = javaClassLoader.call();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetClasses() throws Exception {
        ArrayList<Class> result = JavaClassLoader.getClasses(new JavaClassLoader(new String[]{"arguments"}), "pack");
        Assert.assertEquals(new ArrayList<Class>(Arrays.asList(Class.forName("server.JavaClassLoader"))), result);
    }
}