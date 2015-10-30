/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author Mario Ferreira
 */
public class ListUsersPage extends AbstractMoussaRaserPage {

    public ListUsersPage(WebDriver driver) {
        super(driver);

        // Check that we're on the right page.
        if (!"List Users".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

}
