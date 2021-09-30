package com.suite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.inter.NegativeInterface;
import com.inter.PositiveInterface;
import com.test.CacheTest;
import com.test.NavigationTabsTest;
import com.test.WebElementsTest;

@RunWith(Categories.class)
@SuiteClasses({CacheTest.class, 
		NavigationTabsTest.class, 
		WebElementsTest.class})
@IncludeCategory({PositiveInterface.class})
@ExcludeCategory(NegativeInterface.class)

public class PositiveSuite {

}
