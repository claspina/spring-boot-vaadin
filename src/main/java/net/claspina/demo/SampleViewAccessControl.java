package net.claspina.demo;

import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.ui.UI;
import net.claspina.demo.app.security.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * This demonstrates how you can control access to views.
 */
@Component
public class SampleViewAccessControl implements ViewAccessControl {

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
         if (beanName.equals("adminView")) {
            return SecurityUtils.hasRole("ADMINISTRADOR");
        } else {
            return SecurityUtils.hasRole("BASICO");
        }
    }
}
