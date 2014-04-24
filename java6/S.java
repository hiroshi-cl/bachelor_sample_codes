public interface S<T extends S<T>> {
    T next();

    int prop();
}
