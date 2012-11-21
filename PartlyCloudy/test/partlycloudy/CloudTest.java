
package partlycloudy;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.hamcrest.core.AnyOf;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.junit.Test;

public class CloudTest extends TestCase {
    Mockery context = new Mockery();
    
    class MWind implements IWind {
        private int i;

        public MWind(int i) {
            this.i = i;
        }
        @Override
        public int getPower() {
            return i;
        }
    }

    class MLum implements ILuminary {
        private boolean b;

        public MLum(boolean b) {
            this.b = b;
        }
        @Override
        public boolean isShining() {
            return b;
        }

    }

    class MDL implements IDayLight {
        private DayLightType dl;

        public MDL(DayLightType dl) {
            this.dl = dl;
        }
        @Override
        public DayLightType current() {
            return dl;
        }

    }
    
    @Test
    public void testCloudStaticMocks() {
        Cloud cloud;
        BMagic m = new BMagic();
        
        IWind []ws = {new MWind(0), new MWind(1), new MWind(2), new MWind(3),
                      new MWind(4), new MWind(5), new MWind(6), new MWind(7),
                      new MWind(8), new MWind(9), new MWind(10)};
        
        ILuminary FLum = new MLum(false), TLum = new MLum(true);
        
        ILuminary []ls = {FLum, TLum, FLum, TLum, TLum, FLum, FLum, FLum, TLum,
                          FLum, TLum};
        
        IDayLight DDL = new MDL(DayLightType.DAY);
        IDayLight MDL = new MDL(DayLightType.MORNING);
        IDayLight EDL = new MDL(DayLightType.EVENING);
        IDayLight NDL = new MDL(DayLightType.NIGHT);
        
        IDayLight []dls = {DDL, NDL, EDL, MDL, NDL, EDL, NDL, NDL, DDL, MDL, NDL};
        
        CreatureType []er = {CreatureType.PUPPY, CreatureType.HEDGEHOG, 
                             CreatureType.PUPPY, CreatureType.KITTTEN,
                             CreatureType.HEDGEHOG, CreatureType.PIGLET,
                             CreatureType.PIGLET, CreatureType.BAT,
                             CreatureType.BALLOON, CreatureType.BEARCUB,
                             CreatureType.BALLOON};
        
        System.out.println("testCloudStaticMocks");
        
        for (int i = 0; i < 11; i++) {
            cloud = new Cloud(ws[i], ls[i], dls[i], m);
            assertEquals(er[i], cloud.create().getCreatureType());
            System.out.println(i);
        }
    }
    @Test
    public void testCloudDynamicMocks() {
        final IDayLight daylight = context.mock(IDayLight.class);
        final IWind wind = context.mock(IWind.class);
        final ILuminary luminary = context.mock(ILuminary.class);
        final IMagic magic = context.mock(IMagic.class);
        final Cloud cloud = new Cloud(wind, luminary, daylight, magic);    
               
        System.out.println("testCloudDynamicMocks");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(false)/*doAll(
                                                        returnValue(false),
                                                        returnValue(true),
                                                        returnValue(false),
                                                        returnValue(true),
                                                        returnValue(true),
                                                        returnValue(false),
                                                        returnValue(false),
                                                        returnValue(false),
                                                        returnValue(true),
                                                        returnValue(false),
                                                        returnValue(true),
                                                        returnValue(true)
                                                     )*/
                                               );
                one(wind).getPower(); will(returnValue(0)/*doAll(
                                                    returnValue(0),
                                                    returnValue(1),
                                                    returnValue(2),
                                                    returnValue(3),
                                                    returnValue(4),
                                                    returnValue(5),
                                                    returnValue(6),
                                                    returnValue(7),
                                                    returnValue(8),
                                                    returnValue(9),
                                                    returnValue(10),
                                                    returnValue(2)
                                                 )*/
                                           );
                one(daylight).current(); will( returnValue(DayLightType.DAY)
                                                /*doAll(
                                                        returnValue(DayLightType.DAY),
                                                        returnValue(DayLightType.NIGHT),
                                                        returnValue(DayLightType.EVENING),
                                                        returnValue(DayLightType.MORNING),
                                                        returnValue(DayLightType.NIGHT),
                                                        returnValue(DayLightType.EVENING),
                                                        returnValue(DayLightType.NIGHT),
                                                        returnValue(DayLightType.NIGHT),
                                                        returnValue(DayLightType.DAY),
                                                        returnValue(DayLightType.MORNING),
                                                        returnValue(DayLightType.NIGHT),
                                                        returnValue(DayLightType.DAY)
                                                     )*/
                                               );
                one(magic).callDaemon(CreatureType.PUPPY);
                /*atMost(1).of(magic).callDaemon(with(
                                                    Matchers.anyOf(equal(CreatureType.PUPPY),
                                                                   equal(CreatureType.PIGLET),
                                                                   equal(CreatureType.BALLOON)
                                                                  )
                                                  )
                                             ); 
                atMost(1).of(magic).callStork(with(
                                                    Matchers.anyOf(equal(CreatureType.BAT),
                                                                   equal(CreatureType.BEARCUB),
                                                                   equal(CreatureType.HEDGEHOG),
                                                                   equal(CreatureType.KITTTEN)
                                                                  )
                                                  )
                                             );*/
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
    }
}