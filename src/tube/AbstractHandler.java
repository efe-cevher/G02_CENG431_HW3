package tube;

import java.util.Map;

public abstract class AbstractHandler<E, T> implements IDynamicStorage<E, T>{

    private final IFormatter<Map<E, T>> formatter;
    private final Map<E, T> dataMap;
    private final IStorage storage;

    protected AbstractHandler(IFormatter<Map<E, T>> formatter, IStorage storage){
        this.formatter = formatter;
        this.storage = storage;
        this.dataMap = formatter.toObject(storage.read());
    }

    public T get(E identifier) {

        return dataMap.get(identifier);
    }

    protected Map<E, T> getDataMap() { return this.dataMap; }

    public void modify(E identifier, T object) {
        dataMap.put(identifier,object);
        String formattedStr = formatter.toFormat(dataMap);
        storage.save(formattedStr);
    }
}
