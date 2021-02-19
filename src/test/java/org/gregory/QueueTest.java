package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class QueueTest {
    Queue<Integer> queueActual;

    @BeforeEach
    void setUp() {
        queueActual = new Queue<>();
        queueActual.enqueue(1);
        queueActual.enqueue(2);
        queueActual.enqueue(3);
        queueActual.enqueue(4);
    }

    @AfterEach
    void tearDown() {
        queueActual = null;
    }

    @Test
    void enqueue() {
        assertThat(queueActual.dequeue(), is(1));
        assertThat(queueActual.size(), is(3));
        assertThat(queueActual.dequeue(), is(2));
        assertThat(queueActual.size(), is(2));
        assertThat(queueActual.dequeue(), is(3));
        assertThat(queueActual.size(), is(1));
        assertThat(queueActual.dequeue(), is(4));
        assertThat(queueActual.size(), is(0));

        assertThat(queueActual.dequeue(), nullValue());
        assertThat(queueActual.size(), is(0));


    }

    @Test
    void size() {
        assertThat(queueActual.size(), is(4));
    }

    @Test
    void testEnqueue() {
        assertThat(queueActual.dequeue(), is(1));
        assertThat(queueActual.size(), is(3));
        queueActual.enqueue(1000);

        assertThat(queueActual.dequeue(), is(2));
        assertThat(queueActual.size(), is(3));
        queueActual.enqueue(2000);

        assertThat(queueActual.dequeue(), is(3));
        assertThat(queueActual.size(), is(3));

        assertThat(queueActual.dequeue(), is(4));
        assertThat(queueActual.size(), is(2));

        assertThat(queueActual.dequeue(), is(1000));
        assertThat(queueActual.size(), is(1));

        assertThat(queueActual.dequeue(), is(2000));
        assertThat(queueActual.size(), is(0));

        assertThat(queueActual.dequeue(), nullValue());
        assertThat(queueActual.size(), is(0));
    }

    @Test
    void spin0() {
        queueActual.spin(0);
        assertThat(queueActual.dequeue(), is(1));
        assertThat(queueActual.size(), is(3));
    }

    @Test
    void spin1() {
        queueActual.spin(1);
        assertThat(queueActual.dequeue(), is(2));
        assertThat(queueActual.size(), is(3));
    }

    @Test
    void spin2() {
        queueActual.spin(2);
        assertThat(queueActual.dequeue(), is(3));
        assertThat(queueActual.size(), is(3));
    }

    @Test
    void spin4() {
        queueActual.spin(4);
        assertThat(queueActual.dequeue(), is(1));
        assertThat(queueActual.size(), is(3));
    }

    @Test
    void spin5() {
        queueActual.spin(5);
        assertThat(queueActual.dequeue(), is(2));
        assertThat(queueActual.size(), is(3));
    }

    @Test
    void spinNullQueue() {
        queueActual = new Queue<Integer>();
        queueActual.spin(5);
        assertThat(queueActual.dequeue(), nullValue());
        assertThat(queueActual.size(), is(0));
    }
}