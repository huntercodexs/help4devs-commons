package com.huntercodexs.demo.services.data;

public class Help4DevsLinkedListTypedefService {

    public static class LinkedList<T> {

        private int size;
        private Element<T> first;
        private Element<T> last;
        private Element<T> previous;
        private Element<T> current;

        public LinkedList() {}

        public LinkedList(int size, Element<T> first, Element<T> last, Element<T> previous, Element<T> current) {
            this.size = size;
            this.first = first;
            this.last = last;
            this.previous = previous;
            this.current = current;
        }

        private boolean seeker(T seek) {
            if (this.current == null) {
                return false;
            }

            return this.current.getValue().equals(seek);
        }

        private boolean sizeIsOneThenFirstAndLastIsNull() {
            if (this.size == 1) {
                this.first = null;
                this.last = null;
                return true;
            }
            return false;
        }

        private boolean currentIsEqualFirstThenFirstIsCurrentNext() {
            if (this.current == this.first) {
                this.first = this.current.getNext();
                this.current.setNext(null);
                return true;
            }
            return false;
        }

        private boolean currentIsEqualLastThenLastIsPreviousAndPreviousIsNull() {
            if (this.current == this.last) {
                this.last = this.previous;
                this.previous.setNext(null);
                return true;
            }
            return false;
        }

        private void previousIsCurrentNextAndCurrentIsNull() {
            this.previous.setNext(this.current.getNext());
            this.current = null;
        }

        private void previousIsEqualCurrentAndCurrentIsEqualCurrentNext() {
            this.previous = this.current;
            this.current = this.current.getNext();
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Element<T> getFirst() {
            return first;
        }

        public void setFirst(Element<T> first) {
            this.first = first;
        }

        public Element<T> getLast() {
            return last;
        }

        public void setLast(Element<T> last) {
            this.last = last;
        }

        public Element<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Element<T> previous) {
            this.previous = previous;
        }

        public Element<T> getCurrent() {
            return current;
        }

        public void setCurrent(Element<T> current) {
            this.current = current;
        }

        /*TODO: Fix bug then remove all elements from the object*/
        private void delete(T seek) {
            this.previous = null;
            this.current = this.first;

            for (int i = 0; i < this.getSize(); i++) {

                if (!seeker(seek)) {
                    previousIsEqualCurrentAndCurrentIsEqualCurrentNext();
                    continue;
                }

                if (!sizeIsOneThenFirstAndLastIsNull()) {
                    if (!currentIsEqualFirstThenFirstIsCurrentNext()) {
                        if (!currentIsEqualLastThenLastIsPreviousAndPreviousIsNull()) {
                            previousIsCurrentNextAndCurrentIsNull();
                        }
                    }
                }

                this.size--;
                break;
            }
        }

        public void add(T value) {

            Element<T> newElement = new Element<T>(value);

            if (this.first == null && this.last == null) {
                this.first = newElement;
            } else {
                this.last.setNext(newElement);
            }
            this.last = newElement;
            this.size++;

        }

        public void remove(T seek) {
            this.delete(seek);
        }

        public void removeAll(T seek) {
            for (int i = 0; i < this.getSize(); i++) {
                this.delete(seek);
            }
        }

        public void drop() {
            this.size = 0;
            this.first = null;
            this.last = null;
            this.previous = null;
            this.current = null;
        }

        public Element<T> get(int pos) {
            if (this.size == 0) {
                throw new RuntimeException("Illegal access, the object is empty: size=0");
            }

            if (pos > this.size) {
                throw new RuntimeException("Illegal access, the index "+pos+" is outbound " +
                        "from the size of the current Object: " + this.size);
            }

            this.current = this.first;

            for (int i = 0; i < pos; i++) {
                if (this.current.getNext() != null) {
                    this.current = this.current.getNext();
                }
            }
            return this.current;
        }

        public Iterator<T> getIterator() {
            return new Iterator<T>(this.first);
        }
    }

    public static class Element<T> {

        private T value;
        private Element<T> next;

        public Element() {}

        public Element(T value) {
            this.value = value;
        }

        public Element(T value, Element<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element<T> getNext() {
            return next;
        }

        public void setNext(Element<T> next) {
            this.next = next;
        }
    }

    public static class Iterator<T> {

        private Element<T> element;

        public Iterator() {}

        public Iterator(Element<T> element) {
            this.element = element;
        }

        public Element<T> getElement() {
            return element;
        }

        public void setElement(Element<T> element) {
            this.element = element;
        }

        public boolean hasNext() {
            return this.element.getNext() != null;
        }

        public Element<T> getNext() {
            this.element = this.element.getNext();
            return this.element;
        }
    }

}
