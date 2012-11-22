
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
    final IDayLight daylight = context.mock(IDayLight.class);
    final IWind wind = context.mock(IWind.class);
    final ILuminary luminary = context.mock(ILuminary.class);
    final IMagic magic = context.mock(IMagic.class);
    final Cloud cloud = new Cloud(wind, luminary, daylight, magic);
    
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
    public void testCloudDynMCreatePuppy() {
        System.out.print("testCloudDynMCreatePuppy:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(true));
                one(wind).getPower(); will(returnValue(1));
                one(daylight).current(); will(returnValue(DayLightType.NIGHT));
                one(magic).callStork(CreatureType.HEDGEHOG);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateKitten() {
        System.out.print("testCloudDynMCreateKitten:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(true));
                one(wind).getPower(); will(returnValue(2));
                one(daylight).current(); will(returnValue(DayLightType.NIGHT));
                one(magic).callStork(CreatureType.KITTTEN);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateHedgehog() {
        System.out.print("testCloudDynMCreateHedgehog:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(false));
                one(wind).getPower(); will(returnValue(0));
                one(daylight).current(); will(returnValue(DayLightType.DAY));
                one(magic).callDaemon(CreatureType.PUPPY);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreatePiglet() {
        System.out.print("testCloudDynMCreatePiglet");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(false));
                one(wind).getPower(); will(returnValue(4));
                one(daylight).current(); will(returnValue(DayLightType.EVENING));
                one(magic).callDaemon(CreatureType.PIGLET);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateBat() {
        System.out.print("testCloudDynMCreateBat:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(false));
                one(wind).getPower(); will(returnValue(7));
                one(daylight).current(); will(returnValue(DayLightType.NIGHT));
                one(magic).callStork(CreatureType.BAT);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateBallon() {
        System.out.print("testCloudDynMCreateBallon:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(true));
                one(wind).getPower(); will(returnValue(7));
                one(daylight).current(); will(returnValue(DayLightType.NIGHT));
                one(magic).callDaemon(CreatureType.BALLOON);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateBearcub() {
        System.out.print("testCloudDynMCreateBearcub:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(false));
                one(wind).getPower(); will(returnValue(9));
                one(daylight).current(); will(returnValue(DayLightType.NIGHT));
                one(magic).callStork(CreatureType.BEARCUB);
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
    
    @Test
    public void testCloudDynMCreateNotCreature() {
        System.out.print("testCloudDynMCreateNotCreature:");
        
        Expectations exp = new Expectations() {{
                one(luminary).isShining(); will(returnValue(true));
                one(wind).getPower(); will(returnValue(2));
                one(daylight).current(); will(returnValue(DayLightType.DAY));
            }};
        context.checking(exp);
           
        cloud.create();

        context.assertIsSatisfied();
        
        System.out.println("ok");
    }
}