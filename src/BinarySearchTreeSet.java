/**
 * 二分搜索树实现的Set
 */
public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> tree;

    public BinarySearchTreeSet() {
        tree = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        tree.add(e);
    }

    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public int getSize() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

}
