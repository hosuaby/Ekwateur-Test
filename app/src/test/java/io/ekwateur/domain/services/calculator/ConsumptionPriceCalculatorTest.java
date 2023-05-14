package io.ekwateur.domain.services.calculator;

import io.ekwateur.domain.model.ClientRef;
import io.ekwateur.domain.model.Consumption;
import io.ekwateur.domain.model.PrivateClient;
import io.ekwateur.domain.model.ProfessionalClient;
import io.ekwateur.domain.model.Siret;
import io.ekwateur.domain.services.calculator.ConsumptionPriceCalculator;
import io.ekwateur.domain.services.calculator.LargeProConsumptionPriceCalculator;
import io.ekwateur.domain.services.calculator.PrivateConsumptionPriceCalculator;
import io.ekwateur.domain.services.calculator.ProConsumptionPriceCalculator;
import io.ekwateur.domain.services.calculator.SmallProConsumptionPriceCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ConsumptionPriceCalculatorTest {
    static ConsumptionPriceCalculator consumptionPriceCalculator;

    @BeforeAll
    static void init() {
        var privateConsumptionPriceCalculator = new PrivateConsumptionPriceCalculator();
        var smallProConsumptionPriceCalculator = new SmallProConsumptionPriceCalculator();
        var largeProConsumptionPriceCalculator = new LargeProConsumptionPriceCalculator();
        var proConsumptionPriceCalculator = new ProConsumptionPriceCalculator(
                smallProConsumptionPriceCalculator, largeProConsumptionPriceCalculator);

        consumptionPriceCalculator = new ConsumptionPriceCalculator(
                privateConsumptionPriceCalculator, proConsumptionPriceCalculator);
    }

    @Test
    void testCalculatePriceForPrivateClient() {

        /* Given */
        var client = new PrivateClient(
                ClientRef.parse("EKW12345678"),
                "Mr.",
                "Bob",
                "L'éponge");
        var consumption = new Consumption(10, 2);

        /* When */
        var price = consumptionPriceCalculator.calculatePrice(client, consumption);

        /* Then */
        assertThat(price)
                .isNotNull()
                .isPositive()
                .isEqualTo(new BigDecimal("1.4400"));
    }

    @Test
    void testCalculatePriceForSmallProClient() {

        /* Given */
        var client = new ProfessionalClient(
                ClientRef.parse("EKW12345678"),
                Siret.parse("81445015100042"),
                "Ekwateur",
                9);
        var consumption = new Consumption(10, 2);

        /* When */
        var price = consumptionPriceCalculator.calculatePrice(client, consumption);

        /* Then */
        assertThat(price)
                .isNotNull()
                .isPositive()
                .isEqualTo(new BigDecimal("1.4060"));
    }

    @Test
    void testCalculatePriceForLargeProClient() {

        /* Given */
        var client = new ProfessionalClient(
                ClientRef.parse("EKW12345678"),
                Siret.parse("81445015100042"),
                "Ekwateur",
                1_000_001);
        var consumption = new Consumption(10, 2);

        /* When */
        var price = consumptionPriceCalculator.calculatePrice(client, consumption);

        /* Then */
        assertThat(price)
                .isNotNull()
                .isPositive()
                .isEqualTo(new BigDecimal("1.3620"));
    }
}
