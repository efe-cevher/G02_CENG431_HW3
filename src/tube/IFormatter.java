package tube;

public interface IFormatter<T>{

    public String toFormat(T object);

    public T toObject(String string);
}
