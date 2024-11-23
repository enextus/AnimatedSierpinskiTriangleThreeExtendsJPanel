package org.serpinskitriangle;

import java.awt.event.ActionListener;

public interface TimerInterface {
    void start();
    void stop();
    void addActionListener(ActionListener listener);

}
