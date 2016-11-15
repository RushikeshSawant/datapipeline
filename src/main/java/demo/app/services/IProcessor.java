package demo.app.services;

/**
 * Defines contract for processing resources.
 * 
 * @author rushikesh
 *
 * @param <T>
 */
public interface IProcessor<T> {

	/**
	 * Implementing types to perform data/resource specific processing
	 * 
	 * @param resource
	 */
	long process(T resource);
}
