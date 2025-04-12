import java.util.NoSuchElementException;
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        DoublyLinkedListNode<T> tempNode = new DoublyLinkedListNode<>(data);
        if (size == 0) {
            head = tempNode;
            tail = tempNode;
            size++;
            return;
        }
        if (index == 0) {
            head.setPrevious(tempNode);
            tempNode.setNext(head);
            head = tempNode;
            size++;
            return;
        }
        if (index == size) {
            tail.setNext(tempNode);
            tempNode.setPrevious(tail);
            tail = tempNode;
            size++;
            return;
        }
        // For loop that reaches the index, use index & relate to size
        // Reassign next & previous
        if (index >= size / 2) {
            // Start at tail
            DoublyLinkedListNode<T> currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.getPrevious();
            }
            currentNode.getPrevious().setNext(tempNode);
            tempNode.setNext(currentNode);
            tempNode.setPrevious(currentNode.getPrevious());
            currentNode.setPrevious(tempNode);
            size++;
        } else {
            DoublyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.getNext().setPrevious(tempNode);
            tempNode.setNext(currentNode.getNext());
            tempNode.setPrevious(currentNode);
            currentNode.setNext(tempNode);
            size++;
        }

    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);

    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        T returnValue = null;
        if (size == 1) {
            returnValue = tail.getData();
            clear();
            return returnValue;
        }
        if (index == 0) {
            returnValue = head.getData();
            head.getNext().setPrevious(null);
            head = head.getNext();
        } else if (index == size - 1) {
            returnValue = tail.getData();
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
        } else if (index >= size / 2) {
            DoublyLinkedListNode<T> currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.getPrevious();
            }
            returnValue = currentNode.getData();
            currentNode.getPrevious().setNext(currentNode.getNext());
            currentNode.getNext().setPrevious(currentNode.getPrevious());
        } else if (index < size / 2) {
            DoublyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            returnValue = currentNode.getData();
            currentNode.getNext().setPrevious(currentNode.getPrevious());
            currentNode.getPrevious().setNext(currentNode.getNext());
        }
        size--;
        return returnValue;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty!");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty!");
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        T returnValue = null;
        if (index == 0) {
            returnValue = head.getData();
        } else if (index == size - 1) {
            returnValue = tail.getData();
        } else if (index >= size / 2) {
            DoublyLinkedListNode<T> currentNode = tail;
            for (int i = size; i > index + 1; i--) {
                currentNode = currentNode.getPrevious();
            }
            returnValue = currentNode.getData();
        } else if (index < size / 2) {
            DoublyLinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            returnValue = currentNode.getData();
        }
        return returnValue;

    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;

    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        T returnVal = null;
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1 && tail.getData().equals(data)) {
            returnVal = tail.getData();
            clear();
            return returnVal;
        }
        if (tail.getData().equals(data)) {
            returnVal = tail.getData();
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
            size--;
            return returnVal;
        }
        DoublyLinkedListNode<T> currentNode = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (currentNode.getData().equals(data) && i == 0) {
                returnVal = currentNode.getData();
                head.getNext().setPrevious(null);
                head = head.getNext();
                size--;
                return returnVal;
            } else if (currentNode.getData().equals(data)) {
                returnVal = currentNode.getData();
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                size--;
                return returnVal;
            }
            currentNode = currentNode.getPrevious();
        }
        throw new NoSuchElementException("The data was not found in the list.");

    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        if (size == 0) {
            return new Object[]{};
        }
        Object[] returnArr = new Object[size];
        DoublyLinkedListNode<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            returnArr[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return returnArr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
