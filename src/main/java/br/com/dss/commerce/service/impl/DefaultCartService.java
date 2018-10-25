package br.com.dss.commerce.service.impl;

import br.com.dss.commerce.dto.Cart;
import br.com.dss.commerce.dto.Entry;
import br.com.dss.commerce.dto.Product;
import br.com.dss.commerce.service.CartService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
public class DefaultCartService implements CartService {

    private static final Logger LOG = Logger.getLogger(DefaultCartService.class.getName());

    private static final String CART_PREFIX = "BR";
    private static final String SKU_PREFIX = "SKU";

    @Override
    public List<Cart> getRandomCarts(final Integer quantity) {

        final Long start = System.currentTimeMillis();

        List<Cart> carts = IntStream.range(1, quantity).mapToObj(i -> generateRandomCart()).collect(toList());

        LOG.info("Generate random carts execution time: " + (System.currentTimeMillis() - start) + " ms.");

        return carts;
    }

    @Override
    public List<Cart> getRandomCartsWithSimpleThreads(final Integer quantity) {

        final Long start = System.currentTimeMillis();

//        TODO: Complete this implementation.
//
//        final Runnable runnable = () -> {};
//        final Thread thread = new Thread(runnable);
//        thread.start();

        LOG.info("Generate random carts with simple threads execution time: " + (System.currentTimeMillis() - start) + " ms.");

        return null;
    }

    @Override
    public List<Cart> getRandomCartsWithParallelStream(final Integer quantity) {

        final Long start = System.currentTimeMillis();

        List<Cart> carts = IntStream.range(1, quantity).parallel().mapToObj(i -> generateRandomCart()).collect(toList());

        LOG.info("Generate random carts with parallel stream execution time: " + (System.currentTimeMillis() - start) + " ms.");

        return carts;
    }

    @Override
    public List<Cart> getRandomCartsWithThreadPool(final Integer quantity) throws ExecutionException, InterruptedException {

        final Long start = System.currentTimeMillis();

        final IntStream range = IntStream.range(1, quantity);

        final ForkJoinPool pool = new ForkJoinPool(4);

        List<Cart> carts = pool.submit(() -> range.parallel().mapToObj(i -> generateRandomCart()).collect(toList())).get();

        LOG.info("Generate random carts with thread pool execution time: " + (System.currentTimeMillis() - start) + " ms.");

        return carts;
    }

    @Override
    public Cart generateRandomCart() {

        final Random random = new Random();
        final Integer code = 1000000;

        final String cartCode = CART_PREFIX + (code + random.nextInt(999999));

        final List<Entry> entries = IntStream.range(1, random.nextInt(50))
                .mapToObj(i -> generateRandomEntry()).collect(toList());

        final BigDecimal total = entries.stream()
                .map(e -> e.getProduct().getPrice().multiply(BigDecimal.valueOf(e.getQuantity())))
                    .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        return new Cart(cartCode, total, entries);
    }

    @Override
    public Entry generateRandomEntry() {

        final Random random = new Random();
        final Integer sku = 9000000;

        final String productCode = SKU_PREFIX + (sku + random.nextInt(999999));
        final BigDecimal productPrice = BigDecimal.valueOf(random.nextDouble());

        final Product product = new Product(productCode, RandomStringUtils.random(25), RandomStringUtils.random(250), productPrice);
        final Entry entry = new Entry(product, random.nextInt(10));

        return entry;
    }
}
