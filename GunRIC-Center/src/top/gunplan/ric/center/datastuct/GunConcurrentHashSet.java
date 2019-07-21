package top.gunplan.ric.center.datastuct;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GunConcurrentHashSet
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-21 12:09
 */


public class GunConcurrentHashSet<E> implements Set<E> {
    private final Map<E, Object> map = new ConcurrentHashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        map.put(e, null);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(map::remove);
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }
}
