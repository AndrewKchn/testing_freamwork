package patterns.creational.factory.factory;

import patterns.creational.factory.buttons.Button;
import patterns.creational.factory.buttons.WindowsButton;

public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
