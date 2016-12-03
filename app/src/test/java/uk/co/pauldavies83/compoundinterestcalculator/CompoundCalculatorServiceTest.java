package uk.co.pauldavies83.compoundinterestcalculator;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CompoundCalculatorServiceTest {

    private CompoundCalculatorService compoundCalculatorService;

    @Before
    public void setUp() throws Exception {
        compoundCalculatorService = new CompoundCalculatorService();
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveValuesShouldBeReturned() throws Exception {
        assertThat(compoundCalculatorService.getFiveYearProjection(0, 0), IsCollectionWithSize.hasSize(5));
    }

    @Test
    public void forSpecificationADepositAmountAndInterestRateACollectionOfFiveValuesDefinedInSpecificationShouldBeReturned() throws Exception {
        double deposit = 100.00;
        double percentageRate = 5.00;

        List<Double> expexctedFromSpecification = Arrays.asList(105.00, 110.25, 115.76, 121.55, 127.63);

        assertThat(compoundCalculatorService.getFiveYearProjection(deposit, percentageRate), is(expexctedFromSpecification));
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveCorrectProjectionValuesShouldBeReturned() throws Exception {
        double deposit = 200.00;
        double percentageRate = 2.00;

        List<Double> expected = Arrays.asList(204.00, 208.08, 212.24, 216.49, 220.82);

        assertThat(compoundCalculatorService.getFiveYearProjection(deposit, percentageRate), is(expected));
    }

    @Test
    public void forAnotherDepositAmountAndInterestRateACollectionOfFiveCorrectProjectionValuesShouldBeReturned() throws Exception {
        double deposit = 100000.24;
        double percentageRate = 9.99;

        List<Double> expected = Arrays.asList(109990.26, 120978.29, 133064.02, 146357.12, 160978.19);

        assertThat(compoundCalculatorService.getFiveYearProjection(deposit, percentageRate), is(expected));
    }

    @Test(expected = CompoundCalculatorService.NegativeNumberArgumentExeption.class)
    public void providingANegativeDepositIsInvalid() throws Exception {
        double deposit = -1.00;
        double percentageRate = 1.00;

        compoundCalculatorService.getFiveYearProjection(deposit, percentageRate);
    }

    @Test(expected = CompoundCalculatorService.NegativeNumberArgumentExeption.class)
    public void providingANegativeInterstRateIsInvalid() throws Exception {
        double deposit = 1.00;
        double percentageRate = -1.00;

        compoundCalculatorService.getFiveYearProjection(deposit, percentageRate);
    }

}
