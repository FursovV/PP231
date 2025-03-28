package web.servise;

import org.springframework.stereotype.Component;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServise {
    private static int CARS_COUNT;
    private List<Car> cars;

    {
        cars = new ArrayList<>();
        cars.add(new Car(++CARS_COUNT, "LADA", "BLUE"));
        cars.add(new Car(++CARS_COUNT, "KIA", "RED"));
        cars.add(new Car(++CARS_COUNT, "AUDI", "YELLOW"));
        cars.add(new Car(++CARS_COUNT, "BMW", "GREEN"));
        cars.add(new Car(++CARS_COUNT, "MERCEDES", "BLACK"));
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> show(Integer count) {
        if (count == null || count <= 0 || cars.size() < count) {
            return new ArrayList<>(cars);
        } else {
            return new ArrayList<>(cars.subList(0, count));
        }
    }
}

