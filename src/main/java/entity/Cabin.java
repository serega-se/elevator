package entity;

import java.text.MessageFormat;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Cabin implements Runnable{

    Logger log = Logger.getLogger(Cabin.class.getName());

    public enum Direction{
        UP,
        DOWN
    }

    private final Integer maxFloor;
    private final Integer minFloor;
    private final NavigableSet<Integer> floorsCalls;
    private Integer currentFloor;
    private Direction direction;


    /**
     * @param minFloor Максимальный этаж на который поднимается лифт
     * @param maxFloor Минимальный этаж на который опускается лифт
     */
    public Cabin(Integer minFloor, Integer maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.floorsCalls = new TreeSet<>();
        this.currentFloor = 1;
        this.direction = Direction.UP;
    }

    /**
     * @return текущий список всех вызовов лифта в виде строки. Пример: [1, 2, 3]
     */
    public String getFloorsCalls() {
        return floorsCalls.toString();
    }

    /**
     * @return Текущий этаж на котором находится лифт
     */
    public Integer getCurrentFloor() {
        return currentFloor;
    }

    /**
     * @return текущее направление движения лифта
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return Максимальный этаж на который поднимается лифт
     */
    public Integer getMaxFloor() {
        return maxFloor;
    }

    /**
     * @return Минимальный этаж на который опускается лифт
     */
    public Integer getMinFloor() {
        return minFloor;
    }

    /**
     * @param floor Добавление этажа с определенным номером в список вызовов лифта
     */
    public void addElevatorCall(int floor){
        log.info(MessageFormat.format("Cabin resive call to {0} floor", floor));
        if(floor > maxFloor) return;
        if(floor < minFloor) return;

        this.floorsCalls.add(floor);
        log.info(MessageFormat.format("Cabin set of call {0} floors", floorsCalls.toString()));
    }

    /**
     * Основной цикл работы лифта. Реализация интерфейса Runnable,
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (!this.floorsCalls.isEmpty()) {
                    this.move();
                }
                Thread.sleep(500);
            }
        }catch (InterruptedException e) {
            log.info(String.valueOf(e.getMessage()));
        }
    }

    /**
     * Метод убирает текущий этаж из списка вызовов
     * с последующим движением лифта на один этаж вверх или вниз,
     * в зависимости от актуального списка вызовов, если таковые присутствуют.
     */
    private void move(){
        log.info(MessageFormat.format("Cabin in {0} floor", this.currentFloor));
        this.floorsCalls.remove(this.currentFloor);
        Integer next;
        switch (this.direction) {
            case UP:
                next = this.floorsCalls.higher(this.currentFloor);
                if (next != null) {
                    this.currentFloor++;
                }else{
                    this.direction = Direction.DOWN;
                }
                break;
            case DOWN:
                next = this.floorsCalls.lower(this.currentFloor);
                if (next != null) {
                    this.currentFloor--;
                }else{
                    this.direction = Direction.UP;
                }
                break;
        }
    }
}
