package com.nc.flagpicker.metric;

import java.util.Map;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
public interface IMetricService {

	void increaseCount(final String request, final int status);

	Map<?, ?> getFullMetric();
}
