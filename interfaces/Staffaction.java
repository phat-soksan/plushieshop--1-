package interfaces;

import model.Staff;

@FunctionalInterface
public interface Staffaction {
    void perform(Staff staff);
}