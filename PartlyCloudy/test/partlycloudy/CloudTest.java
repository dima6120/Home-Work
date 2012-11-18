
package partlycloudy;

import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class CloudTest extends TestCase {
    Mockery context = new Mockery();
    class LumIsS implements ILuminary {
        @Override
        public boolean isShining() {
            return true;
        }
    }
    @Test
    public void testCloud() {
        final IDayLight daylight = context.mock(IDayLight.class);
        final IWind wind = context.mock(IWind.class);
        final ILuminary luminary = context.mock(ILuminary.class);
        final IMagic magic = context.mock(IMagic.class);
        //context.setDefaultResultForType(ILuminary.class, LumIsS.class);
        final Cloud cloud = new Cloud(wind, luminary, daylight, magic);
        context.checking(new Expectations() {{
            one(luminary).isShining();
            between(1, 3).of(wind).getPower();
            between(0,1).of(daylight).current();
        }});
        
        cloud.create();
        
        context.assertIsSatisfied();
    }
}