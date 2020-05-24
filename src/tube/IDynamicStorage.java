package tube;

import java.util.List;

public interface IDynamicStorage<E, T> {

    public T get(E identifer);

    public void modify(E identifier, T object);

}
