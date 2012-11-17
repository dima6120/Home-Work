
package partlycloudy;

import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class CloudTest extends TestCase {
    Mockery context = new Mockery();
    
    @Test
    public void testCloud() {
        final Cloud cloud = new Cloud();
        //final IDayLight daylight = context.mock(IDayLight.class);
        final ILuminary luminary = context.mock(ILuminary.class);
        //final IWind wind = context.mock(IWind.class);
        
        context.checking(new Expectations() {{
            one(luminary).isShining();
            //between(1, 4).of(wind).getPower();
            //one(daylight).current();
            //allowing(wind).getPower();
            //allowing(daylight).current();
        }});
        
        cloud.create();
        
        context.assertIsSatisfied();
    }
}