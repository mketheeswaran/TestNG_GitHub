/**
 * This file is part of AlfrescoBasicFunctionalityTestingScripts.
 *
 * AlfrescoBasicFunctionalityTestingScripts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlfrescoBasicFunctionalityTestingScripts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AlfrescoBasicFunctionalityTestingScripts.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zaizi.automation.alfresco.drivers;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.zaizi.automation.alfresco.core.info.*;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class FirefoxDriverStore {

	/**
	 * Defining WebDriver
	 */
	private FirefoxDriver driver;

	/**
	 * @return
	 */
	public FirefoxDriver createWebDriver() {

		FirefoxProfile firefoxProfile = new FirefoxProfile();

		firefoxProfile.setPreference("browser.download.folderList",2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
		firefoxProfile.setPreference("browser.download.dir",TestCaseProperties.DEFAULT_DOWNLOAD_PATH);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
		firefoxProfile.setPreference("pdfjs.disabled", true);
		driver = new FirefoxDriver(firefoxProfile);
		return driver;
	}

}
