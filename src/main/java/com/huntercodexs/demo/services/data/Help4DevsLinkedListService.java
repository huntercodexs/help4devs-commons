package com.huntercodexs.demo.services.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsLinkedListService {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkedList {
        private int size;
        private Element first;
        private Element last;
        private Element previous;
        private Element current;

        private boolean seeker(String seek) {
            return (this.current != null && this.current.getValue().equals(seek));
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

        /*TODO: Fix bug when remove all elements from the object*/
        private void delete(String seek) {
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

        public void add(String value) {

            Element newElement = new Element(value);

            if (this.first == null && this.last == null) {
                this.first = newElement;
            } else {
                this.last.setNext(newElement);
            }
            this.last = newElement;
            this.size++;

        }

        public void remove(String seek) {
            this.delete(seek);
        }

        public void removeAll(String seek) {
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

        public Element get(int pos) {
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
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Element {
        private String value;
        private Element next;

        public Element(String value) {
            this.value = value;
        }
    }

}
