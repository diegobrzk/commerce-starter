package br.com.dss.commerce.controller;


import br.com.dss.commerce.dto.Cart;
import br.com.dss.commerce.dto.Entry;
import br.com.dss.commerce.dto.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@RestController("/cart")
public class CommerceController {

    // MOCKED INFORMATION:
    private static final Integer ENTRY_QUANTITY = 3;
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(39.99);

    private static final String PRODUCT_NAME = "Dark Souls: Remastered - Nintendo Switch";
    private static final String CART_CODE = "BR0001";
    private static final String PRODUCT_DESCRIPTION =   "About the product\n" +
                                                        "Deep and dark universe\n" +
                                                        "Each end is a new Beginning\n" +
                                                        "Gameplay richness and possibilities\n" +
                                                        "Sense of learning, mastering and accomplishments\n" +
                                                        "The way of the multiplayer (up to 6 players with dedicated servers)";

    @RequestMapping(method = RequestMethod.GET)
    public Cart retrieveCart(@RequestParam final String code) {

        // This content is just a sample until the creation of mongodb structure.

        if (isEmpty(code))
            return new Cart();

        final Product product = new Product(code, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE);
        final Entry entry = new Entry(product, ENTRY_QUANTITY);

        final BigDecimal total = BigDecimal.valueOf(entry.getQuantity()).multiply(product.getPrice());

        return new Cart(CART_CODE, total, List.of(entry));
    }

}
