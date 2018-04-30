package br.com.senior;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.senior.admin.AdminTests;
import br.com.senior.sales.SalesTests;

@RunWith(Suite.class)
@SuiteClasses({ InitialChargeTests.class,
	SalesTests.class,
	AdminTests.class})
public class SalesSuite {

}
