package demo.app.services;

/**
 * Defines contract for processing resources.
 * @author rushikesh
 *
 * @param <T>
 */
public interface IProcessor<T> {

	long process(T resource);
}
