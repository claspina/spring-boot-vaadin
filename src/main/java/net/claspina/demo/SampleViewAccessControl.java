package net.claspina.demo;

/**
 * This demonstrates how you can control access to views.
 */
/*@SpringComponent
public class SampleViewAccessControl implements ViewAccessControl {

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
         if (beanName.equals("adminView")) {
            return SecurityUtils.hasRole("ADMINISTRADOR");
        } else {
            return SecurityUtils.hasRole("BASICO");
        }
    }
}*/


public class SampleViewAccessControl {
}
