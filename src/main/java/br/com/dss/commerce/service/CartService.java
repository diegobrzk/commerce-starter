package br.com.dss.commerce.service;

import br.com.dss.commerce.dto.Cart;
import br.com.dss.commerce.dto.Entry;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CartService {

    /**
     * Returns random generated carts defining the {@code quantity} to generate.
     *
     * @param quantity
     * @return {@link List<Cart>}
     */
    List<Cart> getRandomCarts(final Integer quantity);

    /**
     * Returns random generated carts using simple threads from java 7 and before
     * and defining the {@code quantity} to generate.
     *
     * @param quantity
     * @return {@link List<Cart>}
     */
    List<Cart> getRandomCartsWithSimpleThreads(final Integer quantity);

    /**
     * Returns random generated carts using parallel streams from java 8
     * and defining the {@code quantity} to generate.
     *
     * @param quantity
     * @return {@link List<Cart>}
     */
    List<Cart> getRandomCartsWithParallelStream(final Integer quantity);

    /**
     * Returns random generated carts using thread pool from java 8
     * and defining the {@code quantity} to generate.
     *!
     * @param quantity
     * @return {@link List<Cart>}
     */
    List<Cart> getRandomCartsWithThreadPool(final Integer quantity) throws ExecutionException, InterruptedException;

    /**
     * Returns a cart generated with random information.
     *
     * @return {@link Cart}
     */
    Cart generateRandomCart();

    /**
     * Returns an entry generated with random information.
     *
     * @return {@link Entry}
     */
    Entry generateRandomEntry();

}
