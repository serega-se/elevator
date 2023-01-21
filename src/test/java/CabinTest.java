import entity.Cabin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Тестирование лифта
 */
public class CabinTest {

    @InjectMocks
    private Cabin cabin = new Cabin(1,9);

    /**
     * Тестирование создания обьекта лифта, и установки дефолтных значений
     */
    @Test
    @DisplayName("Cabine create method tests")
    public void testCreate(){
        Assertions.assertEquals( 1 , cabin.getCurrentFloor());
        Assertions.assertEquals( Cabin.Direction.UP , cabin.getDirection());
        Assertions.assertEquals( 1 , cabin.getMinFloor());
        Assertions.assertEquals( 9 , cabin.getMaxFloor());
    }

    /**
     * Тестирование добавления вызовов лифта в список вызовов
     */
    @Test
    @DisplayName("Cabine add elevator call method tests")
    public void testAddElevatorCall(){
        cabin.addElevatorCall(10);
        Assertions.assertEquals( 1 , cabin.getCurrentFloor());
        cabin.addElevatorCall(0);
        Assertions.assertEquals( 1 , cabin.getCurrentFloor());
        cabin.addElevatorCall(1);
        cabin.addElevatorCall(1);
        cabin.addElevatorCall(2);
        cabin.addElevatorCall(3);
        Assertions.assertEquals( "[1, 2, 3]" , cabin.getFloorsCalls());
    }

    /**
     * Тестирование движений лифта в зависимости от указанных параметров
     */
    @Test()
    @DisplayName("Cabine move method testing tests")
    public void testMove() throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = Cabin.class.getDeclaredMethod("move");
        method.setAccessible(true);

        cabin.addElevatorCall(5);
        method.invoke(cabin);
        Assertions.assertEquals( 2 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 3 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 4 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 5 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 5 , cabin.getCurrentFloor());
        cabin.addElevatorCall(1);
        method.invoke(cabin);
        Assertions.assertEquals( 4 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 3 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 2 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 1 , cabin.getCurrentFloor());
        method.invoke(cabin);
        Assertions.assertEquals( 1 , cabin.getCurrentFloor());
    }

}
