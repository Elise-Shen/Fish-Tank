package fishtank;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Utilities for auto-testing.
 *
 * The reason for the duplicated constants in here is that we can't assume
 * you haven't changed FishTank.
 */
public class TestUtilities {

    /**
     * The width of a character.
     */
    private static final int charWidth = 6;
    /**
     * The height of a character.
     */
    private static final int charHeight = 10;

    /**
     * The width (in pixels) of the frame
     */
    private static final int frameWidth = 640;

    /**
     * The height (in pixels) of the frame
     */
    private static final int frameHeight = 480;

    /**
     * The width (in entities) of the whole tank
     */
    static final int WIDTH = frameWidth/charWidth;

    /**
     * The height (in entities) of the whole tank
     */
    static final int HEIGHT = frameHeight/charHeight;

    private static void setFinalStatic(Field field, Object newValue)
        throws NoSuchFieldException, IllegalAccessException {
        //source: https://stackoverflow.com/questions/3301635/change-private-static-final-field-using-java-reflection
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    static void clearWholeFishtank() {
        //note: some magic is going on here, you might've renamed the storage in
        //fishtank, so I have to take some liberties.
        for(Field f : FishTank.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object o = f.get(null);
                if(o instanceof FishTankEntity[][]) {
                    FishTankEntity[][] storage = (FishTankEntity[][])o;
                    setFinalStatic(f, new FishTankEntity[storage.length][storage[0].length]);
                } else if(o instanceof Collection) {
                    //So... if you're following up to this point, if you've changed storage to be a Set, we call
                    //s = new Set<>();
                    setFinalStatic(f, o.getClass().getDeclaredConstructor().newInstance());
                }
            } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    static void assertInRange(int value, int lower, int upper) {
        assertTrue(
            "" + value + "should be in [" + lower + ", " + upper + "].",
            lower <= value && value <= upper
            );
    }

    static void addMocked(Class<? extends FishTankEntity> clazz, int x, int y) {
        FishTankEntity e = mock(clazz);
        when(e.getX()).thenReturn(x);
        when(e.getY()).thenReturn(y);
        FishTank.addEntity(x, y, e);
    }
}
