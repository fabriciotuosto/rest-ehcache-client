package org.ehcache.rest.client;

import java.util.concurrent.TimeUnit;

import org.ehcache.rest.client.util.Preconditions;


/**
 * Represent a time in a given {@link TimeUnit}
 */
public class Duration {

	private final long duration;
	private final TimeUnit unit;

	/**
	 * 
	 * @param duration duration of time in the specified unit
	 * @param unit in which measure the duration
	 */
	public Duration(long duration, TimeUnit unit) {
        super();
		this.unit = Preconditions.checkNotNull(unit);
		this.duration = duration;
	}

    /**
     * Creates a duration object in miliseconds
     * is a default constructor
     * @param duration
     */
    public Duration(long duration){
        this(duration,TimeUnit.MILLISECONDS);
    }

	/**
	 * @return the actual duration in {@link TimeUnit.MILLISECONDS}
	 */
	public long inMiliseconds() {
		return TimeUnit.MILLISECONDS.convert(duration, unit);
	}

}
