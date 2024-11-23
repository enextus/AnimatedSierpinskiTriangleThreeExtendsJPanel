package org.serpinskitriangle;

import org.mockito.internal.util.Timer;

import java.awt.event.ActionListener;

public class SwingTimerAdapter implements TimerInterface {
    private final Timer timer;

    public SwingTimerAdapter(int delay) {
        this.timer = new Timer(delay);
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void addActionListener(ActionListener listener) {

    }

    @Override
    public void addActionListener(ActionListener listener) {
        timer.addActionListener(listener);
    }
}