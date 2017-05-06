package es.ganchix.morphia.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mongodb.morphia.utils.ReflectionUtils;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

/**
 * Created by Rafael Ríos on 5/05/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReflectionUtils.class)
public class MorphiaFailUtilsTest {

    private final String PACKAGE_NAME = "es.ganchix.morphia.utils.context.normal";

    @Test(expected = Exception.class)
    public void testCallGetClassesWithFail_ShouldReturnAException() throws IOException, ClassNotFoundException {
        PowerMockito.mockStatic(ReflectionUtils.class);
        BDDMockito.given(ReflectionUtils.getClasses(PACKAGE_NAME, true)).willThrow(new Exception());
        MorphiaUtils.getClasses(PACKAGE_NAME);
    }


}
